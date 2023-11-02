package com.bot.telegram.service

import com.bot.telegram.model.TelegramRequest
import com.bot.telegram.model.TelegramResponse
import com.bot.telegram.model.User
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
/*
* Если пользователя нет в таблице users, то сохраняем его в бд. Если пользователь есть в таблице
* */
@Service
@Transactional(propagation = Propagation.REQUIRES_NEW)
class UserAcceptServiceImpl(private val userService: UserService) : UserAcceptService {
    override fun acceptUser(telegramRequest: TelegramRequest): TelegramResponse {
        val userName = telegramRequest.message?.from?.userName ?: ""
        val chatId = telegramRequest.chatId ?: 0
        val buttons = telegramRequest.buttons?.keyboard
        val isSearching = buttons?.map { it.map { it.callbackData } }?.flatten()?.contains("I'm not looking for a new home for a cat") == true
        val existingUserChat = userService.findUserChatByUserAndChatId(userName, chatId)
        return if(isSearching) {
            TelegramResponse(text = "Thank you for your response. We will contact you if we find a new home for your cat.")
        } else {
            telegramRequest.message?.from?.let {

                userService.saveUser(
                    User(
                        id = it.id,
                        name = it.userName,
                        canWrite = true
                    )
                )
            }
            if(existingUserChat == null) {
                TelegramResponse(text = "Thank you for your response. We will contact you if we find a new home for your cat.")
            } else {
                TelegramResponse(text = "Thank you for your response. We will contact you if we find a new home for your cat.")
            }
        }

    }
}