package com.lykke.me.subscriber.incoming.events.proto

import com.lykke.me.subscriber.incoming.events.CashInEvent
import com.lykke.matching.engine.messages.outgoing.OutgoingMessages as ProtocolEvents

class ProtoCashInEvent(message: ProtocolEvents.CashInEvent) :
        MeProtoEvent<ProtocolEvents.CashInEvent>(message), CashInEvent {
    override val sequenceNumber: Long = message.header.sequenceNumber
    override val messageId: String = message.header.messageId
}