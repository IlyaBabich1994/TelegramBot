package com.bot.telegram.service

import com.bot.telegram.model.Word
import com.bot.telegram.repository.WordRepository
import org.apache.http.HttpStatus
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

    override fun addWord(chatId: Long, word: String): String {
        return wordRepository.save(Word(null, chatId, word)).let { word  }
    }

    override fun deleteWord(chatId: Long, word: String): String {
        return wordRepository.deleteByChatIdAndWord(chatId, word).let { HttpStatus.SC_OK.toString() }
    }

    override fun updateWord(chatId: Long, word: String): String {
        val word = wordRepository.findByWord(word)
        return wordRepository.save(word.copy(chat = chatId)).let { HttpStatus.SC_OK.toString() }

    }

    override fun getAllWordsForChat(chatId: Long): List<String> {
        return wordRepository.findWordsByChatId(chatId).map { it.word ?: "" }
    }

    override fun saveAllWords(chatId: Long, words: List<String>): String {
        return wordRepository.saveAll(words.map { Word(null, chatId, it) }).let { HttpStatus.SC_OK.toString() }
    }
}