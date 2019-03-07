package com.lykke.me.subscriber.incoming.events.proto.deserialization

import com.lykke.me.subscriber.incoming.events.proto.MeProtoEvent
import com.lykke.me.subscriber.incoming.events.proto.ProtoExecutionEvent
import com.lykke.matching.engine.messages.outgoing.OutgoingMessages as ProtocolEvents

class ProtoExecutionEventDeserializer : MeProtoEventDeserializer<ProtocolEvents.ExecutionEvent> {
    override fun deserialize(byteArray: ByteArray): MeProtoEvent<ProtocolEvents.ExecutionEvent> {
        val message = ProtocolEvents.ExecutionEvent.parseFrom(byteArray)
        return ProtoExecutionEvent(message, message.header.messageId)
    }


}