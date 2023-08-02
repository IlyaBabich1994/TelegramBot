package com.bot.telegram.service

abstract class TextFilter {
    var nextFilter: TextFilter? = null

    fun doNextFilter(textFilters: List<TextFilter>): TextFilter? {

    }
    abstract fun doFilter(text: String, keywords: Set<String>): Boolean

    fun checkNextFilter(): Boolean {
        return nextFilter != null
    }
}