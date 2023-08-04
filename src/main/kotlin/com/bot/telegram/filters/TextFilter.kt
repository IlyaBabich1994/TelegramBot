package com.bot.telegram.filters

import com.bot.telegram.model.TelegramResponse
import org.slf4j.LoggerFactory

abstract class TextFilter {
    var nextFilter: TextFilter? = null

    companion object {
        val logger = LoggerFactory.getLogger(TextFilter::class.java)
    }

    fun doNextFilter(text: String, keywords: List<String>): TelegramResponse? {
        logger.info("doNextFilter <-- text: $text, keywords: $keywords")
        val response = doFilter(text, keywords)
        logger.info("doNextFilter response: $response")
        if ((response == null || !response.isEnabled) && checkNextFilter()) {
            logger.info("doNextFilter nextFilter: $nextFilter")
            return nextFilter!!.doNextFilter(text, keywords)
        }
        return response
    }
    abstract fun doFilter(text: String, keywords: List<String>): TelegramResponse?

    private fun checkNextFilter(): Boolean {
        return nextFilter != null
    }
}