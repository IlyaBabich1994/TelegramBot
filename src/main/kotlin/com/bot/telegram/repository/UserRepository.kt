package com.bot.telegram.repository

import com.bot.telegram.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Long> {
    fun findByIdAndChatsIdAndCanWrite(id: Long, chatId: Long, canWrite: Boolean): User?

    fun findByNameAndChatsIdAndCanWrite(name: String, chatId: Long, canWrite: Boolean): User?

    fun findByChatsId(chatId: Long): List<User>

    fun findByName(name: String): User?

}