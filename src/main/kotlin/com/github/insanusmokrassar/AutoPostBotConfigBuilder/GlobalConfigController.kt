package com.github.insanusmokrassar.AutoPostBotConfigBuilder

import com.github.insanusmokrassar.AutoPostBotConfigBuilder.models.*
import com.github.insanusmokrassar.IObjectKRealisations.toJson
import com.github.insanusmokrassar.IObjectKRealisations.toObject
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.control.*
import javafx.stage.FileChooser
import javafx.stage.Stage
import java.io.File
import java.net.URL
import java.util.*

class GlobalConfigController : Initializable {

    private var file: File? = null

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

    private var config: TempConfig
        get() {
            return TempConfig(
                sourceChatId.asLongOrNull(),
                targetChatId.asLongOrNull(),
                botToken.text.let {
                    if (it.isEmpty()) {
                        null
                    } else {
                        it
                    }
                },
                logsChatId.asLongOrNull(),
                databaseConfig = if (dbSwitch.isSelected) {
                    DatabaseConfig(
                        dbUrl.text,
                        dbDriver.text,
                        dbUsername.notEmptyTextOrNull(),
                        dbPassword.notEmptyTextOrNull()
                    )
                } else {
                    null
                },
                proxy = if (proxySwitch.isSelected) {
                    ProxySettings(
                        proxyHost.notEmptyTextOrNull(),
                        proxyPort.asLongOrNull() ?.toInt(),
                        proxyUsername.notEmptyTextOrNull(),
                        proxyPassword.notEmptyTextOrNull()
                    )
                } else {
                    null
                }
            )
        }
        set(value) {
            sourceChatId.text = value.sourceChatId ?.toString() ?: ""
            targetChatId.text = value.targetChatId ?.toString() ?: ""
            logsChatId.text = value.logsChatId ?.toString() ?: ""
            botToken.text = value.botToken ?: ""

            dbUrl.text = value.databaseConfig ?.url ?: ""
            dbDriver.text = value.databaseConfig ?.driver ?: ""
            dbUsername.text = value.databaseConfig ?.username ?: ""
            dbPassword.text = value.databaseConfig ?.password ?: ""

            proxyHost.text = value.proxy ?.host ?: ""
            proxyPort.text = value.proxy ?.port ?.toString() ?: ""
            proxyUsername.text = value.proxy ?.username ?: ""
            proxyPassword.text = value.proxy ?.password ?: ""
        }

    override fun initialize(location: URL?, resources: ResourceBundle?) {
        println("Initialized")
        onNew()
    }

    @FXML
    private fun onOpen() {
        val filechooser = FileChooser()
        filechooser.title = "Open configuration file"
        val file = filechooser.showOpenDialog(stage)
        val content = file?.readText()
        config = content ?.toObject(TempConfig::class.java) ?: TempConfig()
    }

    @FXML
    private fun onSave() {
        if (!config.isValid) {
            return
        }
        file ?.let {
            val c = config.asConfig ?: return
            it.writeText(c.toJson())
        } ?: onSaveAs()
    }

    @FXML
    private fun onSaveAs() {
        if (!config.isValid) {
            return
        }
        file = null
        val fileChooser = FileChooser()
        fileChooser.title = "Save configuration file"
        file = fileChooser.showSaveDialog(stage) ?: return
        onSave()
    }

    @FXML
    private fun onNew() {
        config = TempConfig()
    }
}