package com.github.insanusmokrassar.AutoPostBotConfigBuilder.controllers

import com.github.insanusmokrassar.AutoPostBotConfigBuilder.controllers.json.ValueObjectController
import com.github.insanusmokrassar.AutoPostBotConfigBuilder.controllers.json.ValueWrappers.ObjectController
import com.github.insanusmokrassar.AutoPostBotConfigBuilder.models.PluginConfig
import com.github.insanusmokrassar.IObjectK.interfaces.IObject
import com.github.insanusmokrassar.IObjectK.realisations.SimpleIObject
import com.github.insanusmokrassar.IObjectKRealisations.toObject
import javafx.fxml.*
import javafx.scene.control.TextField

class PluginController {
    @FXML private lateinit var valueObjectController: ValueObjectController
    @FXML private lateinit var classname: TextField

    var plugin: PluginConfig
        get() = PluginConfig(
            classname.text,
            valueObjectController.value.run {
                if (keys().isEmpty()) {
                    null
                } else {
                    this
                }
            }
        )
        set(value) {
            classname.text = value.classname
            valueObjectController.value = value.params ?: SimpleIObject()
        }
}