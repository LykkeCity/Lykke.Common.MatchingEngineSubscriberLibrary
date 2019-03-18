package com.lykke.me.subscriber.incoming.events.proto.deserialization

import com.lykke.me.subscriber.incoming.events.proto.MeProtoEvent
import com.lykke.me.subscriber.incoming.events.proto.ProtoCashOutEvent
import com.lykke.matching.engine.messages.outgoing.OutgoingMessages as ProtocolEvents

class ProtoCashOutEventDeserializer : MeProtoEventDeserializer<ProtocolEvents.CashOutEvent> {
    override fun deserialize(byteArray: ByteArray): MeProtoEvent<ProtocolEvents.CashOutEvent> {
        val message = ProtocolEvents.CashOutEvent.parseFrom(byteArray)
        return ProtoCashOutEvent(message)
    }

}