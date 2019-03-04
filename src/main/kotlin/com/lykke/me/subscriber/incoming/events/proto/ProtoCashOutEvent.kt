package com.lykke.me.subscriber.incoming.events.proto

import com.lykke.me.subscriber.incoming.events.CashOutEvent
import com.lykke.matching.engine.messages.outgoing.OutgoingMessages as ProtocolEvents

class ProtoCashOutEvent(message: ProtocolEvents.CashOutEvent,
                        override val messageId: String) :
        MeProtoEvent<ProtocolEvents.CashOutEvent>(message), CashOutEvent