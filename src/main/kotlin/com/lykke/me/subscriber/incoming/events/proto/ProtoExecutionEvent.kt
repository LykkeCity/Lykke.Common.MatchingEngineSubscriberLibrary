package com.lykke.me.subscriber.incoming.events.proto

import com.lykke.me.subscriber.incoming.events.ExecutionEvent
import com.lykke.matching.engine.messages.outgoing.OutgoingMessages as ProtocolEvents

class ProtoExecutionEvent(message: ProtocolEvents.ExecutionEvent,
                          override val messageId: String) :
        MeProtoEvent<ProtocolEvents.ExecutionEvent>(message), ExecutionEvent