package org.irq3.commands

import net.dv8tion.jda.api.Permission
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent
import net.dv8tion.jda.api.interactions.commands.build.OptionData

interface ICommand {
    val getName:String
    val getDescription:String
    val getPermission: List<Permission>
    val getOptions: List<OptionData>
    fun execute(slashCommandInteractionEvent: SlashCommandInteractionEvent)
}
