package com.bot.telegram.model

import jakarta.persistence.Entity
import jakarta.persistence.Id

@Entity
data class Word(
        @Id
        val id: Long? = null,
        val word: String? = null
) {
}
