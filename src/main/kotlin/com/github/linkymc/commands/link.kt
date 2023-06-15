@file:Command(
    "link",
    permission = ""
)

package com.github.linkymc.commands

import com.github.linkymc.events.PlayerLinkEvent
import com.github.linkymc.lib.API
import com.github.linkymc.lib.getMessage
import com.github.linkymc.lib.mm
import me.aroze.arozeutils.minecraft.generic.coloured
import me.honkling.commando.lib.Command
import org.bukkit.Bukkit
import org.bukkit.entity.Player

private val cachedUpdates = mutableListOf<String>()
private val pluginManager = Bukkit.getPluginManager()

fun link(executor: Player) {
    val msg = getMessage("linkCTA") ?: "<#ffd4e3>Link your Discord account by joining the Discord and sending <b>/link</b>."
    executor.sendMessage(msg.mm())
}

fun accept(executor: Player, id: String) {
    if(cachedUpdates.contains(id)) return

    val resp = API.updateSessionStatus(id, "approved")

    if(resp === null) {
        executor.sendMessage("<red>An error occurred whilst accepting this link request.".mm())
        return
    }

    pluginManager.callEvent(PlayerLinkEvent(executor))

    executor.sendMessage("&pYour Discord is now linked!".coloured())

    cachedUpdates.add(id)
}

fun deny(executor: Player, id: String) {
    if(cachedUpdates.contains(id)) return

    val resp = API.updateSessionStatus(id, "denied")

    if(resp === null) {
        executor.sendMessage("<red>An error occurred whilst denying this link request.".mm())
        return
    }

    executor.sendMessage("Successfully denied.".coloured())

    cachedUpdates.add(id)
}