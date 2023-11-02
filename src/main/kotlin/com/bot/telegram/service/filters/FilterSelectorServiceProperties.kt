package com.bot.telegram.service.filters

import com.bot.telegram.config.FilterConfig
import com.bot.telegram.filters.MessageFilter
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class FilterSelectorServiceProperties(
    private val filterConfig: FilterConfig,
    private val filters: Map<String, MessageFilter>
) : FilterSelector {
    companion object {
        private val logger = LoggerFactory.getLogger(FilterSelectorServiceProperties::class.java)
    }
    override fun getFilterChain(): MessageFilter? {
        var currentFilter: MessageFilter? = null
        for (filterName in filterConfig.default.reversed()) {
            logger.info("Filter name: {}", filterName)
            val filter = filters[filterName]
            if (filter != null) {
                logger.info("Filter: {}", filter)
                filter.nextFilter = currentFilter
                logger.info("Filter next: {}", filter.nextFilter)
                currentFilter = filter
                logger.info("Current filter: {}", currentFilter)
            }
        }
        return currentFilter
    }

    override fun getFilters(): List<MessageFilter> {
        return filters.values.toList()
    }
    override fun getFilter(filterName: String): MessageFilter? {
        return filters[filterName]
    }
}