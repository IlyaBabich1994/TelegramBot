package com.bot.telegram.repository

import com.bot.telegram.model.UserChat
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserChatRepository : JpaRepository<UserChat, Long> {
    fun findByUserNameAndChatId(userName: String, chatId: Long): UserChat?
}