package com.bot.telegram.service

import com.bot.telegram.model.User
import com.bot.telegram.model.UserChat
import com.bot.telegram.repository.UserChatRepository
import com.bot.telegram.repository.UserRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val userChatRepository: UserChatRepository
) : UserService {
    companion object {
        private val log = LoggerFactory.getLogger(UserServiceImpl::class.java)
    }

    @Transactional
    override fun findByIdAndChatIdAndCanWrite(id: Long, chatId: Long, canWrite: Boolean): User? {
        log.info("findByIdAndChatIdAndCanWrite --> id: $id, chatId: $chatId, canWrite: $canWrite")
        return userRepository.findByIdAndChatsIdAndCanWrite(id, chatId, canWrite)
    }

    @Transactional
    override fun findByNameAndChatIdAndCanWrite(name: String, chatId: Long, canWrite: Boolean) =
        userRepository.findByNameAndChatsIdAndCanWrite(name, chatId, canWrite)

    @Transactional
    override fun findByName(name: String) = userRepository.findByName(name)

    @Transactional
    override fun findUserChatByUserAndChatId(name: String, chatId: Long): UserChat? {
        return userChatRepository.findByUserNameAndChatId(name, chatId)?.let {
            return it
        }
    }

    override fun saveUser(user: User) = userRepository.save(user)
    override fun updateUser(userName: String, chatId: Long, canWrite: Boolean): UserChat? {
        return userChatRepository.findByUserNameAndChatId(userName, chatId)?.let {
            it.canWrite = canWrite
            userChatRepository.save(it)
        }
    }
}