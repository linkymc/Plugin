package com.github.linkymc.ws

import com.github.linkymc.Linky
import com.github.linkymc.lib.getMessage
import com.github.linkymc.lib.mm
import com.pusher.client.channel.PusherEvent
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import me.aroze.arozeutils.kotlin.extension.replacePlaceholders
import org.bukkit.Bukkit

object LinkRequest {
    @Serializable
    data class LinkRequest(
        val username: String,
        val discordName: String,
        val sessionId: String
    )

    private val logger = Linky.instance.logger

    init {
        Linky.channel.bind("link-request") {
            linkRequest(it)
        }
        logger.info("Registered link-request event.")
    }

    private fun linkRequest(event: PusherEvent) {
        val response: LinkRequest;

        try {
            response = Json.decodeFromString(event.data)
        } catch (e: Exception) {
            throw Exception("Plugin is out of date! Some Linky data changed within the server. Please re-download :)")
        }

        logger.info("Received event with data: $response")

        val player = Bukkit.getPlayer(response.username)

        if(player === null) {
            logger.info("Received link request for ${response.username}, however, they were not online.")
            return
        }

        var linkMsg = getMessage("linkRequest")

        if(linkMsg === null) {
            logger.info("Link request message is null! Please provide one.")
            return
        }

        linkMsg = linkMsg.replacePlaceholders(hashMapOf(
            "username" to response.discordName,
            "sessionId" to response.sessionId
        ))

        player.sendMessage(linkMsg.mm())
    }
}