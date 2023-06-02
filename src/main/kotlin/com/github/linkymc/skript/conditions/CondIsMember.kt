package com.github.linkymc.skript.conditions

import ch.njol.skript.Skript
import ch.njol.skript.lang.Condition
import ch.njol.skript.lang.Expression
import ch.njol.skript.lang.SkriptParser
import ch.njol.util.Kleenean
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
        return pattern == 0
    }

    override fun toString(e: Event?, debug: Boolean): String {
        return "if ${playerExpr.getSingle(e)} is in discord"
    }
}