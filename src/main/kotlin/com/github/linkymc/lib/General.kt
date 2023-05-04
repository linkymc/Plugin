package com.github.linkymc.lib

import kotlin.random.Random

fun generateRandomString(length: Int = 4): String {
    val chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"
    return (1..length)
        .map { chars[Random.nextInt(0, chars.length)] }
        .joinToString("")
}