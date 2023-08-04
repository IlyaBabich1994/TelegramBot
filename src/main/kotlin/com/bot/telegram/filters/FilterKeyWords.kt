package com.bot.telegram.filters

import com.bot.telegram.model.TelegramResponse
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton


@Component
class FilterKeyWords : TextFilter() {
    override fun doFilter(text: String, keywords: List<String>): TelegramResponse? {
        for (regex in keywords) {
            if (text.contains(Regex(regex))) {
                return constructTelegramResponce()
            }
        }
        return null
    }

    fun constructTelegramResponce(): TelegramResponse {
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
            text = "Котик",
            message = SendMessage().apply {
                text = "Котик"
            },
            buttons = inlinedKeyboard,
            isEnabled = true
        )
    }
}