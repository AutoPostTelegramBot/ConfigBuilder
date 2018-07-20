package com.github.insanusmokrassar.AutoPostBotConfigBuilder.controllers.json.ValueWrappers

import com.github.insanusmokrassar.AutoPostBotConfigBuilder.controllers.json.KeyValueWrappers.KeyValueWrapperController
import com.github.insanusmokrassar.AutoPostBotConfigBuilder.controllers.json.ValueArrayController
import javafx.fxml.FXML
import javafx.scene.control.TextField

class ArrayController : ValueWrapperController<List<Any>> {
    @FXML private lateinit var valueController: ValueArrayController

    override val outValue: List<Any>
        get() = valueController.value
    override var onRemoveCallback: () -> Unit = {}

    @FXML
    private fun remove() = onRemoveCallback()
}