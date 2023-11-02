package com.bot.telegram.service.filters

import com.bot.telegram.model.TelegramRequest
import com.bot.telegram.model.TelegramResponse
import com.bot.telegram.service.WordService
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class FilterService(
    private val filterSelector: FilterSelector
) {
    companion object {
        private val logger = LoggerFactory.getLogger(FilterService::class.java)
    }
    fun getResponse(request: TelegramRequest): TelegramResponse? {
        logger.info("getResponse <-- request: {}", request)
        when {
            request.message != null ->
            {
                logger.info("Buttons is null")
                val message = request.message!!.text
                logger.info("Message: {}", message)
                val filterChain =
                    filterSelector.getFilterChain() ?: return TelegramResponse(text = "isOk", isEnabled = false)
                logger.info("Filter chain: {}", filterChain)
                return filterChain.doNextFilter(request)
            }
            request.buttons != null -> {
                logger.info("Message is null")
                return null
            }
            else -> {
                logger.info("Message and buttons are null")
                return null
            }
        }
    }
}