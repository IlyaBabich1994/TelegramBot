package com.bot.telegram.model

import jakarta.persistence.*

@Entity
@Table(name = "chats")
data class Chat(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val chatName: String? = "",
    val chatId: Long? = null,
    @ElementCollection
    @CollectionTable(name = "chat_words", joinColumns = [JoinColumn(name = "chat_id")])
    @Column(name = "word_id")
    val words: List<Long>? = listOf(),
    @ElementCollection
    @CollectionTable(name = "chat_words", joinColumns = [JoinColumn(name = "chat_id")])
    @Column(name = "word_id")
    val owner: List<Long>? = listOf(),

    // Добавьте другие поля, если необходимо
)