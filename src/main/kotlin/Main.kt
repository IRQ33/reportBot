package org.irq3


fun main() {
        println("Welcome in our new bot!!")
        val getter = loadJsonFromResource().loadJsonFromResource("/info.json")
        val token = getter?.token
        val bot = Bot(token.toString())
       bot.run()
    }

