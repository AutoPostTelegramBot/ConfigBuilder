package com.github.insanusmokrassar.AutoPostBotConfigBuilder.controllers

import com.github.insanusmokrassar.AutoPostBotConfigBuilder.models.PluginConfig
import com.github.insanusmokrassar.IObjectK.interfaces.IObject
import javafx.fxml.FXML

class PluginWrapperController {

    @FXML
    private lateinit var pluginController: PluginController

    var plugin: PluginConfig
        get() = pluginController.plugin
        set(value) {
            pluginController.plugin = value
        }

    var onRemove: () -> Unit = { }

    @FXML
    private fun remove() = onRemove()
}