package com.bot.telegram.model

import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup

class TelegramResponse(
    val text: String,
    val chatId: Long? = null,
    val buttons: InlineKeyboardMarkup? = null,
    val message: SendMessage? = null,
    val isEnabled: Boolean = false
)