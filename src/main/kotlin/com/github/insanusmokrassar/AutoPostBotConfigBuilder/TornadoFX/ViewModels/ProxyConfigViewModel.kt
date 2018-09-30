package com.github.insanusmokrassar.AutoPostBotConfigBuilder.TornadoFX.ViewModels

import tornadofx.*

class ProxyConfigViewModel : ViewModel() {
    val enabledProperty = BindingAwareSimpleBooleanProperty(this, "proxyConfigEnabled")

    val urlProperty = BindingAwareSimpleStringProperty(this, "Proxy url")
    val portProperty = BindingAwareSimpleIntegerProperty(this, "Proxy port")
    val usernameProperty = BindingAwareSimpleStringProperty(this, "Proxy username")
    val passwordProperty = BindingAwareSimpleStringProperty(this, "Proxy password")

    var url: String by urlProperty
    var port: Int by portProperty
    var username: String by usernameProperty
    var password: String by passwordProperty

    init {
        url = "localhost"
        port = 1080
        username = ""
        password = ""
    }
}