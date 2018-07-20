package com.github.insanusmokrassar.AutoPostBotConfigBuilder.controllers.json.KeyValueWrappers

import com.github.insanusmokrassar.AutoPostBotConfigBuilder.controllers.json.ValueArrayController
import com.github.insanusmokrassar.AutoPostBotConfigBuilder.controllers.json.ValueValueController
import javafx.fxml.FXML
import javafx.scene.control.TextField

class KeyArrayController : KeyValueWrapperController<List<Any>> {

    @FXML private lateinit var key: TextField
    @FXML private lateinit var valueController: ValueArrayController

    override var outKey: String
        get() = key.text
        set(value) {
            key.text = value
        }
    override var outValue: List<Any>
        get() = valueController.value
        set(value) {
            valueController.value = value
        }
    override var onRemoveCallback: () -> Unit = {}

    @FXML
    private fun remove() = onRemoveCallback()
}