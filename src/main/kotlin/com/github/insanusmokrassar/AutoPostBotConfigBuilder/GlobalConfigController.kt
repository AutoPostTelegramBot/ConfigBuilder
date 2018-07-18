package com.github.insanusmokrassar.AutoPostBotConfigBuilder

import javafx.event.EventHandler
import javafx.fxml.FXML
import javafx.scene.control.*
import java.net.URL
import java.util.*

class GlobalConfigController {
    @FXML private lateinit var openButton: MenuItem
    @FXML private lateinit var saveButton: MenuItem

    @FXML private lateinit var sourceChatId: TextField
    @FXML private lateinit var targetChatId: TextField
    @FXML private lateinit var logsChatId: TextField
    @FXML private lateinit var botToken: TextField

    @FXML private lateinit var dbSwitch: ToggleButton
    @FXML private lateinit var proxySwitch: ToggleButton

    @FXML private lateinit var dbUrl: TextField
    @FXML private lateinit var dbDriver: TextField
    @FXML private lateinit var dbUsername: TextField
    @FXML private lateinit var dbPassword: PasswordField

    @FXML private lateinit var proxyHost: TextField
    @FXML private lateinit var proxyPort: TextField
    @FXML private lateinit var proxyUsername: TextField
    @FXML private lateinit var proxyPassword: TextField

    @FXML
    private fun initialize() {
        openButton.onAction = EventHandler {
            println(it)
        }
        println("Initialized")
    }
}