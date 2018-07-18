package com.github.insanusmokrassar.AutoPostBotConfigBuilder

import com.github.insanusmokrassar.AutoPostBotConfigBuilder.models.Config
import com.github.insanusmokrassar.AutoPostBotConfigBuilder.models.DatabaseConfig
import com.github.insanusmokrassar.AutoPostBotConfigBuilder.models.ProxySettings
import com.github.insanusmokrassar.IObjectKRealisations.toJson
import com.github.insanusmokrassar.IObjectKRealisations.toObject
import javafx.event.EventHandler
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.control.*
import javafx.stage.FileChooser
import javafx.stage.Stage
import java.net.URL
import java.util.*

class GlobalConfigController : Initializable {

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

    lateinit var stage: Stage

    private var config: Config
        get() {
            return Config(
                    sourceChatId.text.toLong(),
                    targetChatId.text.toLong(),
                    botToken.text,
                    logsChatId.text.toLong(),
                    databaseConfig = DatabaseConfig(
                            dbUrl.text,
                            dbDriver.text,
                            dbUsername.text,
                            dbPassword.text
                    ),
                    proxy = ProxySettings(
                            proxyHost.text,
                            proxyPort.text.toInt(),
                            proxyUsername.text,
                            proxyPassword.text
                    )

            )
        }
        set(value) {
            sourceChatId.text = value.sourceChatId.toString()
            targetChatId.text = value.targetChatId.toString()
            logsChatId.text = value.logsChatId.toString()
            botToken.text = value.botToken

            dbUrl.text = value.databaseConfig?.url
            dbDriver.text = value.databaseConfig?.driver
            dbUsername.text = value.databaseConfig?.username
            dbPassword.text = value.databaseConfig?.password

            proxyHost.text = value.proxy?.host
            proxyPort.text = value.proxy?.port.toString()
            proxyUsername.text = value.proxy?.username
            proxyPassword.text = value.proxy?.password
        }

    override fun initialize(location: URL?, resources: ResourceBundle?) {
        openButton.onAction = EventHandler {
            val filechooser = FileChooser()
            filechooser.title = "Open configuration file"
            val file = filechooser.showOpenDialog(stage)
            val content = file?.readText()
            config = if(content != null) {
                content.toObject(Config::class.java)
            } else throw Exception()
        }

        saveButton.onAction = EventHandler {
            val c = config
            val fileChooser = FileChooser()
            fileChooser.title = "Save configuration file"
            val file = fileChooser.showSaveDialog(stage)
            file.writeText(c.toJson())
        }
        println("Initialized")
    }

    private fun updateView() {
        sourceChatId.text = config.sourceChatId.toString()
        targetChatId.text = config.targetChatId.toString()
        logsChatId.text = config.logsChatId.toString()
        botToken.text = config.botToken

        dbUrl.text = config.databaseConfig?.url
        dbDriver.text = config.databaseConfig?.driver
        dbUsername.text = config.databaseConfig?.username
        dbPassword.text = config.databaseConfig?.password

        proxyHost.text = config.proxy?.host
        proxyPort.text = config.proxy?.port.toString()
        proxyUsername.text = config.proxy?.username
        proxyPassword.text=  config.proxy?.password
    }

    private fun viewToConfig() {
        config = Config(
                sourceChatId.text.toLong(),
                targetChatId.text.toLong(),
                botToken.text,
                logsChatId.text.toLong(),
                databaseConfig = DatabaseConfig(
                        dbUrl.text,
                        dbDriver.text,
                        dbUsername.text,
                        dbPassword.text
                ),
                proxy = ProxySettings(
                        proxyHost.text,
                        proxyPort.text.toInt(),
                        proxyUsername.text,
                        proxyPassword.text
                )

        )
    }

}