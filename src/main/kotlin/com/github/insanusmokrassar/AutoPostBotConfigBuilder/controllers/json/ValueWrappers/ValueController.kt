package com.github.insanusmokrassar.AutoPostBotConfigBuilder.controllers.json.ValueWrappers

import com.github.insanusmokrassar.AutoPostBotConfigBuilder.controllers.json.KeyValueWrappers.KeyValueWrapperController
import com.github.insanusmokrassar.AutoPostBotConfigBuilder.controllers.json.ValueValueController
import javafx.fxml.FXML
import javafx.scene.control.TextField

class ValueController : ValueWrapperController<String> {
    @FXML private lateinit var valueValueController: ValueValueController

    override val outValue: String
        get() = valueValueController.value
    override var onRemoveCallback: () -> Unit = {}

    @FXML
    private fun remove() = onRemoveCallback()
}