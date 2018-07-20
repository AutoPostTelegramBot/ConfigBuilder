package com.github.insanusmokrassar.AutoPostBotConfigBuilder.controllers.json.KeyValueWrappers

import com.github.insanusmokrassar.AutoPostBotConfigBuilder.controllers.json.ValueValueController
import javafx.fxml.FXML
import javafx.scene.control.TextField

class KeyValueController : KeyValueWrapperController<String> {

    @FXML private lateinit var key: TextField
    @FXML private lateinit var valueValueController: ValueValueController

    override val outKey: String
        get() = key.text
    override val outValue: String
        get() = valueValueController.value
    override var onRemoveCallback: () -> Unit = {}

    @FXML
    private fun remove() = onRemoveCallback()
}