package com.bot.telegram.service

import com.bot.telegram.model.Chat
import com.bot.telegram.repository.ChatRepository
import org.springframework.stereotype.Service

@Service
class ChatServiceImpl(private val chatRepository: ChatRepository) : ChatService {
    override fun findChatByChatId(chatId: Long) = chatRepository.findByChatId(chatId)

    override fun saveChat(chat: com.bot.telegram.model.Chat) = chatRepository.save(chat)
    override fun findChatByChatName(chatName: String): Chat? {
        return chatRepository.findByChatName(chatName)?.let { return it }
    }

    override fun getAllChat(): List<Chat> {
        chatRepository.findAll().let { return it }
    }
}