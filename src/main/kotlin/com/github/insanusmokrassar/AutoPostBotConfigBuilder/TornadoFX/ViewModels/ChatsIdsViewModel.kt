package com.github.insanusmokrassar.AutoPostBotConfigBuilder.TornadoFX.ViewModels

import tornadofx.*

class ChatsIdsViewModel : ViewModel() {
    val sourceChatIdProperty = BindingAwareSimpleLongProperty(this, "Source chat id")

    var sourceChatId: Long by sourceChatIdProperty

    val targetChatIdProperty = BindingAwareSimpleLongProperty(this, "Target chat id")

    var targetChatId: Long by targetChatIdProperty

    val logsChatIdProperty = BindingAwareSimpleLongProperty(this, "Target chat id")

    var logsChatId: Long? = null
        set(value) = logsChatIdProperty.set(value ?: 0L)
        get() {
            return field ?: targetChatId
        }
}