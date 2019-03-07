package com.lykke.me.subscriber.incoming.events.proto

import com.lykke.me.subscriber.incoming.events.CashTransferEvent
import com.lykke.matching.engine.messages.outgoing.OutgoingMessages as ProtocolEvents

class ProtoCashTransferEvent(message: ProtocolEvents.CashTransferEvent,
                             override val messageId: String) :
        MeProtoEvent<ProtocolEvents.CashTransferEvent>(message), CashTransferEvent