package com.github.linkymc;

import com.github.linkymc.lib.getMessage
import com.github.linkymc.lib.mm
import com.pusher.client.Pusher
import com.pusher.client.PusherOptions
import me.aroze.arozeutils.minecraft.FancyPlugin
import me.honkling.commando.CommandManager
import java.io.File
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import org.bukkit.Bukkit


class Linky : FancyPlugin() {
    companion object {
        lateinit var instance: Linky
    }

    @Serializable
    data class LinkRequest(
        val username: String
    )

    override fun onEnable() {
        saveDefaultConfig()

        instance = this

        // Creates messages.toml
        val messages = File(dataFolder, "messages.toml")
        if (!messages.exists()) {
            messages.parentFile.mkdirs()
            saveResource("messages.toml", false)
        }

        val commandManager = CommandManager(instance)
        commandManager.registerCommands("com.github.linkymc.commands")

        val options = PusherOptions()
        options.setCluster("us2")

        val pusher = Pusher("059a2227841692b516e0", options)
        pusher.connect()

        val channel = pusher.subscribe(config.getString("token"))

        channel.bind("link-request") { event ->
            val response: LinkRequest = Json.decodeFromString(event.data)

            logger.info("Received event with data: $response")

            val player = Bukkit.getPlayer(response.username)

            if(player === null) {
                logger.info("Received link request for ${response.username}, however, they were not online.")
                return@bind
            }

            val linkMsg = getMessage("linkRequest")

            if(linkMsg === null) {
                logger.info("Link request message is null! Please provide one.")
                return@bind
            }

            player.sendMessage(linkMsg.mm())
        }
    }
}