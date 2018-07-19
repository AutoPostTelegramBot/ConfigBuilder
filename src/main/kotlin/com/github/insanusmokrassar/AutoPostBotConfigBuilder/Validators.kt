package com.github.insanusmokrassar.AutoPostBotConfigBuilder

import javafx.scene.control.TextField

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

private const val errorClass = "error"
private const val warningClass = "warning"
private const val correctClass = "correct"

abstract class RegexValidator<T>(
    override val textField: TextField,
    private val regex: Regex = Regex(".*"),
    private val includeWarning: Boolean = false
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

    private fun refreshCorrectness(value: String) {
        if (isValid(value)) {
            changeCorrectnessState(correctClass)
        } else {
            changeCorrectnessState(warningClass)
        }
    }

    private fun changeCorrectnessState(newValue: String?) {
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

private val chatIdRegex = Regex("^-?\\d{1,16}$")

class ChatIdValidator(
    textField: TextField
) : RegexValidator<Long>(
    textField,
    chatIdRegex,
    true
) {
    override val convert: Long?
        get() = text.toLongOrNull()
}

private val botTokenRegex = Regex("^\\d+:[\\w\\-\\d]{31,}$")

class BotTokenValidator(
    textField: TextField
) : TextValidator(
    textField,
    botTokenRegex
)

private val dbUrlRegex = Regex("^jdbc:[\\w\\d]+:([^;]*;?)*$")

class DatabaseUrlValidator(
    textField: TextField
) : TextValidator(
    textField,
    dbUrlRegex
)

private val packageRegex = Regex("^[A-Za-z\\d]+(\\.[A-Za-z\\d]+)*$")

class PackageValidator(
    textField: TextField
) : TextValidator(
    textField,
    packageRegex
)

private val proxyUrlRegex = Regex("^(https|http|socks)?://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]$")

class ProxyUrlValidator(
    textField: TextField
) : TextValidator(
    textField,
    proxyUrlRegex
)

private val portRegex = Regex("^\\d{1,5}$")

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
        return super.isValid(text) && text.toIntOrNull() in 1 .. 65535
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
