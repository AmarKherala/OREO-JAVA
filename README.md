Oreo.JAVA Source Code! 
Well, this is the first bot program i wrote in java. Its not perfect indeed but it functions well enough for most standards. 
If you want to set up your bot with this, here: 
1 - Copy the repo (duh). 
2 - Make a (.env) file in "src/main/res/config" and copy to it the following :

    BOT_TOKEN=YOUR_BOT_TOKEN

 | you can change the path you want your token sorter to be at on "util/config.java"
3 - Compile and run :
  to automate compiling and restarting the bot in each change I've made updated.sh script. 
  Basically what it does is that it detects any change in the class path and executes the following :
  ## rm - rf out/OUT_DIR : Remove old compiled classes. 
  ## javac -cp "lib/**" -d OUT_DIR $(find src/main -name "*. java") : Re-compile. 
  ## java -cp "lib/*:$OUT_DIR" $CLASS_PATH : Run the bot (make the bot go online. 
  Voallah! 
  Thanks!! 
