package com.bot.telegram.filters

import com.bot.telegram.model.TelegramRequest
import com.bot.telegram.model.TelegramResponse
import com.bot.telegram.service.UserService
import com.bot.telegram.service.WordService
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton


@Component
class FilterKeyWords(
    private val wordService: WordService,
    private val userService: UserService) : MessageFilter() {
    companion object {
        val logger = org.slf4j.LoggerFactory.getLogger(FilterKeyWords::class.java)
    }
    override fun doFilter(request: TelegramRequest): TelegramResponse? {
        logger.info("doFilter <-- text: $request")
        val keywords = wordService.findAll()
        for (regex in keywords) {
            logger.info("doFilter regex: $regex")
            if (request.message?.text?.contains(Regex(regex)) == true) {
                logger.info("doFilter request.message?.text: ${request.message.text}")
                val newUser = userService.findByNameAndChatIdAndCanWrite(
                    request.message.from.userName ?: "",
                    request.chatId ?: 0, true
                )
                return constructTelegramResponce()
            }
        }
        return null
    }

    private fun constructTelegramResponce(): TelegramResponse {
        val rowsInline: MutableList<MutableList<InlineKeyboardButton>> = mutableListOf()
        val rowInline: MutableList<InlineKeyboardButton> = mutableListOf()
        val first = InlineKeyboardButton("Я ищу новый дом для котик")
        first.callbackData = "Looking for a new home for a cat"
        val second = InlineKeyboardButton("Я не ищу новый дом для котика")
        second.callbackData = "I'm not looking for a new home for a cat"
        rowInline.add(first)
        rowInline.add(second)
        rowsInline.add(rowInline)
        val inlinedKeyboard = InlineKeyboardMarkup()
        inlinedKeyboard.keyboard = rowsInline
        return TelegramResponse(
            buttons = inlinedKeyboard,
            isEnabled = true
        )
    }
}