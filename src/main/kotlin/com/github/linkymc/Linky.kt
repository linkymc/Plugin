package com.github.linkymc;

import me.aroze.arozeutils.minecraft.FancyPlugin
import me.honkling.commando.CommandManager

class Linky : FancyPlugin() {
    companion object {
        lateinit var instance: Linky
    }

    override fun onEnable() {
        val instance = this

        val commandManager = CommandManager(instance)
        commandManager.registerCommands("com.github.linkymc.commands")
    }
}