package com.bot.telegram.repository

import com.bot.telegram.model.Chat
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ChatRepository : JpaRepository<Chat, Long> {
    fun findByChatId(chatId: Long): Chat?

    fun save(chat: Chat): Chat

    fun findByChatName(chatName: String): Chat?

    override fun findAll(): List<Chat>
}