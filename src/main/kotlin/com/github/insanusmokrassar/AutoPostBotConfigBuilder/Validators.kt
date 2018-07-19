package com.github.insanusmokrassar.AutoPostBotConfigBuilder

import javafx.scene.control.TextField

fun TextField.asLongOrNull(): Long? {
    return notEmptyTextOrNull() ?.toLongOrNull()
}

fun TextField.notEmptyTextOrNull(): String? {
    return text.let {
        if (it.isEmpty()) {
            null
        } else {
            it
        }
    }
}
