package com.github.insanusmokrassar.AutoPostBotConfigBuilder.controllers.json.ValueWrappers

import com.github.insanusmokrassar.AutoPostBotConfigBuilder.controllers.json.KeyValueWrappers.KeyValueWrapperController
import com.github.insanusmokrassar.AutoPostBotConfigBuilder.controllers.json.ValueValueController
import javafx.fxml.FXML
import javafx.scene.control.TextField

class ValueController : ValueWrapperController<String> {
    @FXML private lateinit var valueValueController: ValueValueController

    override var outValue: String
        get() = valueValueController.value
        set(value) {
            valueValueController.value = value
        }
    override var onRemoveCallback: () -> Unit = {}

    @FXML
    private fun remove() = onRemoveCallback()
}