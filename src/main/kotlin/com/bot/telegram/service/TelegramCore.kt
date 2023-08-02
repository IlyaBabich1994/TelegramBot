package com.bot.telegram.service

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.TelegramBotsApi
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton
import org.telegram.telegrambots.meta.exceptions.TelegramApiException
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession


@Component
class TelegramCore(
        val wordService: WordService,
        val wordFilter: WordFilter
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
        val messages = wordService.findAll();
        logger.info("Message received: {}", message)
        var text = message.text
        if (wordFilter.containsPhrase(text)) {
            try {
                logger.info("Send message: {}", text)
                val inlinedKeyboard = InlineKeyboardMarkup()
                val first = InlineKeyboardButton("Котик")
                val second = InlineKeyboardButton("Пёсик")
                first.callbackData = "Котик"
                second.callbackData = "Пёсик"
                inlinedKeyboard.keyboard = listOf(listOf(first, second))

                val reply = SendMessage()
                reply.chatId = message.chatId.toString()
                reply.text = "Котик"
                logger.info("Reply message: {}", reply)
                execute(reply)

            } catch (e: TelegramApiException) {
                e.printStackTrace()
            }
        } else {
            val reply = SendMessage()
            reply.chatId = message.chatId.toString()
            reply.text = "Я не понимаю"
            logger.info("Reply message: {}", reply)
            execute(reply)
        }
    }

    override fun getBotUsername(): String {
        return username
    }
}
