package amar.oreo.api.gary;

import okhttp3.*;
import org.json.JSONObject;

public class gary {
  
  private OkHttpClient gary = new OkHttpClient();
  
  public String fetchUrl(String url){
   
      Request req = new Request.Builder().url(url).build();
     try (Response res = gary.newCall(req).execute()) {
      if (res.body()==null) return null;
      String json = res.body().string();
      JSONObject obj = new JSONObject(json);
      return obj.getString("url");
      
    } catch (Exception e){
      e.printStackTrace();
      return null;
    }
  }
  public String fetchJoke(String joke){
    Request req = new Request.Builder().url(joke).build();
     try (Response res = gary.newCall(req).execute()) {
      if (res.body()==null) return null;
      String json = res.body().string();
      JSONObject obj = new JSONObject(json);
      return obj.getString("joke");
      
    } catch (Exception e){
      e.printStackTrace();
      return null;
    }
  }
}