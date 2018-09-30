package com.github.insanusmokrassar.AutoPostBotConfigBuilder.TornadoFX.views

import com.github.insanusmokrassar.AutoPostBotConfigBuilder.chatIdValidator
import com.github.insanusmokrassar.AutoPostBotConfigBuilder.TornadoFX.ViewModels.*
import javafx.geometry.Pos
import javafx.scene.Parent
import tornadofx.*

internal const val chatsIdPromptText = "-12345678901"

class NewApp : App(ChatsIdsView::class)

fun main(args: Array<String>) {
    launch<NewApp>(*args)
}

class ChatsIdsView : View() {
    private val chatsIdsViewModel = ChatsIdsViewModel()
    private val botTokenViewModel = BotTokenViewModel()
    private val dbChatsIdsViewModel = DBConfigViewModel()
    private val proxyConfigViewModel = ProxyConfigViewModel()

    override val root: Parent = vbox {
        hbox(
            alignment = Pos.CENTER
        ) {
            add(ChatIdItemView("Source Chat Id", chatsIdsViewModel.sourceChatIdProperty))
            add(ChatIdItemView("Target Chat Id", chatsIdsViewModel.targetChatIdProperty))
            add(
                ChatIdItemView(
                    "Logs Chat Id",
                    chatsIdsViewModel.logsChatIdProperty
                ) {
                    if (it.isNullOrEmpty()) {
                        success()
                    } else {
                        chatIdValidator(it)
                    }
                }
            )
        }
        add(BotTokenView(botTokenViewModel))
        add(DBConfigView(dbChatsIdsViewModel))
        add(ProxyConfigView(proxyConfigViewModel))
        button("PRESS ME") {
            enableWhen(
                chatsIdsViewModel.valid.and(
                    botTokenViewModel.valid
                ).and(
                    dbChatsIdsViewModel.valid
                ).and(
                    proxyConfigViewModel.valid
                )
            )
            action {
                println("${chatsIdsViewModel.sourceChatId}:${chatsIdsViewModel.targetChatId}:${chatsIdsViewModel.logsChatId}")
            }
        }
    }
}