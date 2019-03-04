package com.lykke.me.subscriber.config

data class RabbitMqConfig(val uri: String,
                          val exchange: String,
                          val queue: String,
                          val routingKey: String)