package com.github.insanusmokrassar.AutoPostBotConfigBuilder.controllers.json

import com.github.insanusmokrassar.AutoPostBotConfigBuilder.controllers.json.KeyValueWrappers.KeyValueWrapperController
import com.github.insanusmokrassar.AutoPostBotConfigBuilder.utils.loadFXML
import com.github.insanusmokrassar.IObjectK.interfaces.IObject
import com.github.insanusmokrassar.IObjectK.realisations.SimpleIObject
import javafx.fxml.*
import javafx.scene.Node
import javafx.scene.layout.VBox
import java.net.URL
import java.util.*

class ValueObjectController : Initializable, ValueController<IObject<Any>> {
    @FXML private lateinit var jsonButtonsController: JSONButtonsController
    @FXML private lateinit var jsonObjectRoot: VBox

    private val pairs: MutableList<KeyValueWrapperController<out Any>> = mutableListOf()

    override val value: IObject<Any>
        get() = SimpleIObject().apply {
            pairs.forEach {
                set(it.outKey, it.outValue)
            }
        }

    override fun initialize(location: URL?, resources: ResourceBundle?) {
        jsonButtonsController.onNewObject = {
            addValue("json/KeyValue/KeyObject.fxml")
        }
        jsonButtonsController.onNewValue = {
            addValue("json/KeyValue/KeyValue.fxml")
        }
        jsonButtonsController.onNewArray = {
            addValue("json/KeyValue/KeyArray.fxml")
        }
    }

    private fun addValue(fxmlPath: String) {
        val loader = loadFXML(fxmlPath)
        val node = loader.load<Node>()
        jsonObjectRoot.children.add(
            node
        )
        (loader.getController() as? KeyValueWrapperController<out Any>) ?.also {
            pairs.add(it)
            it.onRemoveCallback = {
                jsonObjectRoot.children.remove(node)
                pairs.remove(it)
            }
        } ?:also {
            jsonObjectRoot.children.remove(node)
        }
    }
}