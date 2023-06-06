package com.solarrentalmac.Logging;

import java.util.ArrayList;

import com.solarrentalmac.Main.SettingsController;

public class AutoGenerateLog {
    ArrayList<String> log = new ArrayList<String>();
    String LogDirectory = SettingsController.getSetting("Log Directory");
    public static void autoDump() {
        for(int i = 0; i < MessageProcessor.messages.size(); i++) {
            String messageType = MessageProcessor.messageTypes.get(i);
            String message = MessageProcessor.messages.get(i);
            String color = ConsoleHandler.getColorByMessageType(messageType);
            System.out.println(color + message);
        }
    }      
}