package com.solarrentalmac.Setup;

import java.io.File;


import com.solarrentalmac.Logging.MessageProcessor;
import com.solarrentalmac.Main.SettingsController;

public class FirstTimeChecker {
    private static boolean firstTime;
   
    private static Boolean readFirstTime(){
        firstTime = Boolean.parseBoolean(SettingsController.getSetting("firstTime"));
        MessageProcessor.processMessage(2, "First Time: " + firstTime, false);
        return firstTime;
    }

    public static Boolean checkFirstTime(){
        String SettingsFile = SettingsController.SettingsFilePath;
        File file = new File(SettingsFile);
        if(file.exists()){
            MessageProcessor.processMessage(2, "Settings file exists.", false);
            MessageProcessor.processMessage(1, "Checking if first time.", true);
            return readFirstTime();
        }else{
            firstTime = true;
            MessageProcessor.processMessage(2, "Settings file does not exist.", false);
            MessageProcessor.processMessage(1, "Entering First Time setup.", true);
            return firstTime;
        }
    }
}
