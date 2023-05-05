package com.github.linkymc;

import com.github.linkymc.ws.LinkRequest
import com.pusher.client.Pusher
import com.pusher.client.PusherOptions
import com.pusher.client.channel.Channel
import me.aroze.arozeutils.minecraft.FancyPlugin
import me.honkling.commando.CommandManager
import java.io.File

class Linky : FancyPlugin() {
    companion object {
        lateinit var instance: Linky
        lateinit var channel: Channel
    }

    override fun onEnable() {
        saveDefaultConfig()

        instance = this

        // Creates messages.toml
        val messages = File(dataFolder, "messages.toml")
        if (!messages.exists()) {
            messages.parentFile.mkdirs()
            saveResource("messages.toml", false)
        }

        // Sets up the command handler
        val commandManager = CommandManager(instance)
        commandManager.registerCommands("com.github.linkymc.commands")

        // Connects to the socket channel
        val options = PusherOptions()
        options.setCluster("us2")

        // This API key isn't sensitive and is
        // supposed to be on the client
        val pusher = Pusher("059a2227841692b516e0", options)
        pusher.connect()

        channel = pusher.subscribe(config.getString("token"))

        // Registers socket events
        LinkRequest
    }
}