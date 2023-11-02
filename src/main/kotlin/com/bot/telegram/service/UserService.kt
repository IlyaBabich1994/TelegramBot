package com.bot.telegram.service

import com.bot.telegram.model.User
import com.bot.telegram.model.UserChat
import org.springframework.transaction.annotation.Transactional

interface UserService {
    fun findByIdAndChatIdAndCanWrite(id: Long, chatId: Long, canWrite: Boolean): User?

    fun findByNameAndChatIdAndCanWrite(name: String, chatId: Long, canWrite: Boolean): User?

    fun findByName(name: String): User?

    fun findUserChatByUserAndChatId(name: String, chatId: Long): UserChat?
    fun saveUser(user: User): User

    fun updateUser(userName: String, chatId: Long, canWrite: Boolean): UserChat?
}