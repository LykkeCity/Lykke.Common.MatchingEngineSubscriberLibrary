package com.lykke.me.subscriber.incoming.events

interface MeEvent {
    val sequenceNumber: Long
    val messageId: String
}