package com.github.insanusmokrassar.AutoPostBotConfigBuilder.controllers.json

import com.github.insanusmokrassar.AutoPostBotConfigBuilder.controllers.json.ValueWrappers.ValueWrapperController
import com.github.insanusmokrassar.AutoPostBotConfigBuilder.utils.loadFXML
import com.github.insanusmokrassar.IObjectK.interfaces.IObject
import javafx.beans.Observable
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

    private val nodes = mutableListOf<Node>()

    override var value: List<Any>
        get() = pairs.map {
            it.outValue
        }
        set(value) {
            jsonArrayRoot.children.apply {
                if (size > 1) {
                    removeAll(
                        subList(1, size)
                    )
                }
            }
            pairs.clear()

            value.forEach {
                when (it) {
                    is List<*> -> addArray() ?.apply {
                        (it as? List<Any>) ?.let {
                            outValue = it
                        }
                    }
                    is IObject<*> -> addObject() ?.apply {
                        (it as? IObject<Any>) ?.let {
                            outValue = it
                        }
                    }
                    else -> addValue() ?.apply {
                        outValue = it
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

    private fun addObject(): ValueWrapperController<IObject<Any>>? {
        return addValue("json/value/Object.fxml")
    }

    private fun addArray(): ValueWrapperController<List<Any>>? {
        return addValue("json/value/Array.fxml")
    }

    private fun addValue(): ValueWrapperController<Any>? {
        return addValue("json/value/Value.fxml")
    }

    private fun <T : Any> addValue(fxmlPath: String): ValueWrapperController<T>? {
        val loader = loadFXML(fxmlPath)
        val node = loader.load<Node>()
        jsonArrayRoot.children.add(
            node
        )
        return (loader.getController() as? ValueWrapperController<T>) ?.also {
            pairs.add(it)
            it.onRemoveCallback = {
                jsonArrayRoot.children.remove(node)
                pairs.remove(it)
            }
        } ?:let {
            jsonArrayRoot.children.remove(node)
            null
        }
    }
}