package com.github.insanusmokrassar.AutoPostBotConfigBuilder.TornadoFX.views

import com.github.insanusmokrassar.AutoPostBotConfigBuilder.*
import com.github.insanusmokrassar.AutoPostBotConfigBuilder.TornadoFX.ViewModels.DBConfigViewModel
import javafx.scene.Parent
import tornadofx.*

class DBConfigView(
    dbConfigViewModel: DBConfigViewModel
) : View() {
    override val root: Parent = vbox {
        dbConfigViewModel.enabledProperty.onChange {
            dbConfigViewModel.validate()
        }

        checkbox("Enable custom database config", dbConfigViewModel.enabledProperty)

        fieldset {
            textfield(dbConfigViewModel.urlProperty) {
                promptText = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1"
                validator(validator = getDBUrlValidator(dbConfigViewModel.enabledProperty))
            }
            textfield(dbConfigViewModel.driverProperty) {
                promptText = "org.h2.Driver"
                validator(validator = getPackageValidator(dbConfigViewModel.enabledProperty))
            }
            textfield(dbConfigViewModel.usernameProperty) {
                promptText = "Username"
            }
            passwordfield (dbConfigViewModel.passwordProperty) {
                promptText = "Password"
            }
        }
    }
}