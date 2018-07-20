package com.github.insanusmokrassar.AutoPostBotConfigBuilder.controllers.json.KeyValueWrappers

import com.github.insanusmokrassar.AutoPostBotConfigBuilder.controllers.json.ValueArrayController
import com.github.insanusmokrassar.AutoPostBotConfigBuilder.controllers.json.ValueValueController
import javafx.fxml.FXML
import javafx.scene.control.TextField

class KeyArrayController : KeyValueWrapperController<List<Any>> {

    @FXML private lateinit var key: TextField
    @FXML private lateinit var valueController: ValueArrayController

    override val outKey: String
        get() = key.text
    override val outValue: List<Any>
        get() = valueController.value
    override var onRemoveCallback: () -> Unit = {}

    @FXML
    private fun remove() = onRemoveCallback()
}