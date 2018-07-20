package com.github.insanusmokrassar.AutoPostBotConfigBuilder.controllers.json.ValueWrappers

import com.github.insanusmokrassar.AutoPostBotConfigBuilder.controllers.json.KeyValueWrappers.KeyValueWrapperController
import com.github.insanusmokrassar.AutoPostBotConfigBuilder.controllers.json.ValueObjectController
import com.github.insanusmokrassar.IObjectK.interfaces.IObject
import javafx.fxml.FXML
import javafx.scene.control.TextField

class ObjectController : ValueWrapperController<IObject<Any>> {
    @FXML private lateinit var valueObjectController: ValueObjectController

    override val outValue: IObject<Any>
        get() = valueObjectController.value
    override var onRemoveCallback: () -> Unit = {}

    @FXML
    private fun remove() = onRemoveCallback()
}