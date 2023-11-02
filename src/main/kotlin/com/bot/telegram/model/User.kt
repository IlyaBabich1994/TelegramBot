package com.bot.telegram.model

import jakarta.persistence.*

@Entity
@Table(name = "users")
data class User(
    @Id
    val id: Long? = null,
    @ManyToMany
    @JoinTable(
        name = "user_chats",
        joinColumns = [JoinColumn(name = "user_id")],
        inverseJoinColumns = [JoinColumn(name = "chat_id")]
    )
    val chats: Set<Chat> = emptySet(),
    @Column(name = "username", unique = true, nullable = false)
    val name: String = "",
    val canWrite: Boolean = false
)
