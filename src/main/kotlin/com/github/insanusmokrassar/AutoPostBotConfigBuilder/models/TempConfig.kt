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
            return if (isValid) {
                Config(
                    targetChatId ?: return null,
                    sourceChatId ?: return null,
                    botToken ?: return null,
                    logsChatId ?: sourceChatId,
                    databaseConfig,
                    proxy,
                    plugins,
                    debug
                )
            } else {
                null
            }
        }

    val isValid: Boolean
        get() {
            return (sourceChatId != null && targetChatId != null && botToken != null) && let {
                databaseConfig ?.let {
                    databaseUrlRegex.matches(it.url) && databaseDriverClassnameRegex.matches(it.driver)
                } ?: true
            } && let {
                proxy ?.let {
                    it.port in 1 .. 65535
                } ?: true
            }
        }
}
