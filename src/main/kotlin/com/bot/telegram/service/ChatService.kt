package com.bot.telegram.service

import com.bot.telegram.model.Chat

interface ChatService {
    fun findChatByChatId(chatId: Long): Chat?
    fun saveChat(chat: Chat): Chat

    fun findChatByChatName(chatName: String): Chat?

    fun getAllChat(): List<Chat>
}