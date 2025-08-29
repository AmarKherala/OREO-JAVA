package amar.oreo.events.tags;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class tags extends ListenerAdapter {

    private final Map<String, String> tags = new HashMap<>();
    private final Gson gson = new Gson();
    private final File file = new File("src/main/res/tags/tags.json");

    public tags() {
        loadTags();
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) return; 

        String raw = event.getMessage().getContentRaw();
        
        String raww = event.getMessage().getContentRaw().toLowerCase();
    if (raww.startsWith("o") && raww.length() > 1) {
      String tagNa = raww.substring(1).toLowerCase();
      if (tags.containsKey(tagNa)){
        event.getChannel().sendMessage(tags.get(tagNa)).queue();
      }
    }
        if (!raw.startsWith("otag")) return;

        String[] args = raw.split("\\s+", 3);

        if (args.length < 2) {
            event.getChannel().sendMessage("Usage: `otag <add| list |remove> <name> [content]`").queue();
            return;
        }

        String subCommand = args[1].toLowerCase();

        switch (subCommand) {
            case "add":
                if (args.length < 3) {
                    event.getChannel().sendMessage("Usage: `otag add <name> <content>`").queue();
                    return;
                }
                String[] nameAndContent = args[2].split("\\s+", 2);
                if (nameAndContent.length < 2) {
                    event.getChannel().sendMessage("Please provide both a tag name and content.").queue();
                    return;
                }
                String tagName = nameAndContent[0].toLowerCase();
                String content = nameAndContent[1];
                tags.put(tagName, content);
                saveTags();
                event.getChannel().sendMessage("Tag `" + tagName + "` added!").queue();
                break;
 
            case "list":
              if (tags.isEmpty()) {
                event.getChannel().sendMessage("No tags available.").queue();
                  } else {
             event.getChannel().sendMessage("Tags: " + String.join(", ", tags.keySet())).queue();
                  }
                 break;


 
            case "remove":
                if (args.length < 3) {
                    event.getChannel().sendMessage("Usage: `otag remove <name>`").queue();
                    return;
                }
                String nameToRemove = args[2].toLowerCase();
                if (tags.remove(nameToRemove) != null) {
                    saveTags();
                    event.getChannel().sendMessage("Tag `" + nameToRemove + "` removed!").queue();
                } else {
                    event.getChannel().sendMessage("Tag `" + nameToRemove + "` not found.").queue();
                }
                break;

            default:
                event.getChannel().sendMessage("Unknown subcommand. Use `add`, `get`, or `remove`.").queue();
                break;
        }
    }

    private void saveTags() {
        try (FileWriter writer = new FileWriter(file)) {
            gson.toJson(tags, writer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadTags() {
        if (!file.exists()) return;
        try (FileReader reader = new FileReader(file)) {
            Type type = new TypeToken<Map<String, String>>(){}.getType();
            Map<String, String> loaded = gson.fromJson(reader, type);
            if (loaded != null) tags.putAll(loaded);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
