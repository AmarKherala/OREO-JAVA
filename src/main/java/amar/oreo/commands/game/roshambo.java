package amar.oreo.commands.game;

import amar.oreo.commands.commandinterface;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;

import java.util.Random;
import java.awt.Color;

public class roshambo implements commandinterface {
  @Override
  public String getName(){
    return "roshambo";
  }
  @Override
  public String getDescription(){
    return "rock-paper-scisors";
  }
  @Override
  public void executeSlash(SlashCommandInteractionEvent event){
    OptionMapping user = event.getOption("move");
    String bot = botMove();
    String userMove = user.getAsString();
    if (userMove==null){
      event.reply("You must make a move!").setEphemeral(true).queue();
      return;
    }
    switch (userMove){
      case "rock"->
      {
      if (bot.equals("rock")){
        event.reply("TIE! bot move was rock too!").queue();
      } else if (bot.equals("paper")){
        event.reply("LOSS! bot move was paper.").queue();
      } else {
        event.reply("WIN! bot move was scissors.").queue();
       }
      }
      case "paper"->
     {
      if (bot.equals("rock")){
        event.reply("WIN! bot move was rock.").queue();
      } else if (bot.equals("paper")){
        event.reply("TIE! bot move was paper too!").queue();
      } else {
        event.reply("LOSS! bot move was scissors.").queue();
      }
    } 
      case "scissors"->
      {
      if (bot.equals("rock")){
        event.reply("LOSS! bot move was rock.").queue();
      } else if (bot.equals("paper")){
        event.reply("WIN! bot move was paper").queue();
      } else {
        event.reply("TIE! bot move was scissors too!").queue();
       }  
      }
    }
  }
  public String botMove(){
    Random random = new Random();
    String [] options = {"rock","paper","scissors"};
    int e = random.nextInt(options.length);
    return options[e];
  }
}