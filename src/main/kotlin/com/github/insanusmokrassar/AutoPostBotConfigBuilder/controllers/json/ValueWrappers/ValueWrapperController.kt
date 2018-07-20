package com.github.insanusmokrassar.AutoPostBotConfigBuilder.controllers.json.ValueWrappers

interface ValueWrapperController<T> {
    var outValue: T
    var onRemoveCallback: () -> Unit
}