package com.bot.telegram.model

import io.micrometer.common.lang.NonNullFields
import jakarta.persistence.*

@Entity
@NonNullFields
data class Word(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,
        @Column(nullable = false, name = "chat_id")
        val chat: Long = 0,
        @Column(nullable = false, length = 255, )
        val word: String? = null
)
