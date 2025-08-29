package amar.oreo.events.buttons;

import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;

public class mathsbutton extends ListenerAdapter {
  @Override
  public void onButtonInteraction(ButtonInteractionEvent event){
    if (event.getComponentId().equals("answer1")){
      event.reply("Correct!").queue();
      event.getMessage().editMessageComponents().queue(); 
    } else if (event.getComponentId().equals("answer2") || event.getComponentId().equals("answer3")){
      event.reply("Incorrect!").queue();
      event.getMessage().editMessageComponents().queue();
  }
 }
}