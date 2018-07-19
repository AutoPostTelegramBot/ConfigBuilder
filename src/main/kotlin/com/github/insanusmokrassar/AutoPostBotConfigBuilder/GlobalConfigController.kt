package com.github.insanusmokrassar.AutoPostBotConfigBuilder

import com.github.insanusmokrassar.AutoPostBotConfigBuilder.models.*
import com.github.insanusmokrassar.IObjectKRealisations.toJson
import com.github.insanusmokrassar.IObjectKRealisations.toObject
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.control.*
import javafx.scene.layout.VBox
import javafx.stage.FileChooser
import javafx.stage.Stage
import java.io.File
import java.net.URL
import java.util.*

class GlobalConfigController : Initializable {

    private var file: File? = null

    @FXML private lateinit var sourceChatId: TextField
    private val sourceChatIdValidator: TextFieldValidator<Long> by lazy {
        ChatIdValidator(sourceChatId)
    }

    @FXML private lateinit var targetChatId: TextField
    private val targetChatIdValidator: TextFieldValidator<Long> by lazy {
        ChatIdValidator(targetChatId)
    }

    @FXML private lateinit var logsChatId: TextField
    private val logsChatIdValidator: TextFieldValidator<Long> by lazy {
        ChatIdValidator(logsChatId)
    }

    @FXML private lateinit var botToken: TextField
    private val botTokenValidator: BotTokenValidator by lazy {
        BotTokenValidator(botToken)
    }

    @FXML private lateinit var dbContainer: VBox
    @FXML private lateinit var dbSwitch: ToggleButton
    @FXML private lateinit var proxySwitch: ToggleButton

    @FXML private lateinit var dbUrl: TextField
    private val databaseUrlValidator: TextValidator by lazy {
        DatabaseUrlValidator(dbUrl)
    }

    @FXML private lateinit var dbDriver: TextField
    private val dbDriverValidator: TextValidator by lazy {
        PackageValidator(dbDriver)
    }

    @FXML private lateinit var dbUsername: TextField
    @FXML private lateinit var dbPassword: PasswordField

    @FXML private lateinit var proxyContainer: VBox
    @FXML private lateinit var proxyHost: TextField
    private val proxyUrlValidator: ProxyUrlValidator by lazy {
        ProxyUrlValidator(proxyHost)
    }

    @FXML private lateinit var proxyPort: TextField
    private val portValidator: PortValidator by lazy {
        PortValidator(proxyPort)
    }

    @FXML private lateinit var proxyUsername: TextField
    @FXML private lateinit var proxyPassword: TextField

    lateinit var stage: Stage

    private var config: TempConfig
        get() {
            return TempConfig(
                sourceChatIdValidator.outputOrNull,
                targetChatIdValidator.outputOrNull,
                botTokenValidator.outputOrNull,
                logsChatIdValidator.outputOrNull,
                databaseConfig = if (dbSwitch.isSelected) {
                    DatabaseConfig(
                        databaseUrlValidator.outputOrNull,
                        dbDriverValidator.outputOrNull,
                        dbUsername.notEmptyTextOrNull(),
                        dbPassword.notEmptyTextOrNull()
                    )
                } else {
                    null
                },
                proxy = if (proxySwitch.isSelected) {
                    ProxySettings(
                        proxyUrlValidator.outputOrNull,
                        portValidator.outputOrNull,
                        proxyUsername.notEmptyTextOrNull(),
                        proxyPassword.notEmptyTextOrNull()
                    )
                } else {
                    null
                }
            )
        }
        set(value) {
            value.apply {
                sourceChatIdValidator.text = sourceChatId ?.toString() ?: ""
                targetChatIdValidator.text = targetChatId ?.toString() ?: ""
                logsChatIdValidator.text = logsChatId ?.toString() ?: ""
                botTokenValidator.text = botToken ?: ""

                databaseConfig ?.apply {
                    dbSwitch.selectedProperty().value = true
                    databaseUrlValidator.text = url ?: ""
                    dbDriverValidator.text = driver ?: ""
                    dbUsername.text = username ?: ""
                    dbPassword.text = password ?: ""
                } ?:apply {
                    dbSwitch.selectedProperty().value = false
                    databaseUrlValidator.text = ""
                    dbDriverValidator.text = ""
                    dbUsername.text = ""
                    dbPassword.text = ""
                }

                proxy ?.apply {
                    proxySwitch.selectedProperty().value = true
                    proxyUrlValidator.text = host ?: ""
                    portValidator.text = port ?.toString() ?: ""
                    proxyUsername.text = username ?: ""
                    proxyPassword.text = password ?: ""
                } ?:apply {
                    proxySwitch.selectedProperty().value = false
                    proxyUrlValidator.text = ""
                    portValidator.text = ""
                    proxyUsername.text = ""
                    proxyPassword.text = ""
                }

            }
        }

    override fun initialize(location: URL?, resources: ResourceBundle?) {
        println("Initialized")

        TogglButtonEnabler(
            dbSwitch,
            dbContainer.disableProperty()
        )

        TogglButtonEnabler(
            proxySwitch,
            proxyContainer.disableProperty()
        )

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