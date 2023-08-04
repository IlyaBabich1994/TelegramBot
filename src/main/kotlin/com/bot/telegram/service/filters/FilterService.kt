package com.bot.telegram.service.filters

import com.bot.telegram.model.TelegramResponse
import com.bot.telegram.service.WordService
import org.springframework.stereotype.Service

@Service
class FilterService(
    private val filterSelector: FilterSelector, val wordService: WordService
) {
    fun getResponse(message: String): TelegramResponse? {
        val filterChain = filterSelector.getFilterChain() ?: return TelegramResponse(text = "isOk", isEnabled = false)
        val words = wordService.findAll();
        return filterChain.doNextFilter(message, words)
    }
}