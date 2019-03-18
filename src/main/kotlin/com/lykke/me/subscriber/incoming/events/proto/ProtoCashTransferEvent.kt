package com.lykke.me.subscriber.incoming.events.proto

import com.lykke.me.subscriber.incoming.events.CashTransferEvent
import com.lykke.matching.engine.messages.outgoing.OutgoingMessages as ProtocolEvents

class ProtoCashTransferEvent(message: ProtocolEvents.CashTransferEvent) :
        MeProtoEvent<ProtocolEvents.CashTransferEvent>(message), CashTransferEvent {
    override val sequenceNumber: Long = message.header.sequenceNumber
    override val messageId: String = message.header.messageId
}