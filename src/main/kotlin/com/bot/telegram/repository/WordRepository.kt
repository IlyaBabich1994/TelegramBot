package com.bot.telegram.repository

import com.bot.telegram.model.Word
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface WordRepository : CrudRepository<Word, Long> {
    fun findByWord(word: String): Word

    fun findByWordIgnoreCase(word: String): Word
    fun findByWordIgnoreCaseContaining(word: String): List<Word>
    override fun findAll(): List<Word>
}