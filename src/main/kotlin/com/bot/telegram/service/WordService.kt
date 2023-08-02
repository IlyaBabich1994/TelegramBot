package com.bot.telegram.service


interface WordService {
    fun findByWord(word: String): String
    fun findByWordIgnoreCase(word: String): String
    fun findByWordIgnoreCaseContaining(word: String): List<String>
    fun findAll(): List<String>
}