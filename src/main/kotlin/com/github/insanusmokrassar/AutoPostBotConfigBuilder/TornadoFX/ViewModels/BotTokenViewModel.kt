package com.github.insanusmokrassar.AutoPostBotConfigBuilder.TornadoFX.ViewModels

import tornadofx.*

class BotTokenViewModel : ViewModel() {
    val botTokenProperty = BindingAwareSimpleStringProperty(this, "Bot token")
    var botToken: String by botTokenProperty
}