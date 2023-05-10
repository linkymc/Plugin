package com.github.linkymc.lib

import com.github.linkymc.Linky
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody

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

    private data class SessionStatusUpdate(val status: String)

    private fun Response.getText(): String {
        return this.body?.string() ?: ""
    }

    private fun String.toJSONBody(): RequestBody {
        return this.toRequestBody("application/json; charset=utf-8".toMediaType())
    }

    fun getSession(id: String): SessionInformation? {
        val apiKey = Linky.instance.config.getString("token")

        val client = OkHttpClient()
        val request = Request.Builder()
            .url("http://192.168.254.68:3000/api/sessions/$id")
            .header("Authorization", "Bearer $apiKey")
            .build()

        val response = client.newCall(request).execute()

        if (!response.isSuccessful) {
            println(response.getText())
            return null
        }

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
            .url("http://192.168.254.68:3000/api/sessions/$id")
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