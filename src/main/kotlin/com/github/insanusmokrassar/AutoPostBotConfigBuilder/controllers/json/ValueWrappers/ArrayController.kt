package com.github.insanusmokrassar.AutoPostBotConfigBuilder.controllers.json.ValueWrappers

import com.github.insanusmokrassar.AutoPostBotConfigBuilder.controllers.json.ValueArrayController
import javafx.fxml.FXML

class ArrayController : ValueWrapperController<List<Any>> {
    @FXML private lateinit var valueController: ValueArrayController

    override var outValue: List<Any>
        get() = valueController.value
        set(value) {
            valueController.value = value
        }
    override var onRemoveCallback: () -> Unit = {}

    @FXML
    private fun remove() = onRemoveCallback()
}