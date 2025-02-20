package org.irq3.commands

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent
import net.dv8tion.jda.api.events.session.ReadyEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions

class CommandManager: ListenerAdapter() {
   private var  listOfCommand :MutableList<ICommand> = mutableListOf()

    override fun onSlashCommandInteraction(event: SlashCommandInteractionEvent) {
        for (command in listOfCommand)
        {
            if(command.getName == event.name)
            {
                command.execute(event)
                return

            }
        }
    }
    override fun onReady(event: ReadyEvent) {
        for (guild in event.jda.guilds)
        {
            for (command in listOfCommand)
            {
                if(!command.getOptions.isNullOrEmpty())
                {
                    guild.upsertCommand(command.getName, command.getDescription).addOptions(command.getOptions).setDefaultPermissions(
                        DefaultMemberPermissions.enabledFor(command.getPermission)).queue()
                }else{
                    guild.upsertCommand(command.getName, command.getDescription).setDefaultPermissions(
                        DefaultMemberPermissions.enabledFor(command.getPermission)).queue()
                }

            }
        }
    }

    fun add(command: ICommand)
    {
        listOfCommand.add(command)
    }

}