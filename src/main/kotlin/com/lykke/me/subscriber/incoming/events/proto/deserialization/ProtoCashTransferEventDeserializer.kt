package com.lykke.me.subscriber.incoming.events.proto.deserialization

import com.lykke.me.subscriber.incoming.events.proto.MeProtoEvent
import com.lykke.me.subscriber.incoming.events.proto.ProtoCashTransferEvent
import com.lykke.matching.engine.messages.outgoing.OutgoingMessages as ProtocolEvents

class ProtoCashTransferEventDeserializer : MeProtoEventDeserializer<ProtocolEvents.CashTransferEvent> {
    override fun deserialize(byteArray: ByteArray): MeProtoEvent<ProtocolEvents.CashTransferEvent> {
        val message = ProtocolEvents.CashTransferEvent.parseFrom(byteArray)
        return ProtoCashTransferEvent(message)
    }
}