package com.lykke.me.subscriber.rabbitmq

class RmqMessageWrapper(val routingKey: String,
                        val message: ByteArray)