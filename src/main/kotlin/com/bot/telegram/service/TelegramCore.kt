package com.bot.telegram.service

import com.bot.telegram.model.Chat
import com.bot.telegram.model.TelegramRequest
import com.bot.telegram.model.TelegramResponse
import com.bot.telegram.service.filters.FilterService
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.TelegramBotsApi
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup
import org.telegram.telegrambots.meta.exceptions.TelegramApiException
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession


@Component
class TelegramCore(
    val wordService: WordService,
    val filterService: FilterService,
    val chatService: ChatService
) : TelegramLongPollingBot("6245918205:AAFtYMs7LUmPy9Cm2aHSo2_RWmgXwogVYFc") {
    private val logger = LoggerFactory.getLogger(TelegramCore::class.java)

    private val username: String = "@ChatGPTAlgorithmBot"

    init {
        logger.info("TelegramCore init. username: {}", username)
        logger.info("TelegramCore init. token: {}", this.botToken)
        val telegramBot = TelegramBotsApi(DefaultBotSession::class.java)
        telegramBot.registerBot(this)
        logger.info("Telegram Bot started successfully.")
    }

    override fun onUpdateReceived(update: Update) {
        logger.info("Update received: {}", update)
        val message = update.message
        val telegramResponse: TelegramResponse?
        logger.info("Message received: {}", message)
        try {
            if (update.hasChatMember()) {
                chatService.saveChat(
                    Chat(
                        chatId = update.chatMember.chat.id,
                        chatName = update.chatMember.chat.userName
                    )
                )
                return
            }
            telegramResponse = filterService.getResponse(
                TelegramRequest(
                    message = update.message,
                    buttons = message?.replyMarkup
                )
            )
            logger.info("Filter received: {}", telegramResponse)
            if (telegramResponse != null) {

                val reply = SendMessage()
                reply.chatId = message.chatId.toString()
                reply.text = telegramResponse.text ?: ""
                reply.replyMarkup = telegramResponse.buttons
                logger.info("Reply message: {}", reply)
                execute(reply)
            } else {
                val reply = SendMessage()
                reply.chatId = message.chatId.toString()
                reply.text = "No filter found"
                execute(reply)
                logger.info("No filter found")
            }
        } catch (e: TelegramApiException) {
            e.printStackTrace()
        }
    }

    override fun getBotUsername(): String {
        return username
    }
}
