package amar.oreo;

// main classes needed ->
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import amar.oreo.util.amarlogger;

// other writen-by-me classes
import amar.oreo.events.message;
import amar.oreo.events.slashcommand;
import amar.oreo.events.joinsleavs;
import amar.oreo.events.buttons.mathsbutton;
import amar.oreo.events.buttons.rolesbutton;
import amar.oreo.events.tags.tags;
// config classes
import amar.oreo.util.config;

// setting up GetwayIntents
import net.dv8tion.jda.api.requests.GatewayIntent;
import java.util.EnumSet;

// commands handling classes
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.interactions.commands.OptionType;

// ** ** ** ** ** ** ** **
public class main {
  
  private static JDA jda;

	public static void main(String[] args) throws InterruptedException {
	  
	  String botToken = config.token();
	    if (botToken==null){
	      amarlogger.warn("Couldnt find bot token, cant start.");
	      return;
	    } 
	  try {
		  jda = JDABuilder.createDefault(botToken)
		  .enableIntents(EnumSet.allOf(GatewayIntent.class))
		  .setActivity(Activity.watching("The world brun."))
		     .addEventListeners(
		       new message(),
		       new slashcommand(),
		       new joinsleavs(),
		       // buttons
		       new mathsbutton(),
		       new rolesbutton(),
		       // tags
		       new tags()
		       )
		  .build();
		  amarlogger.info("Bot is starting...");
	     } catch (Exception e){
	       amarlogger.error("JDA failed!");
	     }
	     if (jda==null){
	      amarlogger.error("No JDA instace detected!");
	      return;
	     }
   	  jda.awaitReady();
   	  jda.updateCommands()
   	  .addCommands(
   	    // info commands
   	   Commands.slash("ping", "bots ping"),
   	   Commands.slash("botinfo", "info about the bot"),
   	   Commands.slash("serverinfo","info about the server"),
   	   Commands.slash("userinfo", "info about user")
   	   .addOption(OptionType.USER,"user","whos info to get"),
   	   Commands.slash("avatar","get user avatar")
   	   .addOption(OptionType.USER,"user","whoem avatar"),
   	   // game commands
   	   Commands.slash("maths", "simple math game"),
   	   Commands.slash("roshambo","rock paper scisors").addOptions(
   	   new OptionData(OptionType.STRING,"move","choose a move",true)
   	   .addChoice("rock","rock")
   	   .addChoice("paper","paper")
   	   .addChoice("scissors","scissors"))
   	   ).queue();
	} 
}