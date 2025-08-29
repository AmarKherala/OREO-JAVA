package amar.oreo.commands.info;

import amar.oreo.commands.commandinterface;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.Member;
import java.awt.Color;
import java.time.LocalDate;

public class server implements commandinterface {
  @Override
  public String getName(){
    return "srverinfo";
  }
  @Override
  public String getDescription(){
    return "info about server";
  }
  @Override
  public void executeSlash(SlashCommandInteractionEvent event){
    Guild guild = event.getGuild();
    User usr = event.getUser();
    
    // get server INFO
    String name = guild.getName();
    String id = guild.getId();
    String icon = guild.getIconUrl();
    Member owner = guild.getOwner();
    LocalDate time = guild.getTimeCreated().toLocalDate();
    int members = guild.getMemberCount();
    //"" "" "" "" "" "" 
    
    if (guild!=null){
      try {
        
        User req = event.getUser();
        
    EmbedBuilder em = new EmbedBuilder();
    em.setTitle("SERVER INFO");
    em.setThumbnail(icon);
    em.setDescription("### Server Name : \n" + name + "\n### Server ID : \n" + id + "\n### Server Owner : \n" + owner.getAsMention() + "\n### Server Creation Date : \n" + time + "\n### Server Members : \n"+ members);
    em.setColor(Color.BLUE);
    em.setFooter("Requested by " + req.getName(), req.getAvatarUrl());
    event.replyEmbeds(em.build()).queue();
      } catch (Exception e){
        e.printStackTrace();
      }
    } else {
      event.reply("Are you sure you are on a server?").queue();
    }
  }
}