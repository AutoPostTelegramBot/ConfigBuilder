package com.github.insanusmokrassar.AutoPostBotConfigBuilder

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.stage.Stage

class ConfigBuilderApplication : Application() {
    override fun start(stage: Stage) {
        stage.scene = Scene(
            FXMLLoader.load(this::class.java.classLoader.getResource("ConfigBuilder.fxml"))
        )
        stage.show()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            launch(ConfigBuilderApplication::class.java)
        }
    }
}