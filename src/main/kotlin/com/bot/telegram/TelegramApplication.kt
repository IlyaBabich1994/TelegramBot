package com.bot.telegram

import com.bot.telegram.service.TelegramCore
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.telegram.telegrambots.meta.TelegramBotsApi
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession

@SpringBootApplication
class TelegramApplication

fun main(args: Array<String>) {
    runApplication<TelegramApplication>(*args)

}
