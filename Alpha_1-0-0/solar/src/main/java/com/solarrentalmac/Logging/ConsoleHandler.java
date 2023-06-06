package com.solarrentalmac.Logging;

import com.solarrentalmac.Assets.Logo;
import com.solarrentalmac.Assets.customScanner;
import com.solarrentalmac.Main.SettingsController;
import com.solarrentalmac.Assets.TxtColorTable;
public class ConsoleHandler {
    public static boolean isErrorEnabled = true;
    public static boolean isWarningEnabled = true;
    public static boolean isInfoEnabled = true;
    public static boolean isSystemEnabled = true;
    public static boolean isDebugEnabled = true;
    public static String ErrorColor = "Red";
    public static String WarningColor = "Yellow";
    public static String InfoColor = "Blue";
    public static String SystemColor = "Green";
    public static String DebugColor = "Purple";
    
    public static String getConsole() {
        MessageProcessor.sortMessages();
        int size = MessageProcessor.sortedMessages.size();
        int maxMessagesToDisplay = 5; // replace with your variable
        int startMessageIndex = Math.max(0, size - maxMessagesToDisplay);
        
        if (size > 0) {
            System.out.println("Console: ");
            
            for (int i = startMessageIndex; i < size; i++) {
                if(MessageProcessor.sortedVisibleToConsole.get(i) == true) {
                    String messageType = MessageProcessor.sortedMessageTypes.get(i);
                    String message = MessageProcessor.sortedMessages.get(i);
                    String color = getColorByMessageType(messageType);
                    System.out.println(color + message);
                    
                    // Set the visibility to false so it won't be displayed again
                    MessageProcessor.sortedVisibleToConsole.set(i, false);
                }
            }
        }
        return null;
    }
    
    
    
    public static String getColorByMessageType(String messageType) {
        switch (messageType) {
            case "[Error]: ":
                return TxtColorTable.getColor("Error");
            case "[Warning]: ":
                return TxtColorTable.getColor("Warning");
            case "[Info]: ":
                return TxtColorTable.getColor("Info");
            case "[System]: ":
                return TxtColorTable.getColor("System");
            case "[Debug]: ":
                return TxtColorTable.getColor("Debug");
            default:
                MessageProcessor.processMessage(-1, "Invalid Message Type: ", true);
                MessageProcessor.processMessage(2, "Invalid Message Type: " + messageType + "(CONSOLE HANDLER; GETCONSOLE())", false);
                return "";
        }
    }
    
    public static void ConsoleSettings(){
        Logo.getLogo();
        System.out.println("Console Settings");
        System.out.println("[Error]: Error Messages: " + isErrorEnabled);
        System.out.println("[WARNING]: Warning Messages: " + isWarningEnabled);
        System.out.println("[INFO]: Info Messages: " + isInfoEnabled);
        System.out.println("[System]: System Messages: " + isSystemEnabled);
        System.out.println("[Debug]: Debug Messages: " + isDebugEnabled);
        System.out.println("[ErrorColor]: Error Color: " + ErrorColor);
        System.out.println("[WarningColor]: Warning Color: " + WarningColor);
        System.out.println("[InfoColor]: Info Color: " + InfoColor);
        System.out.println("[SystemColor]: System Color: " + SystemColor);
        System.out.println("[DebugColor]: Debug Color: " + DebugColor);
        System.out.println("Type the name of the setting you want to change, or type 'back' to go back.");
        String settingToChange = customScanner.nextLine().toLowerCase().trim();
        switch (settingToChange) {
            case "error":
                if(isErrorEnabled){
                    isErrorEnabled = !isErrorEnabled;
                }else{
                    isErrorEnabled = true;
                }
                break;
            case "warning":
                    
                break;
            case "info":
                    
                break;
            case "system":
                        
                break;
            case "debug":

                break;
            case "errorcolor":
                            
                break;
            case "warningcolor":
                                
                break;
            case "infocolor":
                                    
                break;
            case "systemcolor":
                                        
                break;
            case "debugcolor":
                                            
                break;
            case "back":
                SettingsController.SettingsMenu();              
                break;
            default:
                MessageProcessor.processMessage(-1, "Invalid setting name. Please try again.", true);
                ConsoleSettings();
                break;
        }

    }
    public static boolean saveConsoleSettings() {
        return SettingsController.saveSettings();

    }   
    
}
