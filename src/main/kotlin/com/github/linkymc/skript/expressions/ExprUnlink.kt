package com.github.linkymc.skript.expressions

import ch.njol.skript.Skript
import ch.njol.skript.lang.Expression
import ch.njol.skript.lang.ExpressionType
import ch.njol.skript.lang.SkriptParser
import ch.njol.skript.lang.util.SimpleExpression
import ch.njol.util.Kleenean
import com.github.linkymc.lib.API
import org.bukkit.entity.Player
import org.bukkit.event.Event

class ExprUnlink : SimpleExpression<Void>() {
    companion object {
        init {
            Skript.registerExpression(
                    ExprUnlink::class.java,
                    Void::class.java,
                    ExpressionType.SIMPLE,
                    "unlink discord of %player%"
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

    override fun get(e: Event): Array<out Void?> {
        val player = playerExpr.getSingle(e)!!

        API.unlinkUser(player.uniqueId)

        return arrayOf()
    }

    override fun getReturnType(): Class<Void> {
        return Void::class.java
    }

    override fun isSingle(): Boolean {
        return true
    }

    override fun toString(e: Event?, debug: Boolean): String {
        return "unlink discord of ${playerExpr.getSingle(e)}"
    }
}