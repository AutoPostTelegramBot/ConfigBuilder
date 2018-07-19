package com.github.insanusmokrassar.AutoPostBotConfigBuilder.models


class Config (
    val targetChatId: Long,
    val sourceChatId: Long,
    val botToken: String,
    val logsChatId: Long = sourceChatId,
    val databaseConfig: DatabaseConfig? = null,
    val proxy: ProxySettings? = null,
    val plugins: List<PluginConfig> = emptyList(),
    val debug: Boolean = false
)

data class DatabaseConfig(
    val url: String,
    val driver: String,
    val username: String?,
    val password: String?
)
