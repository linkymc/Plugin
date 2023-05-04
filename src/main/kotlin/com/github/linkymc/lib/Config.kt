package com.github.linkymc.lib

import com.github.linkymc.Linky
import com.moandjiezana.toml.Toml
import java.io.File

fun getMessage(key: String): String? {
    val path = File(Linky.instance.dataFolder, "messages.toml")
    val toml: Toml = Toml().read(path)
    return toml.getString(key) ?: null
}
