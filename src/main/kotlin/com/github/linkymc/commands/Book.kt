@file:Command(
    "book",
    permission = "linky.link"
)

package com.github.linkymc.commands

import com.github.linkymc.lib.getMessage
import me.aroze.arozeutils.minecraft.generic.centerTextToChat
import me.aroze.arozeutils.minecraft.generic.coloured
import me.honkling.commando.lib.Command
import net.kyori.adventure.inventory.Book
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.minimessage.MiniMessage
import org.bukkit.entity.Player

fun book(executor: Player) {
    val mm = MiniMessage.miniMessage()

    val lines = getMessage("lines") ?: "Invalid lines"

    val bookPages: MutableList<Component> = mutableListOf(
        mm.deserialize(lines.coloured())
    )

    executor.sendMessage(centerTextToChat(lines))

    val myBook: Book = Book.book(
        mm.deserialize("<aqua>Linking Book"),
        mm.deserialize("Linky"),
        bookPages
    )
    executor.openBook(myBook)
}

