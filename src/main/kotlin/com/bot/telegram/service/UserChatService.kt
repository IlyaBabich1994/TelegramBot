package com.bot.telegram.service

import com.bot.telegram.model.Chat
import com.bot.telegram.model.User
import com.bot.telegram.model.UserChat

interface UserChatService {
    fun updateUserChat(user: User, chat: Chat, canWrite: Boolean): UserChat?
}