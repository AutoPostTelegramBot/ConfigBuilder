package com.github.insanusmokrassar.AutoPostBotConfigBuilder

import javafx.event.EventHandler
import javafx.fxml.FXML
import javafx.scene.control.MenuItem
import javafx.scene.control.TextField
import javafx.scene.control.ToggleButton

class GlobalConfigController {
    @FXML val openButton: MenuItem
    @FXML val saveButton: MenuItem

    @FXML val sourceChatId: TextField
    @FXML val targetChatId: TextField
    @FXML val logsChatId: TextField
    @FXML val botToken: TextField

    @FXML val dbSwitch: ToggleButton
    @FXML val proxySwitch: ToggleButton

    @FXML val dbUrl: TextField
    @FXML val dbDriver: TextField
    @FXML val dbUsername: TextField
    @FXML val dbPassword: TextField

    @FXML val proxyHost: TextField
    @FXML val proxyPort: TextField
    @FXML val proxyUsername: TextField
    @FXML val proxyPassword: TextField

    @FXML
    private fun initialize() {
        openButton.onAction = 
    }
}