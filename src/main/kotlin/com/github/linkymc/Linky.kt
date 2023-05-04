package com.github.linkymc;

import com.pusher.client.Pusher
import com.pusher.client.PusherOptions
import me.aroze.arozeutils.minecraft.FancyPlugin
import me.honkling.commando.CommandManager
import java.io.File
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json


class Linky : FancyPlugin() {
    companion object {
        lateinit var instance: Linky
    }

    @Serializable
    data class SuccessfulLinkResponse(
        val event: String,
        val data: String,
        val channel: String,
    )

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

        val options = PusherOptions()
        options.setCluster("us2")

        val pusher = Pusher("059a2227841692b516e0", options)
        pusher.connect()

        val channel = pusher.subscribe(config.getString("token"))
        channel.bind("successful-verify") { event ->
            val response: SuccessfulLinkResponse = Json.decodeFromString(event.data)

            logger.info("Received event with data: $response")
        }
    }
}