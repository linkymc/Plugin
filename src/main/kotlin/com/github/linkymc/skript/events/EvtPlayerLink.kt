package com.github.linkymc.skript.events

import ch.njol.skript.Skript
import ch.njol.skript.lang.util.SimpleEvent
import ch.njol.skript.registrations.EventValues
import ch.njol.skript.util.Getter
import com.github.linkymc.events.PlayerLinkEvent
import org.bukkit.entity.Player
import org.bukkit.event.Event

class EvtPlayerLink : SimpleEvent() {
    companion object {
        init {
            Skript.registerEvent(
                "Player link",
                EvtPlayerLink::class.java,
                PlayerLinkEvent::class.java,
                "[player] link")

            EventValues.registerEventValue(PlayerLinkEvent::class.java, Player::class.java, object : Getter<Player, PlayerLinkEvent>() {
                override fun get(e: PlayerLinkEvent): Player {
                    return e.player
                }
            }, 0)
        }
    }

    override fun toString(e: Event?, debug: Boolean): String {
        return "player link event"
    }
}