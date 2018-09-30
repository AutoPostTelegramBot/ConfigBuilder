package com.github.insanusmokrassar.AutoPostBotConfigBuilder.TornadoFX.views

import com.github.insanusmokrassar.AutoPostBotConfigBuilder.botTokenValidator
import com.github.insanusmokrassar.AutoPostBotConfigBuilder.TornadoFX.ViewModels.BotTokenViewModel
import javafx.geometry.Pos
import javafx.scene.Parent
import javafx.scene.layout.Priority
import tornadofx.*

private const val botTokenPrompt = "123456789:ABCDEFGHIGKLMNOPQRSTUVWXYZabcdefghi"

class BotTokenView(
    botTokenViewModel: BotTokenViewModel
) : View() {
    override val root: Parent = hbox (
        alignment = Pos.CENTER
    ) {
        label("Bot token")
        textfield(botTokenViewModel.botTokenProperty) {
            text = ""
            promptText = botTokenPrompt
            hgrow = Priority.ALWAYS
            validator(validator = botTokenValidator)
        }
    }
}