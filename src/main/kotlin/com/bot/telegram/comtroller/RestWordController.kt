package com.bot.telegram.comtroller

import com.bot.telegram.model.Word
import com.bot.telegram.service.WordService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class RestWordController(val wordService: WordService) {
    @GetMapping("/word/{id-chat}/all")
    fun getAllWord(@PathVariable("id-chat") chatId: Long) : ResponseEntity<List<String>> {
        return ResponseEntity.ok(wordService.getAllWordsForChat(chatId))
    }

    @GetMapping("/word/{id-chat}/add")
    fun addWord(@PathVariable("id-chat") chatId: Long, @RequestParam("word") word: String) : ResponseEntity<String> {
        return ResponseEntity.ok(wordService.addWord(chatId, word))
    }

    @PostMapping("/word/{id-chat}/delete")
    fun deleteWord(@PathVariable("id-chat") chatId: Long, @RequestParam("word") word: String) : ResponseEntity<String> {
        return ResponseEntity.ok(wordService.deleteWord(chatId, word))
    }

    @PostMapping("/word/{id-chat}/update")
    fun updateWord(@PathVariable("id-chat") chatId: Long, @RequestParam("word") word: String) : ResponseEntity<String> {
        return ResponseEntity.ok(wordService.updateWord(chatId, word))
    }

    @PostMapping("/word/{id-chat}/save")
    fun saveWord(@PathVariable("id-chat") chatId: Long, @RequestBody words: List<String>) : ResponseEntity<String> {
        return ResponseEntity.ok(wordService.saveAllWords(chatId, words))
    }
}