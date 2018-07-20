package com.github.insanusmokrassar.AutoPostBotConfigBuilder.controllers.json.KeyValueWrappers

interface KeyValueWrapperController<T> {
    val outKey: String
    val outValue: T
    var onRemoveCallback: () -> Unit
}