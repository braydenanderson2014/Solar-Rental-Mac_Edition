package com.solarrentalmac.Logging;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import com.solarrentalmac.Main.SettingsController;

public class MessageProcessor {
    public static ArrayList<String> messages = new ArrayList<String>();
    public static ArrayList<Boolean> visibleToConsole = new ArrayList<Boolean>();
    public static ArrayList<String> messageTypes = new ArrayList<String>();
    public static ArrayList<String> sortedMessages = new ArrayList<String>();
    public static ArrayList<String> sortedMessageTypes = new ArrayList<String>();
    public static ArrayList<Boolean> sortedVisibleToConsole = new ArrayList<Boolean>();
    public static ArrayList<String> sortedDatetime = new ArrayList<String>();
    public static ArrayList<String> datetime = new ArrayList<String>();
    public static LocalDateTime currentTime = LocalDateTime.now();
    public static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    public static String dtime = currentTime.format(dtf);
    public static boolean dateTimeOn = true;

    public static String processMessage(int messageType, String message, Boolean visibleToConsole) {
        if (messageType == -2) {
            messages.add(message);
            messageTypes.add("[Error]: ");
            MessageProcessor.visibleToConsole.add(visibleToConsole);
            currentTime = LocalDateTime.now();
            datetime.add("[" + dtime + "]");
            return message;
        } else if (messageType == -1) {
            messages.add(message);
            messageTypes.add("[Warning]: ");
            MessageProcessor.visibleToConsole.add(visibleToConsole);
            currentTime = LocalDateTime.now();
            datetime.add("[" + dtime + "]");
            return message;
        } else if (messageType == 0) {
            messages.add(message);
            messageTypes.add("[Info]: ");
            MessageProcessor.visibleToConsole.add(visibleToConsole);
            currentTime = LocalDateTime.now();
            datetime.add("[" + dtime + "]");
            return message;
        } else if (messageType == 1) {
            messages.add(message);
            messageTypes.add("[System]: ");
            MessageProcessor.visibleToConsole.add(visibleToConsole);
            currentTime = LocalDateTime.now();
            datetime.add("[" + dtime + "]");
            return message;
        } else if (messageType == 2) {
            messages.add(message);
            messageTypes.add("[Debug]: ");
            MessageProcessor.visibleToConsole.add(visibleToConsole);
            currentTime = LocalDateTime.now();
            datetime.add("[" + dtime + "]");
            return message;
        } else {
            processMessage(-1, "Invalid Message Type sent to Processor: " + messageType + " Message:" + message,
                    visibleToConsole);
            return null;
        }
    }

    public static Boolean sortMessages() {
        // Use checkSetting to see if the "datetime" setting exists.
        if (SettingsController.checkSetting("datetime")) {
            // If it does, use the value in the setting.
            dateTimeOn = SettingsController.getSetting("datetime").equals("true");
        } else {
            // If it does not exist, assume dateTimeOn should be true.
            dateTimeOn = true;
        }
    
        MessageProcessor.processMessage(2, "Sorting Messages for console", true);
    
        for (int i = 0; i < messages.size(); i++) {
            if (visibleToConsole.get(i)) {
                if (dateTimeOn) {
                    if (shouldDisplayMessage(messageTypes.get(i))) {
                        sortedMessages.add(datetime.get(i) + " " + messageTypes.get(i) + ": " + messages.get(i));
                        sortedDatetime.add(datetime.get(i));
                        sortedMessageTypes.add(messageTypes.get(i));
                        sortedVisibleToConsole.add(visibleToConsole.get(i));
                    }
                } else {
                    if (shouldDisplayMessage(messageTypes.get(i))) {
                        sortedMessages.add(messageTypes.get(i) + ": " + messages.get(i));
                        sortedDatetime.add(datetime.get(i));
                        sortedMessageTypes.add(messageTypes.get(i));
                        sortedVisibleToConsole.add(visibleToConsole.get(i));
                    }
                }
            }
        }
        // Rest of the code
        return true;
    }
    
    
    

    private static boolean shouldDisplayMessage(String messageType) {
        String displaySetting = SettingsController.getSetting("Display " + messageType + " Messages");
    
        // If the setting is not found, set a default value based on the message type.
        if (displaySetting == null) {
            MessageProcessor.processMessage(2, "Display Setting: " + displaySetting, true);
            switch (messageType) {
                case "Info ":
                case "Warning ":
                case "Error ":
                case "System ":
                case "Debug ":
                    displaySetting = "true";
                break;

                default:
                    // This should never happen, but it might be worth logging or throwing an error.
                    displaySetting = "false";
                    MessageProcessor.processMessage(2, "Display Setting: " + displaySetting, true);
                    break;
            }
        }
    
        // Returns true only if the setting equals "true".
        return "true".equals(displaySetting);
    }
    
    
}
