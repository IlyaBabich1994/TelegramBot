package com.bot.telegram.service

import com.bot.telegram.model.Chat
import com.bot.telegram.model.User
import com.bot.telegram.model.UserChat
import com.bot.telegram.repository.UserChatRepository
import org.springframework.stereotype.Service

@Service
class UserChatServiceImpl(private val userChatRepository: UserChatRepository) : UserChatService {
    override fun updateUserChat(user: User, chat: Chat, canWrite: Boolean): UserChat? {

        return chat.chatId?.let { userInChat ->
            userChatRepository.findByUserNameAndChatId(user.name, userInChat)?.let {
                it.canWrite = canWrite
                return userChatRepository.save(it)
            }
        }
    }
}