package com.github.insanusmokrassar.AutoPostBotConfigBuilder.controllers.json.KeyValueWrappers

import com.github.insanusmokrassar.AutoPostBotConfigBuilder.controllers.json.ValueValueController
import javafx.fxml.FXML
import javafx.scene.control.TextField

class KeyValueController : KeyValueWrapperController<String> {

    @FXML private lateinit var key: TextField
    @FXML private lateinit var valueController: ValueValueController

    override var outKey: String
        get() = key.text
        set(value) {
            key.text = value
        }
    override var outValue: String
        get() = valueController.value
        set(value) {
            valueController.value = value
        }
    override var onRemoveCallback: () -> Unit = {}

    @FXML
    private fun remove() = onRemoveCallback()
}