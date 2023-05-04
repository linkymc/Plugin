@file:Command(
    "link",
    permission = "linky.link"
)

package com.github.linkymc.commands

import com.github.linkymc.lib.generateRandomString
import com.github.linkymc.lib.getMessage
import me.aroze.arozeutils.kotlin.extension.replacePlaceholders
import me.honkling.commando.lib.Command
import net.kyori.adventure.inventory.Book
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.minimessage.MiniMessage
import org.bukkit.entity.Player

fun link(executor: Player) {
    val mm = MiniMessage.miniMessage()

    val lines = getMessage("lines") ?: "Invalid lines"

    val placeholders = hashMapOf(
        "code" to generateRandomString(),
    )

    val bookPages: MutableList<Component> = mutableListOf(
        mm.deserialize(lines.replacePlaceholders(placeholders))
    )

    val myBook: Book = Book.book(
        mm.deserialize("<aqua>Linking Book"),
        mm.deserialize("Linky"),
        bookPages
    )
    executor.openBook(myBook)
}

