@file:Command(
    "link",
    permission = "linky.link"
)

package com.github.linkymc.commands

import com.github.linkymc.lib.getMessage
import com.github.linkymc.lib.mm
import me.aroze.arozeutils.minecraft.generic.coloured
import me.honkling.commando.lib.Command
import org.bukkit.entity.Player

fun link(executor: Player) {
    val msg = getMessage("linkCTA") ?: "<#ffd4e3>Link your Discord account by joining the Discord and sending <b>/link</b>."
    executor.sendMessage(msg.mm())
}

fun accept(executor: Player, id: String) {
    // call API
    executor.sendMessage("&pwoo".coloured())
}

fun deny(executor: Player, id: String) {
    executor.sendMessage("&palso, woo.".coloured())
    // call API
    // add to hashmap
}