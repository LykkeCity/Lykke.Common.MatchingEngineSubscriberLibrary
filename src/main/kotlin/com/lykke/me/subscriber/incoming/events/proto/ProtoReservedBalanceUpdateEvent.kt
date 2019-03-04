package com.lykke.me.subscriber.incoming.events.proto

import com.lykke.me.subscriber.incoming.events.ReservedBalanceUpdateEvent
import com.lykke.matching.engine.messages.outgoing.OutgoingMessages as ProtocolEvents

class ProtoReservedBalanceUpdateEvent(message: ProtocolEvents.ReservedBalanceUpdateEvent,
                                      override val messageId: String) :
        MeProtoEvent<ProtocolEvents.ReservedBalanceUpdateEvent>(message), ReservedBalanceUpdateEvent