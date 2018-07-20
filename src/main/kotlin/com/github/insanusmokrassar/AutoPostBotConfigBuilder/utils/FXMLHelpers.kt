package com.github.insanusmokrassar.AutoPostBotConfigBuilder.utils

import javafx.fxml.FXMLLoader

fun Any.loadFXML(path: String): FXMLLoader {
    return FXMLLoader(this::class.java.classLoader.getResource(path))
}