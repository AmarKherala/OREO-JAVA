package amar.oreo.events;

// from jda
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

// from java
import java.util.Map;
import java.util.HashMap;

// your commands
import amar.oreo.commands.commandinterface;
import amar.oreo.commands.info.ping;
import amar.oreo.commands.info.bot;
import amar.oreo.commands.info.avatar;
import amar.oreo.commands.info.server;
import amar.oreo.commands.info.user;
import amar.oreo.commands.game.maths;
import amar.oreo.commands.game.roshambo;
// *** *** ***
public class slashcommand extends ListenerAdapter {
  
  private final HashMap <String,commandinterface> commands = new HashMap<>();
  
  public slashcommand (){
    // info commands section
    commands.put("ping", new ping());
    commands.put("botinfo", new bot());
    commands.put("serverinfo", new server());
    commands.put("userinfo", new user());
    commands.put("avatar", new avatar());
    
    // game commands section
    commands.put("maths", new maths());
    commands.put("roshambo", new roshambo());
 
  }
  @Override
  public void onSlashCommandInteraction(SlashCommandInteractionEvent event){
    String commandName = event.getName();
    commandinterface command = commands.get(commandName);
    if (commandName!=null){
      command.executeSlash(event);
    } else {
      event.reply("Unknown Command!").setEphemeral(true).queue();
    }
  }
}