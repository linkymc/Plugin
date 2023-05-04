package com.github.linkymc.lib

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.minimessage.MiniMessage
import kotlin.random.Random

fun generateRandomString(length: Int = 4): String {
    val chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"
    return (1..length)
        .map { chars[Random.nextInt(0, chars.length)] }
        .joinToString("")
}

fun String.mm(): Component {
    val mm = MiniMessage.miniMessage()
    return mm.deserialize(this)
}