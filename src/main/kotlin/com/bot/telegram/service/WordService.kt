package com.bot.telegram.service


interface WordService {
    fun findByWord(word: String): String
    fun findByWordIgnoreCase(word: String): String
    fun findByWordIgnoreCaseContaining(word: String): List<String>
    fun findAll(): List<String>
    fun addWord(chatId: Long, word: String): String
    fun deleteWord(chatId: Long, word: String): String
    fun updateWord(chatId: Long, word: String): String
    fun getAllWordsForChat(chatId: Long): List<String>

    fun saveAllWords(chatId: Long, words: List<String>): String
}