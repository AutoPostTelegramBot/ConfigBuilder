package com.github.insanusmokrassar.AutoPostBotConfigBuilder.controllers.json

import com.github.insanusmokrassar.AutoPostBotConfigBuilder.controllers.json.ValueWrappers.ValueWrapperController
import com.github.insanusmokrassar.AutoPostBotConfigBuilder.utils.loadFXML
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.Node
import javafx.scene.layout.VBox
import java.net.URL
import java.util.*

class ValueArrayController : Initializable, ValueController<List<Any>> {
    @FXML
    private lateinit var jsonButtonsController: JSONButtonsController
    @FXML
    private lateinit var jsonArrayRoot: VBox

    private val pairs = mutableListOf<ValueWrapperController<out Any>>()

    override val value: List<Any>
        get() = pairs.map {
            it.outValue
        }

    override fun initialize(location: URL?, resources: ResourceBundle?) {
        jsonButtonsController.onNewObject = {
            addValue("json/value/Object.fxml")
        }
        jsonButtonsController.onNewValue = {
            addValue("json/value/Value.fxml")
        }
        jsonButtonsController.onNewArray = {
            addValue("json/value/Array.fxml")
        }
    }

    private fun addValue(fxmlPath: String) {
        val loader = loadFXML(fxmlPath)
        val node = loader.load<Node>()
        jsonArrayRoot.children.add(
            node
        )
        (loader.getController() as? ValueWrapperController<out Any>) ?.also {
            pairs.add(it)
            it.onRemoveCallback = {
                jsonArrayRoot.children.remove(node)
                pairs.remove(it)
            }
        } ?:also {
            jsonArrayRoot.children.remove(node)
        }
    }
}