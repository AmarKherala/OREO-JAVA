package amar.oreo.events.buttons;

import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;

// for role buttons
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import net.dv8tion.jda.api.interactions.components.buttons.ButtonStyle;
import java.awt.Color;
// *** *** *** ***

public class rolesbutton extends ListenerAdapter {
  @Override
  public void onButtonInteraction(ButtonInteractionEvent  event){
    String id = event.getComponentId();
      if (id.startsWith("color:")) handleAdd(event, id);
      else if (id.startsWith("yes:")) handleRemove(event,id);
  }
  public void handleAdd(ButtonInteractionEvent event, String id){
   Guild g = event.getGuild();
    Member m = event.getMember();
   
    if (m==null||g==null){
      event.reply("Something went wrong").setEphemeral(true).queue();
    }
    String roleKey = id.substring("color:".length());
    String roleId = null;
    switch (roleKey){
      case "purple" -> roleId ="1401951980611702925";
            case "orange" -> roleId ="1308846283766894624";
            case "yellow" ->roleId = "1306218380306681916";
            case "red" -> roleId = "1307016237326401569";
            case "lightblue" -> roleId = "1307766379234988153";
            case "catgirl" -> roleId = "1307366663133597726";
            case "black" -> roleId = "1309075735272488960";
            case "green" -> roleId = "1405197124806643782";
            default ->roleId = null;
    }
    if (roleId==null) {
      event.reply("Role not found").setEphemeral(true).queue();
      return;
    }
    Role r = g.getRoleById(roleId);
    if (r==null){ event.reply("Unknown role!").setEphemeral(true).queue();
    return;
    }
    if(m.getRoles().contains(r)){
      sendRoleCheck(m,r,event);
      return;
    }
    g.addRoleToMember(m, r).queue(
    success -> event.reply("Role **" + r.getName() + "** has been added to you!").setEphemeral(true).queue(),
      error -> event.reply("Failed to add the role: " + error.getMessage()).setEphemeral(true).queue()
        ); 
  }
  public void handleRemove(ButtonInteractionEvent event, String id){
    Guild g = event.getGuild();
    Member m = event.getMember();
    
    if (g == null || m == null) {
     event.reply("Could not get guild or member information.").setEphemeral(true).queue();
     return;
     }
     
     String[] parts = id.split(":");
      if (parts.length < 3) {
      event.reply("Role information missing.").setEphemeral(true).queue();
      return;
        }

     String optionKey = parts[1];
     String roleId = parts[2];

        Role r = g.getRoleById(roleId);
        if (r == null) {
            event.reply("Role no longer exists on this server.").setEphemeral(true).queue();
            return;
        }

        switch (optionKey) {
    case "do" -> g.removeRoleFromMember(m, r).queue(
       success -> event.reply("Role " + r.getName() + " has been removed from you!").setEphemeral(true).queue(),
      error -> event.reply("Failed to remove role " + r.getName() + "!").setEphemeral(true).queue()
            );
    case "dont" -> event.reply("Enjoy your color!").setEphemeral(true).queue();
    default -> event.reply("Something went wrong").setEphemeral(true).queue();
    }
  }
  public void sendRoleCheck(Member m, Role r, ButtonInteractionEvent event){
    EmbedBuilder emb = new EmbedBuilder();
        emb.setTitle("You already have this role!");
        emb.setDescription("Remove it?");
        emb.setColor(Color.BLUE);

        Button yes = Button.of(ButtonStyle.PRIMARY, "yes:do:" + r.getId(), "Remove the role");
        Button no = Button.of(ButtonStyle.PRIMARY, "yes:dont:" + r.getId(), "Don't remove the role");

        event.replyEmbeds(emb.build())
            .setEphemeral(true)
            .addActionRow(yes, no)
            .queue();
  }
}