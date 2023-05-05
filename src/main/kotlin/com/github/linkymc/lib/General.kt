package com.github.linkymc.lib

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.minimessage.MiniMessage

/**
 * A String extension function to convert a string into a deserialized MiniMessage component.
 *
 * @return A MiniMessage component
 */
fun String.mm(): Component {
    val mm = MiniMessage.miniMessage()
    return mm.deserialize(this)
}