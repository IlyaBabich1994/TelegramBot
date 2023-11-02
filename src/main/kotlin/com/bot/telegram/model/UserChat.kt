package com.bot.telegram.model

import jakarta.persistence.*

@Entity
@Table(name = "user_chats")
data class UserChat(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    val user: User? = null,
    @ManyToOne
    @JoinColumn(name = "chat_id", nullable = false)
    val chat: Chat? = null,
    var canWrite: Boolean = false
)
