package com.github.insanusmokrassar.AutoPostBotConfigBuilder.TornadoFX.ViewModels

import tornadofx.*

class DBConfigViewModel : ViewModel() {
    val enabledProperty = BindingAwareSimpleBooleanProperty(this, "dbConfigEnabled")

    val urlProperty = BindingAwareSimpleStringProperty(this, "DB Url")
    val driverProperty = BindingAwareSimpleStringProperty(this, "Driver")
    val usernameProperty = BindingAwareSimpleStringProperty(this, "Username")
    val passwordProperty = BindingAwareSimpleStringProperty(this, "Password")

    var url: String by urlProperty
    var driver: String by driverProperty
    var username: String by usernameProperty
    var password: String by passwordProperty

    init {
        url = ""
        driver = "org.h2.Driver"
        username = ""
        password = ""
    }
}