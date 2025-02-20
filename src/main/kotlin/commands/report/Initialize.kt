package org.irq3.commands.report

import net.dv8tion.jda.api.Permission
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent
import net.dv8tion.jda.api.interactions.commands.OptionType
import net.dv8tion.jda.api.interactions.commands.build.OptionData
import org.irq3.commands.ICommand
import org.irq3.commands.report.Report

class Initialize : ICommand {
    override val getName: String = "initialize"
    override val getDescription: String = "Initialize channel to make reports"
    override val getPermission: List<Permission> = listOf(Permission.ADMINISTRATOR)
    override val getOptions: List<OptionData> = listOf(OptionData(OptionType.CHANNEL, "channel", "channel to initialize report there", true),)

    override fun execute(slashCommandInteractionEvent: SlashCommandInteractionEvent) {
        val option = slashCommandInteractionEvent.getOption("channel")
        if(option!=null)
        {

            val channel = option.asChannel
            val init = Report(channel.asTextChannel(), slashCommandInteractionEvent.jda)
            init.sendMessage()
            slashCommandInteractionEvent.reply("Ok, zrobione").queue()
        }


    }
}