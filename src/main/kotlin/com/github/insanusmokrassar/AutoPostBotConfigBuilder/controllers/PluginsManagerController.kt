package com.github.insanusmokrassar.AutoPostBotConfigBuilder.controllers

import com.github.insanusmokrassar.AutoPostBotConfigBuilder.controllers.json.ValueWrappers.ObjectController
import com.github.insanusmokrassar.AutoPostBotConfigBuilder.models.PluginConfig
import com.github.insanusmokrassar.AutoPostBotConfigBuilder.utils.loadFXML
import com.github.insanusmokrassar.IObjectK.interfaces.IObject
import javafx.fxml.FXML
import javafx.scene.Node
import javafx.scene.layout.VBox

class PluginsManagerController {

    @FXML private lateinit var pluginsVBox: VBox

    private val pluginsControllers = mutableListOf<PluginWrapperController>()

    var plugins: List<PluginConfig>
        get() = pluginsControllers.map {
            it.plugin
        }
        set(value) {
            pluginsVBox.children.apply {
                clear()
            }
            value.forEach {
                addPlugin(it)
            }
        }

    @FXML
    private fun onAddPlugin() {
        addPlugin()
    }

    private fun addPlugin(from: PluginConfig? = null) {
        val loader = loadFXML("PluginWrapper.fxml")
        val node = loader.load<Node>()

        pluginsVBox.children.add(node)

        pluginsControllers.add(
            loader.getController<PluginWrapperController>().also {
                it.onRemove = {
                    pluginsControllers.remove(it)
                    pluginsVBox.children.remove(node)
                }
                from ?.also {
                    from ->
                    it.plugin = from
                }
            }
        )
    }
}
