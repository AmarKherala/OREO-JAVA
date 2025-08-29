package amar.oreo.util;

import io.github.cdimascio.dotenv.Dotenv;

public class config {
  
  private static final Dotenv loader = Dotenv.configure().directory("src/main/res/config").load();
  
  public static String token(){
    return loader.get("BOT_TOKEN");
  }
}