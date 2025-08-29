#!/bin/bash
SRC_DIR="src/main"
OUT_DIR="out"
MAIN_CLASS="amar.oreo.main"

mkdir -p $OUT_DIR

BOT_PID=""

start_bot() {
    echo "Deleting old compiled classes..."
    rm -rf out/amar
    echo "Compiling..."
    javac -cp "lib/*" -d out $(find src/main -name "*.java")
    if [ $? -eq 0 ]; then
        echo "Starting bot..."
        java -cp "out:lib/*" $MAIN_CLASS &
        BOT_PID=$!
        echo "Bot running with PID $BOT_PID"
    else
        echo "Compilation failed. Waiting for changes..."
    fi
}

# Initial run
start_bot

# Watch for file changes
while inotifywait -e modify -r $SRC_DIR; do
    echo "Change detected. Restarting bot..."
    if [ ! -z "$BOT_PID" ]; then
        echo "Killing old bot PID $BOT_PID"
        kill $BOT_PID 2>/dev/null
        wait $BOT_PID 2>/dev/null
    fi
    start_bot
done
