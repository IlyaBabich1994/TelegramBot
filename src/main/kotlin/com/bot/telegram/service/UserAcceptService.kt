package com.bot.telegram.service

import com.bot.telegram.model.TelegramRequest
import com.bot.telegram.model.TelegramResponse

interface UserAcceptService {
    fun acceptUser(telegramRequest: TelegramRequest): TelegramResponse
}