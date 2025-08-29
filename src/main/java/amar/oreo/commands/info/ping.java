package amar.oreo.commands.info;

import amar.oreo.commands.commandinterface;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class ping implements commandinterface {
  @Override
  public String getName(){
    return "ping";
  }
  @Override
  public String getDescription(){
    return "bots ping";
  }
  @Override
  public void executeSlash(SlashCommandInteractionEvent event){
    long ping = event.getJDA().getGatewayPing();
    event.replyFormat("PONG! the ping is: %dms", ping).queue();
  }
}