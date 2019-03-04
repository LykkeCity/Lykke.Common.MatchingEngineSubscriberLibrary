package com.lykke.me.subscriber.incoming.events.proto

import com.lykke.me.subscriber.incoming.events.CashInEvent
import com.lykke.matching.engine.messages.outgoing.OutgoingMessages as ProtocolEvents

class ProtoCashInEvent(message: ProtocolEvents.CashInEvent,
                       override val messageId: String) :
        MeProtoEvent<ProtocolEvents.CashInEvent>(message), CashInEvent