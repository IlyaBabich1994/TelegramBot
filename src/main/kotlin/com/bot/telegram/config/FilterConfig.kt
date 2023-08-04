package com.bot.telegram.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "filters")
data class FilterConfig(
    val default: List<String>
)