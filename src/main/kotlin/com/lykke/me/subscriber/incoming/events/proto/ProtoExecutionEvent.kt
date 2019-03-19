package com.lykke.me.subscriber.incoming.events.proto

import com.lykke.me.subscriber.incoming.events.ExecutionEvent
import com.lykke.matching.engine.messages.outgoing.OutgoingMessages as ProtocolEvents

class ProtoExecutionEvent(message: ProtocolEvents.ExecutionEvent) :
        MeProtoEvent<ProtocolEvents.ExecutionEvent>(message), ExecutionEvent {
    override val sequenceNumber: Long = message.header.sequenceNumber
    override val messageId: String = message.header.messageId
}