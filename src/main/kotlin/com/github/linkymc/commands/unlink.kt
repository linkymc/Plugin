@file:Command(
        "unlink",
        permission = ""
)

package com.github.linkymc.commands

import com.github.linkymc.lib.API
import com.github.linkymc.lib.getMessage
import com.github.linkymc.lib.mm
import me.honkling.commando.lib.Command
import org.bukkit.entity.Player

fun unlink(executor: Player) {
    API.unlinkUser(executor.uniqueId)
    val msg = getMessage("unlinked") ?: "<#ffd4e3>Successfully unlinked."
    executor.sendMessage(msg.mm())
}