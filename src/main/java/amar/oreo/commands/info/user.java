package amar.oreo.commands.info;

import amar.oreo.commands.commandinterface;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.EmbedBuilder;


import java.awt.Color;
import java.time.LocalDate;
import java.time.OffsetDateTime;

public class user implements commandinterface {
  @Override
  public String getName(){
    return "userinfo";
  }
  @Override
  public String getDescription(){
    return "info about user";
  }
  @Override
  public void executeSlash(SlashCommandInteractionEvent event){
    OptionMapping user = event.getOption("user");
    Member ur = user!=null ? user.getAsMember() : null;
    Guild guild = event.getGuild();
    if (ur!=null){
      try {
        // requester name and avatar
        User req = event.getUser();
        String reqName = req.getName();
        String reqAv = req.getAvatarUrl();
        
        // user info -->
        String name = ur.getAsMention();
        String id = ur.getId();
        String avatar = ur.getEffectiveAvatarUrl();
        LocalDate joined = ur.getTimeJoined().toLocalDate();
        LocalDate time = ur.getTimeCreated().toLocalDate();
        //** ** ** ** **
        
        EmbedBuilder em = new EmbedBuilder();
        em.setTitle("USER INFO");
        em.setThumbnail(avatar);
        em.setDescription(" User: " + name + "\n UserId: " + id + "\n ServerJoinedTime: " + joined + "\n CreatedAccountAt: " + time);
        em.setColor(Color.BLUE);
        em.setFooter("Requested by "+ reqName, reqAv);
        event.replyEmbeds(em.build()).queue();
      } catch (Exception e){
        e.printStackTrace();
      }
    } else {
      event.reply("What...could not find user!").queue();
   }
  }
}