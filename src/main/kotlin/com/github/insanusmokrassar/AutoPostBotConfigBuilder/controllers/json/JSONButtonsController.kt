package com.github.insanusmokrassar.AutoPostBotConfigBuilder.controllers.json

import javafx.event.ActionEvent
import javafx.fxml.FXML

class JSONButtonsController {
    var onNewObject: (() -> Unit)? = null
    var onNewArray: (() -> Unit)? = null
    var onNewValue: (() -> Unit)? = null

    @FXML
    private fun addObject() {
        onNewObject ?. invoke()
    }

    @FXML
    private fun addArray() {
        onNewArray ?. invoke()
    }

    @FXML
    private fun addValue() {
        onNewValue ?. invoke()
    }
}