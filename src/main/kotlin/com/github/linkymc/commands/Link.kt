@file:Command(
    "link",
    permission = "linky.link"
)

package com.github.linkymc.commands

import com.github.linkymc.lib.generateRandomString
import com.github.linkymc.lib.getMessage
import com.github.linkymc.lib.mm
import me.aroze.arozeutils.kotlin.extension.replacePlaceholders
import me.honkling.commando.lib.Command
import net.kyori.adventure.inventory.Book
import net.kyori.adventure.text.Component
import org.bukkit.entity.Player

fun link(executor: Player) {
    val lines = getMessage("lines") ?: "Invalid lines"

    val placeholders = hashMapOf(
        "code" to generateRandomString(),
    )

    val bookPages: MutableList<Component> = mutableListOf(
        lines.replacePlaceholders(placeholders).mm()
    )

    val myBook: Book = Book.book(
        "<aqua>Linking Book".mm(),
        "Linky".mm(),
        bookPages
    )
    executor.openBook(myBook)
}

