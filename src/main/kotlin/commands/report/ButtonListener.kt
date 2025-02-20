package org.irq3.commands.report

import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import net.dv8tion.jda.api.interactions.components.buttons.Button
import net.dv8tion.jda.api.utils.messages.MessageCreateBuilder
import org.irq3.JsonOperations
import java.awt.Color
import java.util.concurrent.TimeUnit

class ButtonListener : ListenerAdapter() {

    override fun onButtonInteraction(event: ButtonInteractionEvent) {
        if (event.button.id.equals("report"))
        {
            report(event)
        }
        if(event.button.id.equals("close"))
        {
            close(event)
        }
    }

    private fun report(event: ButtonInteractionEvent) {
        val loadjs = JsonOperations()
        val data = loadjs.loadJsonFromResource("/info.json")

        event.guild?.createTextChannel("issue " + (data?.number?.inc()))?.queue { channel ->
            val embed = EmbedBuilder().setTitle("Problem " + (data?.number))
                .setDescription("Jeśli uznasz że problem został rozwiązany kliknij w przycisk Zamknij").build()
            val button = Button.primary("close", "Zamknij")
            val msg = MessageCreateBuilder().addEmbeds(embed).setActionRow(button).build()
            channel.sendMessage(msg).queue()
            }
        if (data != null) {
            data.number += 1
            loadjs.writeJsonToResources("/info.json", data)
        }
        event.reply("Tworzę nowy kanał").setEphemeral(true).queue()
    }

    private fun close(event: ButtonInteractionEvent) {
        event.replyEmbeds(
            EmbedBuilder().setTitle("Usuwanie kanału").setColor(Color.RED)
                .setDescription("Kanał zostanie usunięty w ciągu 5 sekund").build()
        ).setEphemeral(true).queue()
        event.channel.delete().queueAfter(5, TimeUnit.SECONDS)
    }
}