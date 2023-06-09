package com.solarrentalmac.Main;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Properties;

import com.solarrentalmac.Logging.MessageProcessor;

public class SettingsController {
    public static Properties Settings = new Properties();
    private static String userHome = System.getProperty("user.home");
    public static String SettingsFile = "settings.properties";
    public static String SettingsPath = userHome + "/SolarRentalMac/" + SettingsFile + "/";
    public static String SystemPath = userHome + "/SolarRentalMac/";
    public static String SettingsFilePath = SettingsPath + SettingsFile;

    public static String LoadSettings() {
        try{
            Settings.load(SettingsController.class.getResourceAsStream(SettingsFile));
            return Settings.toString();
        } catch (Exception e) {
            MessageProcessor.processMessage(-2, "Error loading settings file.", true);
            MessageProcessor.processMessage(-2, e.toString(), true);
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            String sStackTrace = sw.toString(); // stack trace as a string
            MessageProcessor.processMessage(2, sStackTrace, false);
            return null;
        }
    }

    public static boolean saveSettings() {
        try{
            Settings.store(new FileOutputStream(SettingsFile), null);
            return true;
        } catch (Exception e) {
            MessageProcessor.processMessage(-2, "Error saving settings file.", true);
            MessageProcessor.processMessage(-2, e.toString(), true);
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            String sStackTrace = sw.toString(); // stack trace as a string
            MessageProcessor.processMessage(2, sStackTrace, false);
            return false;
        }
    }

    public static String getSetting(String settingName) {
        MessageProcessor.processMessage(2, "Fetching: " + settingName, false);
        MessageProcessor.processMessage(2, "Setting Value Found: " + Settings.getProperty(settingName), false);
        return Settings.getProperty(settingName);
    }
    public static boolean setProperty(String settingName, String settingValue) {
        Settings.setProperty(settingName, settingValue);
        MessageProcessor.processMessage(2, "Setting: " + settingName + "Value: " + settingValue, false);
        MessageProcessor.processMessage(1, "Setting: " + settingName + "was updated by to: " + settingValue, false);
        return true;
    }

    public static boolean createSettingsFile() {
        File file = new File(SystemPath);
        if(!file.exists()){
            file.mkdirs();
        }
        MessageProcessor.processMessage(2, "Settings Directory Created", false);
        file = new File(SettingsFilePath);
        try{
            file.createNewFile();
            MessageProcessor.processMessage(2, "Settings File Created", false);
            setProperty("firstTime", "true");
            return true;
        } catch (Exception e) {
            MessageProcessor.processMessage(-2, "Error creating settings file.", true);
            MessageProcessor.processMessage(-2, e.toString(), true);
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            String sStackTrace = sw.toString(); // stack trace as a string
            MessageProcessor.processMessage(2, sStackTrace, false);
            return false;
        }
    }

