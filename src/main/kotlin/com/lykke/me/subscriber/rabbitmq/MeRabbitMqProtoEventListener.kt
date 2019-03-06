package com.lykke.me.subscriber.rabbitmq

import com.lykke.me.subscriber.config.RabbitMqConfig
import com.lykke.me.subscriber.incoming.events.proto.MeProtoEvent
import com.lykke.me.subscriber.incoming.events.proto.deserialization.MeProtoEventDeserializer
import com.lykke.utils.logging.ThrottlingLogger
import com.lykke.utils.notification.AbstractListener
import com.lykke.utils.rabbit.Connector
import com.lykke.utils.rabbit.ConsumerFactory
import com.lykke.utils.rabbit.RabbitMqSubscriber
import com.rabbitmq.client.Channel
import com.rabbitmq.client.ConnectionFactory
import com.rabbitmq.client.Consumer
import java.util.concurrent.BlockingQueue
import kotlin.concurrent.thread
import com.lykke.utils.rabbit.RabbitMqConfig as UtilsRabbitMqConfig

class MeRabbitMqProtoEventListener(private val configs: Set<RabbitMqConfig>,
                                   private val queue: BlockingQueue<RmqMessageWrapper>) : AbstractListener<MeProtoEvent<*>>() {

    companion object {
        private val LOGGER = ThrottlingLogger.getLogger(MeRabbitMqProtoEventListener::class.java.name)
    }

    private var started = false
    private val deserializerByRoutingKey = HashMap<String, MeProtoEventDeserializer<*>>()

    @Synchronized
    fun start() {
        if (started) {
            return
        }
        initDeserializers()
        startRabbitMqSubscribers()
        startMessagesProcessing()
        started = true
    }

    private fun initDeserializers() {
        deserializerByRoutingKey.putAll(configs.map { it.routingKey }
            .groupBy { it }
            .mapValues { MeProtoEventDeserializer.createDeserializer(it.value.single()) })
    }

    private fun startRabbitMqSubscribers() {
        configs.forEach(::startRabbitMqSubscriber)
    }

    private fun startRabbitMqSubscriber(config: RabbitMqConfig) {
        val tmpFactory = ConnectionFactory()
        tmpFactory.setUri(config.uri)
        val routingKey = config.routingKey
        RabbitMqSubscriber(
            UtilsRabbitMqConfig(
                tmpFactory.host,
                tmpFactory.port,
                tmpFactory.username,
                tmpFactory.password,
                config.exchange,
                config.queue,
                null
            ),
            object : Connector {
                override fun createChannel(config: UtilsRabbitMqConfig): Channel {
                    val factory = ConnectionFactory()
                    factory.host = config.host
                    factory.port = config.port
                    factory.username = config.username
                    factory.password = config.password
                    factory.requestedHeartbeat = 30
                    factory.isAutomaticRecoveryEnabled = true

                    val connection = factory.newConnection()
                    val channel = connection!!.createChannel()

                    channel.exchangeDeclarePassive(config.exchange)
                    channel.queueDeclare(config.queue, false, false, true, null)
                    channel.queueBind(config.queue, config.exchange, routingKey)
                    return channel
                }
            },
            object : ConsumerFactory {
                override fun newConsumer(channel: Channel): Consumer {
                    return MeEventConsumer(channel, routingKey, queue)
                }
            }
        ).start()
    }

    private fun startMessagesProcessing() {
        thread(name = "${MeRabbitMqProtoEventListener::class.java.name}.messageProcessing") {
            while (true) {
                val messageWrapper = queue.take()
                try {
                    processMessage(messageWrapper)
                } catch (e: Exception) {
                    LOGGER.error("Unable to process message from RabbitMQ (routingKey: ${messageWrapper.routingKey})", e)
                }
            }
        }
    }

    private fun processMessage(messageWrapper: RmqMessageWrapper) {
        val deserializer = deserializerByRoutingKey[messageWrapper.routingKey]
        if (deserializer == null) {
            LOGGER.error("There is no deserializer for routingKey=${messageWrapper.routingKey}")
        }
        val event = deserializer!!.deserialize(messageWrapper.message)
        LOGGER.debug("Got event from rabbit mq: routingKey=${messageWrapper.routingKey}, class: ${event.message::class.java.name}, messageId=${event.messageId}")
        notifySubscribers(event)
    }
}