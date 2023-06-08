package com.solarrentalmac.Logging;

import java.time.LocalDateTime;


import com.solarrentalmac.Main.SettingsController;

import java.util.*;

public class MessageProcessor {

    private static int maxMessages = 5;

    private static List<Message> messages = new LinkedList<>();
    private static Map<Integer, String> messageTypes = Map.of(
        -2, "[Error]: ",
        -1, "[Warning]: ",
        0, "[Info]: ",
        1, "[System]: ",
        2, "[Debug]: "
    );
    private static List<String> colorCodes = List.of(
        "\u001B[30m", // BLACK
        "\u001B[31m", // RED
        "\u001B[32m", // GREEN
        "\u001B[33m", // YELLOW
        "\u001B[34m", // BLUE
        "\u001B[35m", // PURPLE
        "\u001B[36m", // CYAN
        "\u001B[37m", // WHITE
        "\u001B[90m", // BRIGHT_BLACK
        "\u001B[91m", // BRIGHT_RED
        "\u001B[92m", // BRIGHT_GREEN
        "\u001B[93m", // BRIGHT_YELLOW
        "\u001B[94m", // BRIGHT_BLUE
        "\u001B[95m", // BRIGHT_PURPLE
        "\u001B[96m", // BRIGHT_CYAN
        "\u001B[97m"  // BRIGHT_WHITE
    );

    private static Map<Integer, Integer> messageTypeColorIndex = new HashMap<>();
    private static Map<Integer, String> messageColors = new HashMap<>() {{
        put(-2, "\033[0;31m"); // RED
        put(-1, "\033[0;33m"); // YELLOW
        put(0, "\033[0;32m");  // GREEN
        put(1, "\033[0;34m");  // BLUE
        put(2, "\033[0;36m");  // CYAN
    }};

    private static Map<Integer, Boolean> messageTypeVisibility = new HashMap<>() {{
        put(-2, true);
        put(-1, true);
        put(0, true);
        put(1, true);
        put(2, true);
    }};

    static class Message {
        int messageType;
        String message;
        Boolean visibleToConsole;
        LocalDateTime dateTime;

        Message(int messageType, String message, Boolean visibleToConsole) {
            this.messageType = messageType;
            this.message = message;
            this.visibleToConsole = visibleToConsole;
            this.dateTime = LocalDateTime.now();
        }

        @Override
        public String toString() {
            return messageColors.get(messageType) + messageTypes.get(messageType) + " [" + dateTime + "] " + message + "\033[0m";  // RESET color after printing the message
        }
    }

    public static void processMessage(int messageType, String message, Boolean visibleToConsole) {
        if (!messageTypes.containsKey(messageType)) {
            processMessage(-1, "Invalid Message Type sent to Processor: " + messageType + " Message: " + message,
                    visibleToConsole);
            return;
        }

        Message newMessage = new Message(messageType, message, visibleToConsole);
        messages.add(newMessage);
        AutoGenerateLog.log.add(newMessage.toString());

        while (messages.size() > maxMessages) {
            messages.remove(0);
        }
    }

    public static void displayMessages() {
        for (Message message : messages) {
            if (messageTypeVisibility.get(message.messageType) && message.visibleToConsole) {
                System.out.println(message);
                message.visibleToConsole = false;
            }
        }
    }

    public static void setMessageTypeVisibility(int messageType, boolean isVisible) {
        if (messageTypes.containsKey(messageType)) {
            messageTypeVisibility.put(messageType, isVisible);
        }
    }

    public static void setMessageTypeColor(int messageType, String color) {
        if (messageTypes.containsKey(messageType)) {
            messageColors.put(messageType, color);
        }
    }

    public static void setMaxMessages(int newMax) {
        maxMessages = newMax;
    }

    public static void cycleMessageTypeColor(int messageType) {
        if (!messageTypes.containsKey(messageType)) {
            processMessage(-1, "Invalid Message Type sent to Processor: " + messageType, true);
            return;
        }

        // Initialize color index if it's not set yet
        messageTypeColorIndex.putIfAbsent(messageType, 0);

        // Increment the color index for the message type
        int nextColorIndex = messageTypeColorIndex.get(messageType) + 1;

        // If the index exceeds the color list size, reset it to 0
        if (nextColorIndex >= colorCodes.size()) {
            nextColorIndex = 0;
        }

        // Update the color for the message type
        messageColors.put(messageType, colorCodes.get(nextColorIndex));

        // Update the index for next time
        messageTypeColorIndex.put(messageType, nextColorIndex);
    }
}

