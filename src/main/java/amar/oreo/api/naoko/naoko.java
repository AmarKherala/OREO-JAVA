package amar.oreo.api.naoko;
// this isnt really an api, but i didnt want to put the embed links in the main message event class.

import com.google.gson.*;
import java.util.Random;
import java.io.*;
import java.nio.file.*;
import java.io.IOException;

public class naoko {
  
private static final Gson gson = new Gson();
private static final Random ran = new Random();
private static final String path = "src/main/res/images/naoko.json";
  
  public static String randomNaokosImage(){
    
    try (FileReader read = new FileReader(path)){
      JsonObject obj = gson.fromJson(read, JsonObject.class);
      JsonArray arr = obj.getAsJsonArray("naoko");
      if (arr==null || arr.size()==0){
        return null;
      }
      return arr.get(ran.nextInt(arr.size())).getAsString();
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
   }
   public void addNaokoImage(String url) throws IOException {
   
     String json = new String(Files.readAllBytes(Paths.get(path)));
     JsonObject ob = gson.fromJson(json, JsonObject.class);
     
     JsonArray ara = ob.getAsJsonArray("naoko");
     
       ara.add(url);
       
     try (FileWriter rite = new FileWriter(path)) {
       gson.toJson(ob, rite);
      }
    }
    public boolean checkImage(String url) throws IOException{
      String j = new String(Files.readAllBytes(Paths.get(path)));
      JsonObject obe = gson.fromJson(j, JsonObject.class);
      JsonArray array = obe.getAsJsonArray("naoko");
      for (int i=0; i< array.size();i++){
        if (array.get(i).getAsString().equals(url)){
          return true;
        } 
      }
      return false;
    }
  }