package com.lykke.me.subscriber

interface MeSubscriber<T> {
    fun notify(message: T)
}