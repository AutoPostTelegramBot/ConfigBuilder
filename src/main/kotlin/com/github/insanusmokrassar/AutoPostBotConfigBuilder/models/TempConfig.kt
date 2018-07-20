package com.github.insanusmokrassar.AutoPostBotConfigBuilder.models

private val databaseUrlRegex = Regex("^jdbc:[^:]+:.*")
private val databaseDriverClassnameRegex = Regex("[\\w\\d]*(\\.[\\w\\d]*)*")

class TempConfig (
    val targetChatId: Long? = null,
    val sourceChatId: Long? = null,
    val botToken: String? = null,
    val logsChatId: Long? = null,
    val databaseConfig: DatabaseConfig? = null,
    val proxy: ProxySettings? = null,
    val plugins: List<PluginConfig> = emptyList(),
    val debug: Boolean = false
) {
    val asConfig: Config?
        get() {
            return Config(
                targetChatId ?: return null,
                sourceChatId ?: return null,
                botToken ?: return null,
                logsChatId ?: sourceChatId,
                databaseConfig,
                proxy,
                plugins,
                debug
            )
        }
}
