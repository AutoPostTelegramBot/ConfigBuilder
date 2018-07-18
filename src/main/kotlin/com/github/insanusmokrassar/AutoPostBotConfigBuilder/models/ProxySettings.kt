package com.github.insanusmokrassar.AutoPostBotConfigBuilder.models

data class ProxySettings(
    val host: String = "localhost",
    val port: Int = 1080,
    val username: String? = null,
    val password: String? = null
)
