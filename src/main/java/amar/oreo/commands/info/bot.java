package amar.oreo.commands.info;

import amar.oreo.commands.commandinterface;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.User;

import java.awt.Color;

public class bot implements commandinterface {
  @Override
  public String getName(){
    return "botinfo";
  }
  @Override
  public String getDescription(){
    return "info about the bot";
  }
  public void executeSlash(SlashCommandInteractionEvent event){
    User usr = event.getUser();
    EmbedBuilder em = new EmbedBuilder();
    em.setTitle("information about Oreo.JAVA!");
    // thumbnail not needed atm
    // em.setThumbnail("https://cdn.discordapp.com/attachments/1346385034222764062/1408199620668166194/1754992550982.jpg?ex=68a8df98&is=68a78e18&hm=ca5a5e91e46c582cdbd248e33649b6c2b6cfbbc50e817b574a1ea567ff4b8380&");
    em.setDescription("Oreo.JAVA is a bot created with <3 with java using Java-Discord-Libary (JDA) version 5.\n I was created by amaroreo as a way to learn java properly!\n **AND IM NOT MEANT TO BE USED ANYWHERE ELSE OTHER THAN OREOS BAR AT THE MOMENT!**");
    em.setColor(Color.BLUE);
    em.setFooter("Requested by " + usr.getName(), usr.getAvatarUrl());
    event.replyEmbeds(em.build()).queue();
  }
}