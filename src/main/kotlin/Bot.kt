package org.irq3

import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.requests.GatewayIntent
import net.dv8tion.jda.api.utils.cache.CacheFlag
import org.irq3.commands.CommandManager
import org.irq3.commands.Hello
import org.irq3.commands.report.ButtonListener
import org.irq3.commands.report.Initialize
import org.irq3.commands.report.Report
import java.util.concurrent.TimeUnit

class Bot (private val token:String){

    fun run()
    {
        val manager: CommandManager = CommandManager()
        manager.add(Hello())
        manager.add(Initialize())
        val jda =JDABuilder.createLight(token, listOf(GatewayIntent.GUILD_MEMBERS,GatewayIntent.GUILD_MESSAGES, GatewayIntent.MESSAGE_CONTENT,GatewayIntent.GUILD_PRESENCES))
            .enableCache(CacheFlag.ONLINE_STATUS).addEventListeners(manager, ButtonListener()).enableIntents(GatewayIntent.GUILD_MESSAGES,GatewayIntent.GUILD_MEMBERS).build().awaitReady()

        jda.run {
            println("===== SIGMA BOT =====")
            val guilds = jda.guilds
            for (guild in guilds)
            {
                println(guild.name+ " "+ guild.id)
            }

        }
    }
}