package org.irq3.commands.report

import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel
import net.dv8tion.jda.api.interactions.components.buttons.Button
import net.dv8tion.jda.api.utils.messages.MessageCreateBuilder
import java.awt.Color

class Report(private val channel:TextChannel) {

    fun sendMessage()
    {
        val embed = EmbedBuilder()
        embed.setTitle("System reportów")
        embed.setDescription("Jeśli masz jakiś problem/skargę na użytkownika zgłoś to używając przycisky poniżej")
        embed.setColor(Color.RED)

        val button = Button.danger("report","Report")

        val message = MessageCreateBuilder().addEmbeds(embed.build()).setActionRow(button).build()
        channel.sendMessage(message).queue()
    }
}