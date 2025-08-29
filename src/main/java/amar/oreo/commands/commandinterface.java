package amar.oreo.commands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public interface commandinterface {
   String getName();
   String getDescription();
   void executeSlash(SlashCommandInteractionEvent event);
}