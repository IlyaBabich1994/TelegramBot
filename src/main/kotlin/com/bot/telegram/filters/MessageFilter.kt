package com.bot.telegram.filters

import com.bot.telegram.model.TelegramRequest
import com.bot.telegram.model.TelegramResponse
import org.slf4j.LoggerFactory

abstract class MessageFilter {
    var nextFilter: MessageFilter? = null

    companion object {
        val logger = LoggerFactory.getLogger(MessageFilter::class.java)
    }

    fun doNextFilter(request: TelegramRequest): TelegramResponse? {
        logger.info("doNextFilter <-- text: $request")
        val response = doFilter(request)
        logger.info("doNextFilter response: $response")
        if ((response == null || !response.isEnabled!!) && checkNextFilter()) {
            logger.info("doNextFilter nextFilter: $nextFilter")
            return nextFilter!!.doNextFilter(request)
        }
        return response
    }
    abstract fun doFilter(request: TelegramRequest): TelegramResponse?

    private fun checkNextFilter(): Boolean {
        return nextFilter != null
    }
}