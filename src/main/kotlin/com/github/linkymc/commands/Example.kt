@file:Command("example")

package com.github.linkymc.commands

import me.honkling.commando.lib.Command
import net.kyori.adventure.text.minimessage.MiniMessage
import org.bukkit.entity.Player

fun example(executor: Player) {
    val mm = MiniMessage.miniMessage()

    executor.sendMessage(mm.deserialize("<rainbow>this works!!!</rainbow>"))
}