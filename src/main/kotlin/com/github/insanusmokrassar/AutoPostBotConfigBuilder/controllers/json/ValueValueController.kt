package com.github.insanusmokrassar.AutoPostBotConfigBuilder.controllers.json

import com.github.insanusmokrassar.AutoPostBotConfigBuilder.controllers.json.ValueWrappers.ValueWrapperController
import com.github.insanusmokrassar.AutoPostBotConfigBuilder.utils.loadFXML
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.Node
import javafx.scene.control.TextField
import javafx.scene.layout.VBox
import java.net.URL
import java.util.*

class ValueValueController : ValueController<String> {
    @FXML private lateinit var valueField: TextField

    override var value: String
        get() = valueField.text
        set(value) {
            valueField.text = value
        }
}