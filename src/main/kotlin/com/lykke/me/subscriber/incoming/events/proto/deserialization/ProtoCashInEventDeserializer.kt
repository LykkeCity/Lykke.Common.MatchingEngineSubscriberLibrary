package com.lykke.me.subscriber.incoming.events.proto.deserialization

import com.lykke.me.subscriber.incoming.events.proto.MeProtoEvent
import com.lykke.me.subscriber.incoming.events.proto.ProtoCashInEvent
import com.lykke.matching.engine.messages.outgoing.OutgoingMessages as ProtocolEvents

class ProtoCashInEventDeserializer : MeProtoEventDeserializer<ProtocolEvents.CashInEvent> {
    override fun deserialize(byteArray: ByteArray): MeProtoEvent<ProtocolEvents.CashInEvent> {
        val message = ProtocolEvents.CashInEvent.parseFrom(byteArray)
        return ProtoCashInEvent(message, message.header.messageId)
    }
}