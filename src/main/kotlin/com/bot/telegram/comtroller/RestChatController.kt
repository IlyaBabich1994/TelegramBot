package com.bot.telegram.comtroller

import com.bot.telegram.model.Chat
import com.bot.telegram.service.ChatService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/chat")
class RestChatController(val chatService: ChatService) {
    @GetMapping("/all")
    fun getAllChat() : ResponseEntity<List<Chat>> {
        return ResponseEntity.ok(chatService.getAllChat())
    }

    @PostMapping("/save")
    fun saveChat(@RequestBody chat: Chat) : ResponseEntity<Chat> {
        return ResponseEntity.ok(chatService.saveChat(chat))
    }

    @GetMapping("/find")
    fun findChatByChatId(@RequestParam("chatId") chatId: Long) : ResponseEntity<Chat> {
        return ResponseEntity.ok(chatService.findChatByChatId(chatId))
    }

    @GetMapping("/find-by-name")
    fun findChatByChatName(@RequestParam("chatName") chatName: String) : ResponseEntity<Chat> {
        return ResponseEntity.ok(chatService.findChatByChatName(chatName))
    }
}