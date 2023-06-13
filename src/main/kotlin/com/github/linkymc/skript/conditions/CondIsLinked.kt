package com.github.linkymc.skript.conditions

import ch.njol.skript.Skript
import ch.njol.skript.lang.Condition
import ch.njol.skript.lang.Expression
import ch.njol.skript.lang.SkriptParser
import ch.njol.util.Kleenean
import com.github.linkymc.lib.API
import org.bukkit.entity.Player
import org.bukkit.event.Event

class CondIsLinked : Condition() {
    companion object {
        init {
            Skript.registerCondition(
                    CondIsLinked::class.java,
                    "%player% (is|has) linked",
                    "%player% (is|has) not linked",
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

        val resp = API.getUser(player.uniqueId)

        if(resp === null) {
            // If it's "has not linked", this will return true.
            // If it's "has linked", this will return false
            return pattern != 0
        }

        // If it's "has linked", this will return true
        // If it's "has not linked", this will return false.
        return pattern == 0
    }

    override fun toString(e: Event?, debug: Boolean): String {
        return "if ${playerExpr.getSingle(e)} is linked"
    }
}