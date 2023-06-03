package com.github.linkymc.lib

import com.github.linkymc.Linky
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import java.util.UUID

object API {
    @Serializable
    data class SessionInformation(
        val id: String,
        val createdAt: String,
        val discordId: String,
        val username: String,
        val status: String
    )

    @Serializable
    data class SessionUpdateResponse(
        val success: Boolean,
        val status: String
    )

    @Serializable
    data class UserInfoResponse(
        val success: Boolean,
        val isInGuild: Boolean,
        val id: String,
        val createdAt: String,
        val discordId: String,
        val username: String,
        val uuid: String,
        val status: String
    )

    private data class SessionStatusUpdate(val status: String)
    private val apiURL = "http://127.0.0.1:3000/api"

    private fun Response.getText(): String {
        return this.body?.string() ?: ""
    }

    private fun String.toJSONBody(): RequestBody {
        return this.toRequestBody("application/json; charset=utf-8".toMediaType())
    }

    fun getUser(uuid: UUID): UserInfoResponse? {
        println("[API] Getting user with UUID of $uuid")
        val apiKey = Linky.instance.config.getString("token")

        val client = OkHttpClient()
        val request = Request.Builder()
            .url("$apiURL/users/$uuid")
            .header("Authorization", "Bearer $apiKey")
            .build()

        val response = client.newCall(request).execute()

        if (!response.isSuccessful) {
            println(response.getText())
            return null
        }

        println("[API] Request finished.")

        return Json.decodeFromString(response.getText())
    }

    fun updateSessionStatus(id: String, status: String): SessionUpdateResponse? {
        val apiKey = Linky.instance.config.getString("token")

        val client = OkHttpClient()

        // if someone's got a better / cleaner way to do this, make a PR :)
        val body = """
           {
              "status": "$status"
           }
        """.trimIndent().toJSONBody()

        val request = Request.Builder()
            .url("$apiURL/sessions/$id")
            .header("Authorization", "Bearer $apiKey")
            .patch(body)
            .build()

        val response = client.newCall(request).execute()

        if (!response.isSuccessful) {
            println(response.getText())
            return null
        }

        return Json.decodeFromString(response.getText())
    }
}