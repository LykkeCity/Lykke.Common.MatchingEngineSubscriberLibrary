package com.lykke.me.subscriber.incoming.events.proto

import com.lykke.me.subscriber.incoming.events.ReservedBalanceUpdateEvent
import com.lykke.matching.engine.messages.outgoing.OutgoingMessages as ProtocolEvents

class ProtoReservedBalanceUpdateEvent(message: ProtocolEvents.ReservedBalanceUpdateEvent) :
        MeProtoEvent<ProtocolEvents.ReservedBalanceUpdateEvent>(message), ReservedBalanceUpdateEvent {
    override val sequenceNumber: Long = message.header.sequenceNumber
    override val messageId: String = message.header.messageId
}