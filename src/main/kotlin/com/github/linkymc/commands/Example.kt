@file:Command("example")

package com.github.linkymc.commands

import me.honkling.commando.lib.Command
import org.bukkit.entity.Player

fun example(executor: Player) {
    executor.sendMessage("hello world!")
}