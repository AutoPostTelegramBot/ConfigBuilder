package com.github.insanusmokrassar.AutoPostBotConfigBuilder.controllers.json.ValueWrappers

interface ValueWrapperController<T> {
    val outValue: T
    var onRemoveCallback: () -> Unit
}