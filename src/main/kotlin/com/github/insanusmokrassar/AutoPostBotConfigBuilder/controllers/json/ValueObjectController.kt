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

    override var value: IObject<Any>
        get() = SimpleIObject().apply {
            pairs.forEach {
                set(it.outKey, it.outValue)
            }
        }
        set(value) {
            jsonObjectRoot.children.apply {
                if (size > 1) {
                    removeAll(
                        subList(1, size)
                    )
                }
            }
            pairs.clear()

            value.forEach {
                (key, value) ->
                when (value) {
                    is List<*> -> addArray() ?.apply {
                        (value as? List<Any>) ?.let {
                            outKey = key
                            outValue = it
                        }
                    }
                    is IObject<*> -> addObject() ?.apply {
                        (value as? IObject<Any>) ?.let {
                            outKey = key
                            outValue = it
                        }
                    }
                    else -> addValue() ?.apply {
                        outKey = key
                        outValue = value.toString()
                    }
                }
            }
        }

    override fun initialize(location: URL?, resources: ResourceBundle?) {
        jsonButtonsController.onNewObject = {
            addObject()
        }
        jsonButtonsController.onNewValue = {
            addValue()
        }
        jsonButtonsController.onNewArray = {
            addArray()
        }
    }

    private fun addObject(): KeyValueWrapperController<IObject<Any>>? {
        return addValue("json/KeyValue/KeyObject.fxml")
    }

    private fun addArray(): KeyValueWrapperController<List<Any>>? {
        return addValue("json/KeyValue/KeyArray.fxml")
    }

    private fun addValue(): KeyValueWrapperController<String>? {
        return addValue("json/KeyValue/KeyValue.fxml")
    }

    private fun <T: Any> addValue(fxmlPath: String): KeyValueWrapperController<T>? {
        val loader = loadFXML(fxmlPath)
        val node = loader.load<Node>()
        jsonObjectRoot.children.add(
            node
        )
        return (loader.getController() as? KeyValueWrapperController<T>) ?.also {
            pairs.add(it)
            it.onRemoveCallback = {
                jsonObjectRoot.children.remove(node)
                pairs.remove(it)
            }
        } ?:let {
            jsonObjectRoot.children.remove(node)
            null
        }
    }
}