    public static void ApplySettings() {
        MessageProcessor.processMessage(2, "Applying Settings", false);
        //Code to apply settings goes here
        String Setting = "Debug Color";
        if(checkSetting(Setting) == true) {
            MessageProcessor.processMessage(0, "Setting: " + Setting + " found.", true);
            MessageProcessor.processMessage(2, "Setting: " + Setting + " value: " + Settings.getProperty(Setting), false);
            String color = MessageProcessor.getColorCodeByName(Settings.getProperty(Setting));
            MessageProcessor.setMessageTypeColor(2, color);
        } else {
            MessageProcessor.processMessage(2, "Setting: " + Setting + " not found.", false);
        }
        Setting = "Info Color";
        if(checkSetting(Setting) == true) {
            MessageProcessor.processMessage(0, "Setting: " + Setting + " found.", true);
            MessageProcessor.processMessage(2, "Setting: " + Setting + " value: " + Settings.getProperty(Setting), false);
            String color = MessageProcessor.getColorCodeByName(Settings.getProperty(Setting));
            MessageProcessor.setMessageTypeColor(1, color);
        } else {
            MessageProcessor.processMessage(2, "Setting: " + Setting + " not found.", false);
        }
        Setting = "Warning Color";
        if(checkSetting(Setting) == true) {
            MessageProcessor.processMessage(0, "Setting: " + Setting + " found.", true);
            MessageProcessor.processMessage(2, "Setting: " + Setting + " value: " + Settings.getProperty(Setting), false);
            String color = MessageProcessor.getColorCodeByName(Settings.getProperty(Setting));
            MessageProcessor.setMessageTypeColor(-1, color);
        } else {
            MessageProcessor.processMessage(2, "Setting: " + Setting + " not found.", false);
        }
        Setting = "Error Color";
        if(checkSetting(Setting) == true) {
            MessageProcessor.processMessage(0, "Setting: " + Setting + " found.", true);
            MessageProcessor.processMessage(2, "Setting: " + Setting + " value: " + Settings.getProperty(Setting), false);
            String color = MessageProcessor.getColorCodeByName(Settings.getProperty(Setting));
            MessageProcessor.setMessageTypeColor(-2, color);
        } else {
            MessageProcessor.processMessage(2, "Setting: " + Setting + " not found.", false);
        }
        Setting = "System Color";
        if(checkSetting(Setting) == true) {
            MessageProcessor.processMessage(0, "Setting: " + Setting + " found.", true);
            MessageProcessor.processMessage(2, "Setting: " + Setting + " value: " + Settings.getProperty(Setting), false);
            String color = MessageProcessor.getColorCodeByName(Settings.getProperty(Setting));
            MessageProcessor.setMessageTypeColor(0, color);
        } else {
            MessageProcessor.processMessage(2, "Setting: " + Setting + " not found.", false);
        }

        Setting = "Display Debug Messages";
        if(checkSetting(Setting) == true) {
            MessageProcessor.processMessage(0, "Setting: " + Setting + " found.", true);
            MessageProcessor.processMessage(2, "Setting: " + Setting + " value: " + Settings.getProperty(Setting), false);
            MessageProcessor.setMessageTypeVisibility(2, Boolean.parseBoolean(getSetting(Setting)));
        } else {
            MessageProcessor.processMessage(2, "Setting: " + Setting + " not found.", false);
        }

        Setting = "Display Info Messages";
        if(checkSetting(Setting) == true) {
            MessageProcessor.processMessage(0, "Setting: " + Setting + " found.", true);
            MessageProcessor.processMessage(2, "Setting: " + Setting + " value: " + Settings.getProperty(Setting), false);
            MessageProcessor.setMessageTypeVisibility(1, Boolean.parseBoolean(getSetting(Setting)));
        } else {
            MessageProcessor.processMessage(2, "Setting: " + Setting + " not found.", false);
        }

        Setting = "Display Warning Messages";
        if(checkSetting(Setting) == true) {
            MessageProcessor.processMessage(0, "Setting: " + Setting + " found.", true);
            MessageProcessor.processMessage(2, "Setting: " + Setting + " value: " + Settings.getProperty(Setting), false);
            MessageProcessor.setMessageTypeVisibility(-1, Boolean.parseBoolean(getSetting(Setting)));
        } else {
            MessageProcessor.processMessage(2, "Setting: " + Setting + " not found.", false);
        }

        Setting = "Display Error Messages";
        if(checkSetting(Setting) == true) {
            MessageProcessor.processMessage(0, "Setting: " + Setting + " found.", true);
            MessageProcessor.processMessage(2, "Setting: " + Setting + " value: " + Settings.getProperty(Setting), false);
            MessageProcessor.setMessageTypeVisibility(-2, Boolean.parseBoolean(getSetting(Setting)));
        } else {
            MessageProcessor.processMessage(2, "Setting: " + Setting + " not found.", false);
        }

        Setting = "Display System Messages";
        if(checkSetting(Setting) == true) {
            MessageProcessor.processMessage(0, "Setting: " + Setting + " found.", true);
            MessageProcessor.processMessage(2, "Setting: " + Setting + " value: " + Settings.getProperty(Setting), false);
            MessageProcessor.setMessageTypeVisibility(0, Boolean.parseBoolean(getSetting(Setting)));
        } else {
            MessageProcessor.processMessage(2, "Setting: " + Setting + " not found.", false);
        }

        Setting = "Console Length";
        if(checkSetting(Setting) == true) {
            MessageProcessor.processMessage(0, "Setting: " + Setting + " found.", true);
            MessageProcessor.processMessage(2, "Setting: " + Setting + " value: " + Settings.getProperty(Setting), true);
            MessageProcessor.setMaxMessages(Integer.parseInt(getSetting(Setting)));
        } else {
            MessageProcessor.processMessage(2, "Setting: " + Setting + " not found.", true);
        }







        
        MessageProcessor.processMessage(2, "Settings Applied", false);
    }

    public static boolean checkSettingsFile() {
        File file = new File(SettingsFilePath);
        if(file.exists()) {
            MessageProcessor.processMessage(2, "Settings File Found", false);
            return true;
        }else{
            MessageProcessor.processMessage(2, "Settings File Not Found", false);
            return false;
        }
    }

    public static boolean checkSetting(String setting){
        if(Settings.getProperty(setting) != null) {
            MessageProcessor.processMessage(2, "Setting Found: " + setting, false);
            return true;
        }else{
            MessageProcessor.processMessage(2, "Setting Not Found: " + setting, false);
            return false;
        }
    }

    public static void SettingsMenu(){
        
    }
}
