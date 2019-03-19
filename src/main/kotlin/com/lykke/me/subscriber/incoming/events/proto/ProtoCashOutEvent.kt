package com.lykke.me.subscriber.incoming.events.proto

import com.lykke.me.subscriber.incoming.events.CashOutEvent
import com.lykke.matching.engine.messages.outgoing.OutgoingMessages as ProtocolEvents

class ProtoCashOutEvent(message: ProtocolEvents.CashOutEvent) :
        MeProtoEvent<ProtocolEvents.CashOutEvent>(message), CashOutEvent {
    override val sequenceNumber: Long = message.header.sequenceNumber
    override val messageId: String = message.header.messageId
}