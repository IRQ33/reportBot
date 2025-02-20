package org.irq3.commands

import net.dv8tion.jda.api.Permission
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent
import net.dv8tion.jda.api.interactions.commands.build.OptionData

class Hello : ICommand {
    override val getName: String = "hello"
    override val getDescription: String = "Says hello"
    override val getPermission: List<Permission> = listOf(Permission.MESSAGE_SEND)
    override val getOptions: List<OptionData> = emptyList()


    override fun execute(slashCommandInteractionEvent: SlashCommandInteractionEvent) {
        slashCommandInteractionEvent.reply("Witaj :D").queue()
    }


}