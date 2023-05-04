package com.github.linkymc;

import me.aroze.arozeutils.minecraft.FancyPlugin
import me.honkling.commando.CommandManager
import java.io.File

class Linky : FancyPlugin() {
    companion object {
        lateinit var instance: Linky
    }

    override fun onEnable() {
        saveDefaultConfig()

        instance = this

        // Creates messages.toml
        val messages = File(dataFolder, "book.toml")
        if (!messages.exists()) {
            messages.parentFile.mkdirs()
            saveResource("book.toml", false)
        }

        val commandManager = CommandManager(instance)
        commandManager.registerCommands("com.github.linkymc.commands")
    }
}