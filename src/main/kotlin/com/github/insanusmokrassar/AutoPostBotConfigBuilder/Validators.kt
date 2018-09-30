package com.github.insanusmokrassar.AutoPostBotConfigBuilder

import javafx.beans.value.ObservableValue
import javafx.scene.control.TextField
import tornadofx.*

interface TextFieldValidator<T> {
    val textField: TextField
    val isValid: Boolean
        get() = isValid(text)
    val outputOrNull: T?
    val output: T
        @Throws(IllegalStateException::class)
        get() = outputOrNull ?: throw IllegalStateException("Field is incorrect")
    var text: String
        get() = textField.text
        set(value) {
            textField.text = value
        }
    fun isValid(text: String): Boolean
}

const val errorClass = "error"
const val warningClass = "warning"
const val correctClass = "correct"

abstract class RegexValidator<T>(
    override val textField: TextField,
    val regex: Regex = Regex(".*"),
    val includeWarning: Boolean = false
) : TextFieldValidator<T> {
    protected abstract val convert: T?
    override fun isValid(text: String): Boolean {
        return regex.matches(text)
    }

    override val outputOrNull: T?
        get() = if (isValid) {
            convert
        } else {
            changeCorrectnessState(errorClass)
            textField.setOnMousePressed {
                if (includeWarning) {
                    changeCorrectnessState(warningClass)
                } else {
                    changeCorrectnessState(null)
                }
                textField.onMousePressed = null
            }
            null
        }

    init {
        if (includeWarning) {
            textField.textProperty().addListener {
                _, _, newValue ->
                refreshCorrectness(newValue)
            }
            refreshCorrectness(text)
        }
    }

    fun refreshCorrectness(value: String) {
        if (isValid(value)) {
            changeCorrectnessState(correctClass)
        } else {
            changeCorrectnessState(warningClass)
        }
    }

    fun changeCorrectnessState(newValue: String?) {
        textField.styleClass.apply {
            remove(errorClass)
            remove(warningClass)
            remove(correctClass)
            newValue ?.also {
                add(it)
            }
        }
    }
}

open class TextValidator(
    textField: TextField,
    regex: Regex
) : RegexValidator<String>(
    textField,
    regex,
    true
) {
    override val convert: String?
        get() = text
}

typealias ValueChangedValidator = ValidationContext.(String?) -> ValidationMessage?

private val chatIdRegex = Regex("^-?\\d{1,16}$")
private val botTokenRegex = Regex("^\\d+:[\\w\\-\\d]{31,}$")
private val dbUrlRegex = Regex("^(jdbc:[\\w\\d]+:([^;]*;?)*)?$")
private val packageRegex = Regex("^[A-Za-z\\d]+(\\.[A-Za-z\\d]+)*$")
private val portRegex = Regex("^\\d{0,5}$|^$")
private val proxyUrlRegex = Regex("^((https|http|socks)://)?[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]$|^\$")

private fun getRegexValidator(
    regex: Regex,
    errorMessage: String? = "Incorrect"
): ValueChangedValidator {
    return {
        if (it != null) {
            if (regex.matches(it)) {
                success()
            } else {
                error(errorMessage)
            }
        } else {
            null
        }
    }
}

private fun getRegexOrPropertyValidator(
    regex: Regex,
    property: ObservableValue<Boolean>?,
    errorMessage: String? = "Incorrect"
): ValueChangedValidator {
    val regexValidator = getRegexValidator(regex, errorMessage)
    property ?.also {
        _ ->
        return {
            if (property.value != true) {
                null
            } else {
                regexValidator(it)
            }
        }
    }
    return regexValidator
}

val chatIdValidator: ValueChangedValidator = getRegexValidator(chatIdRegex, "Chat id is incorrect")

val botTokenValidator: ValueChangedValidator = getRegexValidator(botTokenRegex, "Bot token is incorrect")

fun getDBUrlValidator(enabledProperty: ObservableValue<Boolean>): ValueChangedValidator {
    return getRegexOrPropertyValidator(
        dbUrlRegex,
        enabledProperty,
        "DB URL is incorrect"
    )
}

fun getPackageValidator(enabledProperty: ObservableValue<Boolean>? = null): ValueChangedValidator {
    return getRegexOrPropertyValidator(
        packageRegex,
        enabledProperty,
        "Package is incorrect"
    )
}

fun getProxyUrlValidator(enabledProperty: ObservableValue<Boolean>? = null): ValueChangedValidator {
    return getRegexOrPropertyValidator(
        proxyUrlRegex,
        enabledProperty,
        "Proxy url is incorrect"
    )
}

fun getProxyPortValidator(enabledProperty: ObservableValue<Boolean>? = null): ValueChangedValidator {
    return getRegexOrPropertyValidator(
        portRegex,
        enabledProperty,
        "Port of proxy is incorrect"
    )
}

// --------------------------------------------------------------

open class ChatIdValidator(
    textField: TextField
) : RegexValidator<Long>(
    textField,
    chatIdRegex,
    true
) {
    override val convert: Long?
        get() = text.toLongOrNull()
}

class LogsChatIdValidator(
    textField: TextField
) : ChatIdValidator(
    textField
) {
    override fun isValid(text: String): Boolean {
        return super.isValid(text) || text.isEmpty()
    }
}

class BotTokenValidator(
    textField: TextField
) : TextValidator(
    textField,
    botTokenRegex
)


class DatabaseUrlValidator(
    textField: TextField
) : TextValidator(
    textField,
    dbUrlRegex
)


class PackageValidator(
    textField: TextField
) : TextValidator(
    textField,
    packageRegex
)

class ProxyUrlValidator(
    textField: TextField
) : TextValidator(
    textField,
    proxyUrlRegex
) {
    override val outputOrNull: String?
        get() = super.outputOrNull ?.let {
            if (it.isEmpty()) {
                null
            } else {
                it
            }
        }
}


class PortValidator(
    textField: TextField
) : RegexValidator<Int>(
    textField,
    portRegex,
    true
) {
    override val convert: Int?
        get() = text.toIntOrNull()

    override fun isValid(text: String): Boolean {
        return super.isValid(text) && if (text.isNotEmpty()) {
            text.toIntOrNull() in 1 .. 65535
        } else {
            true
        }
    }
}

fun TextField.notEmptyTextOrNull(): String? {
    return text.let {
        if (it.isEmpty()) {
            null
        } else {
            it
        }
    }
}
