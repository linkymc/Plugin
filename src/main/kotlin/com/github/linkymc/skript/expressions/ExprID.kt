package com.github.linkymc.skript.expressions

import ch.njol.skript.Skript
import ch.njol.skript.lang.Expression
import ch.njol.skript.lang.ExpressionType
import ch.njol.skript.lang.SkriptParser
import ch.njol.skript.lang.util.SimpleExpression
import ch.njol.util.Kleenean
import org.bukkit.entity.Player
import org.bukkit.event.Event

class ExprID : SimpleExpression<String>() {
    companion object {
        init {
            Skript.registerExpression(
                ExprID::class.java,
                String::class.java,
                ExpressionType.SIMPLE,
                "discord id of %player%"
            )
        }
    }

    lateinit var playerExpr: Expression<Player>

    override fun init(
        exprs: Array<out Expression<*>>?,
        matchedPattern: Int,
        isDelayed: Kleenean?,
        parseResult: SkriptParser.ParseResult?
    ): Boolean {
        playerExpr = exprs!![0] as Expression<Player>
        return true
    }

    override fun get(e: Event): Array<out String?> {
       return arrayOf("000000000000000")
    }

    override fun getReturnType(): Class<String> {
        return String::class.java
    }

    override fun isSingle(): Boolean {
        return true
    }

    override fun toString(e: Event?, debug: Boolean): String {
        return "discord tag of ${playerExpr.getSingle(e)}"
    }
}