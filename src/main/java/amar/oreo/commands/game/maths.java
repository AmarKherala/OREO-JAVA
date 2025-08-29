package amar.oreo.commands.game;

import amar.oreo.commands.commandinterface;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import net.dv8tion.jda.api.EmbedBuilder;

import java.util.Random;
import java.util.Collections;
import java.util.ArrayList;


public class maths implements commandinterface {

private static final Random random = new Random();

  @Override
  public String getName(){
    return "maths";
  }
  @Override 
  public String getDescription(){
    return "simple math game";
  }
  @Override
  public void executeSlash(SlashCommandInteractionEvent event){
    
    int randomnumber1 = random.nextInt(100)+1;
    int randomnumber2 = random.nextInt(100)+1;
    
    int a = random.nextInt(50)+1;
    int b = random.nextInt(50)+1;
    String [] operator = {"+","-","*"};
    int index = random.nextInt(operator.length);
    String op = operator[index];
    int cAnswer = correctAnswer(a,b,op);
    
    EmbedBuilder em = new EmbedBuilder();
    em.setTitle("Q");
    em.setDescription("Whats " + a + " " + op + " " + b + "?");
    
    ArrayList <Button> buttons = new ArrayList<>();
    buttons.add(Button.primary("answer1" , String.valueOf(cAnswer)));
    buttons.add(Button.primary("answer2", String.valueOf(randomnumber2)));
    buttons.add(Button.primary("answer3", String.valueOf(randomnumber1)));
    Collections.shuffle(buttons);
    event.replyEmbeds(em.build()).addActionRow(
     buttons).queue();
  }
  
  public static int correctAnswer(int a,int b,String op){
    switch(op){
      case "+" : return a + b;
      case "*" : return a * b;
      case "-" : return a - b;
      default : return -1;
    }
  }
}