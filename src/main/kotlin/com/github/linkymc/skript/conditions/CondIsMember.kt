package com.github.linkymc.skript.conditions

import ch.njol.skript.Skript
import ch.njol.skript.lang.Condition
import ch.njol.skript.lang.Expression
import ch.njol.skript.lang.SkriptParser
import ch.njol.util.Kleenean
import com.github.linkymc.lib.API
import org.bukkit.entity.Player
import org.bukkit.event.Event

class CondIsMember : Condition() {
    companion object {
        init {
            Skript.registerCondition(
                    CondIsMember::class.java,
                    "%player% is in [the] discord [server]",
                    "%player% is not in [the] discord [server]",
            )
        }
    }

    lateinit var playerExpr: Expression<Player>
    private var pattern: Int = 0

    override fun init(
            exprs: Array<out Expression<*>>?,
            matchedPattern: Int,
            isDelayed: Kleenean?,
            parseResult: SkriptParser.ParseResult?
    ): Boolean {
        pattern = matchedPattern
        playerExpr = exprs!![0] as Expression<Player>
        return true
    }

    override fun check(e: Event?): Boolean {
        val player = playerExpr.getSingle(e)!!
        val isNot = pattern == 1

        val resp = API.getUser(player.uniqueId)

        // if "has not", returns true
        // if "has", returns false
        if(resp === null) return isNot

        return if(isNot) {
            !resp.isInGuild
        } else resp.isInGuild
    }

    override fun toString(e: Event?, debug: Boolean): String {
        return "if ${playerExpr.getSingle(e)} is in discord"
    }
}