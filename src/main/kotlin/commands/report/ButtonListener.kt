package org.irq3.commands.report

import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import net.dv8tion.jda.api.interactions.components.buttons.Button
import net.dv8tion.jda.api.utils.messages.MessageCreateBuilder
import org.irq3.loadJsonFromResource
import java.awt.Color
import java.util.concurrent.TimeUnit

class ButtonListener : ListenerAdapter() {

    override fun onButtonInteraction(event: ButtonInteractionEvent) {
        if (event.button.id.equals("report"))
        {
            val loadjs = loadJsonFromResource()
            var data = loadjs.loadJsonFromResource("/info.json")
            println()
            val channel  = event.guild?.createTextChannel("issue"+ (data?.number ?: println("can t load")))?.queue {
                channel ->
                val embed = EmbedBuilder().setTitle("Problem " + (data?.number ?: 0))
                    .setDescription("Jeśli uznasz że problem został rozwiązany kliknij w przycisk Zamknij").build()
                val button = Button.primary("close", "Zamknij")
                val msg = MessageCreateBuilder().addEmbeds(embed).setActionRow(button).build()
                channel.sendMessage(msg).queue()
            }
            if (data != null) {
               data.number =  data.number.inc()
                loadjs.writeJsonToResources("/info.json",data)
            }
            event.reply("Tworzę nowy kanał").setEphemeral(true).queue()
        }
        if(event.button.id.equals("close"))
        {
            event.replyEmbeds(EmbedBuilder().setTitle("Usuwanie kanału").setColor(Color.RED).setDescription("Kanał zostanie usunięty w ciągu 5 sekund").build()).queue()
            event.channel.delete().queueAfter(5,TimeUnit.SECONDS)
        }
    }
}