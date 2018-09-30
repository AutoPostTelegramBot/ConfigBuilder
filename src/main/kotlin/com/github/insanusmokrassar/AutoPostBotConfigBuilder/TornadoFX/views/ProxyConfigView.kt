package com.github.insanusmokrassar.AutoPostBotConfigBuilder.TornadoFX.views

import com.github.insanusmokrassar.AutoPostBotConfigBuilder.*
import com.github.insanusmokrassar.AutoPostBotConfigBuilder.TornadoFX.ViewModels.ProxyConfigViewModel
import javafx.scene.Parent
import tornadofx.*

class ProxyConfigView(
    proxyConfigViewModel: ProxyConfigViewModel
) : View() {
    override val root: Parent = vbox {
        proxyConfigViewModel.enabledProperty.onChange {
            proxyConfigViewModel.validate()
        }

        checkbox("Enable proxy config", proxyConfigViewModel.enabledProperty)

        fieldset {
            textfield(proxyConfigViewModel.urlProperty) {
                promptText = "localhost"
                validator(validator = getProxyUrlValidator(proxyConfigViewModel.enabledProperty))
            }
            textfield(proxyConfigViewModel.portProperty) {
                text = proxyConfigViewModel.port.toString()
                promptText = "1080"
                validator(validator = getProxyPortValidator(proxyConfigViewModel.enabledProperty))
            }
            textfield(proxyConfigViewModel.usernameProperty) {
                promptText = "Username"
            }
            passwordfield (proxyConfigViewModel.passwordProperty) {
                promptText = "Password"
            }
        }
    }
}