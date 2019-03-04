package com.lykke.me.subscriber

interface MeListener<T> {
    fun subscribe(subscriber: MeSubscriber<T>)
    fun unsubscribe(subscriber: MeSubscriber<T>)
}