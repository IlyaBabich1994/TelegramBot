package com.bot.telegram.filters

import com.bot.telegram.model.TelegramRequest
import com.bot.telegram.model.TelegramResponse
import com.bot.telegram.service.UserAcceptService
import com.bot.telegram.service.UserService
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component

@Component
class FilterUserAccept(
    private val userService: UserService,
    private val userAcceptService: UserAcceptService) : MessageFilter() {
    companion object {
        val logger = org.slf4j.LoggerFactory.getLogger(FilterUserAccept::class.java)
    }

    @Bean

    override fun doFilter(request: TelegramRequest): TelegramResponse? {
        logger.info("doFilter <-- text: $request")
        val user =
            userService.findByNameAndChatIdAndCanWrite(
                request.message?.from?.userName ?: "",
                request.chatId ?: 0, true
            )
        logger.info("doFilter user: $user")
        return if (user == null) {
            logger.info("doFilter user doesn't exist in db with name: ${request.message?.from?.userName}")
            null
        } else {
            nextFilter = null
            logger.info("doFilter user found in db with name: ${request.message?.from?.userName}")
            null
        }
    }
}