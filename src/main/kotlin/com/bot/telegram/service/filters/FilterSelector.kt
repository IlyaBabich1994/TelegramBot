package com.bot.telegram.service.filters

import com.bot.telegram.filters.TextFilter

interface FilterSelector {
    fun getFilters(): List<TextFilter>
    fun getFilter(filterName: String): TextFilter?

    fun getFilterChain(): TextFilter?
}