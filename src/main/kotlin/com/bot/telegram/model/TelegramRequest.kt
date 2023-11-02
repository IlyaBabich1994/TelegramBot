package com.bot.telegram.model

import org.telegram.telegrambots.meta.api.objects.Message
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup

data class TelegramRequest(
    val text: String? = null,
    val chatId: Long? = null,
    val buttons: InlineKeyboardMarkup? = null,
    val message: Message? = null,
    val replyToMessageId: Int? = null
)
