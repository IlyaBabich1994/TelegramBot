package com.bot.telegram.service

class TextFilterService : TextFilter {
    override fun doFilter(text: String, keywords: Set<String>): Boolean {
        val filteredKeywords = keywords.filter { keyword ->
            keyword.split("+").all { subKeyword ->
                subKeyword.trim().split("*").all { part ->
                    when {
                        part.contains('?') -> {
                            val pattern = part.replace("?", ".?")
                            text.contains(Regex(pattern))
                        }
                        part.contains('(') -> {
                            val variants = part.substringAfter('(').substringBefore(')').split(',')
                            variants.any { variant -> text.contains(variant.trim()) }
                        }
                        else -> text.contains(part.trim())
                    }
                }
            }
        }
        return text.replace("котик", "Котик")
    }
}