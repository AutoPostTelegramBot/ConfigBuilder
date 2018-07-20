package com.github.insanusmokrassar.AutoPostBotConfigBuilder.controllers.json.KeyValueWrappers

interface KeyValueWrapperController<T> {
    var outKey: String
    var outValue: T
    var onRemoveCallback: () -> Unit
}