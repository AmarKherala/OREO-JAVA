package amar.oreo.commands.info;

import amar.oreo.commands.commandinterface;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.EmbedBuilder;

import java.awt.Color;

public class avatar implements commandinterface {
  @Override
  public String getName(){
    return "avatar";
  }
  @Override
  public String getDescription(){
    return "get user avatar";
  }
  @Override
  public void executeSlash(SlashCommandInteractionEvent event){
    OptionMapping u = event.getOption("user");
    Member m = u.getAsMember();
    User um = event.getUser();
    String a = m.getEffectiveAvatarUrl();
    EmbedBuilder em = new EmbedBuilder();
    em.setTitle("Avatar of user " + m.getAsMention());
    em.setImage(a);
    em.setFooter("Requested by: " + um.getName(), um.getEffectiveAvatarUrl());
    em.setColor(Color.BLUE);
    event.replyEmbeds(em.build()).queue();
  }
}