package com.lykke.me.subscriber.incoming.events.proto.deserialization

import com.lykke.me.subscriber.incoming.events.proto.MeProtoEvent
import com.lykke.me.subscriber.incoming.events.proto.ProtoReservedBalanceUpdateEvent
import com.lykke.matching.engine.messages.outgoing.OutgoingMessages as ProtocolEvents

class ProtoReservedBalanceUpdateEventDeserializer :
    MeProtoEventDeserializer<ProtocolEvents.ReservedBalanceUpdateEvent> {
    override fun deserialize(byteArray: ByteArray): MeProtoEvent<ProtocolEvents.ReservedBalanceUpdateEvent> {
        val message = ProtocolEvents.ReservedBalanceUpdateEvent.parseFrom(byteArray)
        return ProtoReservedBalanceUpdateEvent(message, message.header.messageId)
    }

}