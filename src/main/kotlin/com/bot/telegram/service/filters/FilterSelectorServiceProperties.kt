package com.bot.telegram.service.filters

import com.bot.telegram.config.FilterConfig
import com.bot.telegram.filters.TextFilter
import org.springframework.stereotype.Service

@Service
class FilterSelectorServiceProperties(
    private val filterConfig: FilterConfig,
    private val filters: Map<String, TextFilter>
) : FilterSelector {
    override fun getFilterChain(): TextFilter? {
        var currentFilter: TextFilter? = null
        for (filterName in filterConfig.default) {
            val filter = filters[filterName]
            if (filter != null) {
                filter.nextFilter = currentFilter
                currentFilter = filter
            }
        }
        return currentFilter
    }

    override fun getFilters(): List<TextFilter> {
        return filters.values.toList()
    }
    override fun getFilter(filterName: String): TextFilter? {
        return filters[filterName]
    }
}