package com.github.insanusmokrassar.AutoPostBotConfigBuilder

import javafx.beans.property.BooleanProperty
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.control.ToggleButton

class TogglButtonEnabler(
    private val switcher: ToggleButton,
    private val affectDisableProperty: BooleanProperty
) : EventHandler<ActionEvent> {
    init {
        switcher.onAction = this
        handle(null)
    }

    override fun handle(event: ActionEvent?) {
        affectDisableProperty.value = !switcher.isSelected
    }
}