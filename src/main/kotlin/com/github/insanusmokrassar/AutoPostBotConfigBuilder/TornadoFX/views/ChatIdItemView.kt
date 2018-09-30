package com.github.insanusmokrassar.AutoPostBotConfigBuilder.TornadoFX.views

import com.github.insanusmokrassar.AutoPostBotConfigBuilder.ValueChangedValidator
import com.github.insanusmokrassar.AutoPostBotConfigBuilder.chatIdValidator
import javafx.beans.value.ObservableValue
import javafx.geometry.Pos
import javafx.scene.Parent
import tornadofx.*

class ChatIdItemView(
    propertyName: String,
    chatIdProperty: ObservableValue<Number>,
    validator: ValueChangedValidator = chatIdValidator
) : View() {
    override val root: Parent = vbox (
        alignment = Pos.CENTER
    ) {
        label(propertyName)
        textfield(chatIdProperty) {
            promptText = chatsIdPromptText
            text = ""
            validator(validator = validator)
        }
    }
}