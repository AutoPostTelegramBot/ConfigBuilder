package com.github.insanusmokrassar.AutoPostBotConfigBuilder.controllers.json.KeyValueWrappers

import com.github.insanusmokrassar.AutoPostBotConfigBuilder.controllers.json.ValueObjectController
import com.github.insanusmokrassar.IObjectK.interfaces.IObject
import javafx.fxml.FXML
import javafx.scene.control.TextField

class KeyObjectController : KeyValueWrapperController<IObject<Any>> {

    @FXML private lateinit var key: TextField
    @FXML private lateinit var valueController: ValueObjectController

    override var outKey: String
        get() = key.text
        set(value) {
            key.text = value
        }
    override var outValue: IObject<Any>
        get() = valueController.value
        set(value) {
            valueController.value = value
        }
    override var onRemoveCallback: () -> Unit = {}

    @FXML
    private fun remove() = onRemoveCallback()
}