package com.bot.telegram.service.filters

import com.bot.telegram.filters.MessageFilter

interface FilterSelector {
    fun getFilters(): List<MessageFilter>
    fun getFilter(filterName: String): MessageFilter?

    fun getFilterChain(): MessageFilter?
}