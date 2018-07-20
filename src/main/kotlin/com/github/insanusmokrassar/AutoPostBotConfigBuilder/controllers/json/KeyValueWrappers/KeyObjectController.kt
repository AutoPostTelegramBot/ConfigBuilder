package com.github.insanusmokrassar.AutoPostBotConfigBuilder.controllers.json.KeyValueWrappers

import com.github.insanusmokrassar.AutoPostBotConfigBuilder.controllers.json.ValueObjectController
import com.github.insanusmokrassar.IObjectK.interfaces.IObject
import javafx.fxml.FXML
import javafx.scene.control.TextField

class KeyObjectController : KeyValueWrapperController<IObject<Any>> {

    @FXML private lateinit var key: TextField
    @FXML private lateinit var valueObjectController: ValueObjectController

    override val outKey: String
        get() = key.text
    override val outValue: IObject<Any>
        get() = valueObjectController.value
    override var onRemoveCallback: () -> Unit = {}

    @FXML
    private fun remove() = onRemoveCallback()
}