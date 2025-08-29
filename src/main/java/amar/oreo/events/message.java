package amar.oreo.events;

import amar.oreo.util.amarlogger;

// api
import amar.oreo.api.gary.gary;
import amar.oreo.api.naoko.naoko;

//** ** **
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.Member;

import java.io.IOException;

public class message extends ListenerAdapter {

  private final gary gary = new gary();
  private final naoko naoko = new naoko();
  
  @Override
  public void onMessageReceived(MessageReceivedEvent event){
   
    String userMessage = event.getMessage().getContentRaw();
   
    User user = event.getAuthor();
    String id = user.getId();

    if (event.getAuthor().isBot()) return;
    
 
    // invite to Oreo's Bar 
    if (userMessage.equalsIgnoreCase("oinvite") && !event.getAuthor().isBot()){
      event.getChannel().sendMessage("https://discord.gg/4s9YUyJaSW").queue();
    }

    // funny whales easter-eggs
    if (userMessage.contains("whales")){
      event.getMessage().addReaction(Emoji.fromCustom("whalesJumpScare", 1403882718244110500L, false)).queue();
    }
 
    // gary api implementation
    String res =null;
    if (userMessage.equalsIgnoreCase("Ogary")){
       res = gary.fetchUrl("https://api.garythe.cat/gary");}
    if (userMessage.equalsIgnoreCase("Ogoober")){
       res = gary.fetchUrl("https://api.garythe.cat/goober");}
    if (userMessage.equalsIgnoreCase("Ojoke")){
      res = gary.fetchJoke("https://api.garythe.cat/joke");
    }
    if (res!=null) {event.getChannel().sendMessage(res).queue();}
    
        // naoko command fr
    if (userMessage.equalsIgnoreCase("Onaoko")){
      String na = naoko.randomNaokosImage();
      event.getChannel().sendMessage(na).queue();
    }
    
    if (userMessage.startsWith("naokoadd")){
      naokoHandler(event, userMessage, id, user);
    }
  }
  
  
  // Add naoko image handler
  private void naokoHandler(MessageReceivedEvent event, String userMessage, String id, User user){
      
      amarlogger.info("User [" + user.getName() + "] tried executing [[naokoadd]]");
      
      if (!(id.equalsIgnoreCase("750064880861708421") || id.equalsIgnoreCase("467460043340513290"))){
        event.getChannel().sendMessage("You're not allowed to do this.").queue();
        return;
      }
     
     String url = userMessage.substring("naokoadd".length()).trim();
     
     // -- check if image already exists --
     try{
     if (naoko.checkImage(url)){
       amarlogger.info("User: ["+ user.getName()+"] tried to add an image, but it already exists in naoko.json!!!");
       event.getChannel().sendMessage("Opps! Image already exists!").queue();
       return;
       }
     } catch (IOException a){
       a.printStackTrace();
       amarlogger.error("Someting went wrong..");
     }
     // if not, continue.
     
     if (url.isEmpty()) {
        event.getChannel().sendMessage("Usage: `naokoadd <URL>`").queue();
        return;
    }
     
   
     if (url.startsWith("https://cdn.discordapp.com/attachments/")) {
       
     try {
       naoko.addNaokoImage(url);
       amarlogger.info("Image [["+url+"]] added to [[naoko.json]] by User: [" + user.getName() +"]");
       event.getChannel().sendMessage("Image added!").queue();
     } catch (IOException e){
       e.printStackTrace();
       amarlogger.info("IOException while trying to write to file [[naoko.json]] by user: [" + user.getName() + "].");
      }
     } else {
       event.getChannel().sendMessage("Please insert a valid url").queue();
      }
   }
}