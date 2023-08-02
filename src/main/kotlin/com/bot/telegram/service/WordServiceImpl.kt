package com.bot.telegram.service

import com.bot.telegram.repository.WordRepository
import org.springframework.stereotype.Service

@Service
class WordServiceImpl(private val wordRepository: WordRepository) : WordService {
    override fun findByWord(word: String): String {
        return wordRepository.findByWord(word).word ?: ""
    }

    override fun findByWordIgnoreCase(word: String): String {
        return wordRepository.findByWordIgnoreCase(word).word ?: ""
    }

    override fun findByWordIgnoreCaseContaining(word: String): List<String> {
        return wordRepository.findByWordIgnoreCaseContaining(word).map { it.word ?: "" }
    }

    override fun findAll(): List<String> {
        return wordRepository.findAll().map { it.word ?: "" }
    }
}