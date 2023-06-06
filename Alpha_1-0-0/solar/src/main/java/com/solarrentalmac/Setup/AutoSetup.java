package com.solarrentalmac.Setup;

import com.solarrentalmac.Login.LoginController;
import com.solarrentalmac.Main.SettingsController;
import com.solarrentalmac.UserControllers.SetupUserController;

public class AutoSetup {

    public static void StartAutomaticSetup() {
        if(SettingsController.checkSettingsFile() == false) {
            SettingsController.createSettingsFile();
        }
        SettingsController.LoadSettings();
        if(SettingsController.checkSetting("Current Version") == false) {
            SettingsController.setProperty("Current Version", VersionController.getVersionNumber());
        }

        if(SettingsController.checkSetting("Update Check") == false) {
            SettingsController.setProperty("Update Check", "true");
        }
        if(SettingsController.checkSetting("Console Enabled") == false) {
            SettingsController.setProperty("Console Enabled", "true");
        }
        if(SettingsController.checkSetting("Installed Directory") == false) {
            SettingsController.setProperty("Installed Directory", System.getProperty("user.dir"));
        }
        if(SettingsController.checkSetting("Database Directory") == false) {
            SettingsController.setProperty("Database Directory", System.getProperty("user.dir") + "/Database");
        }
        if(SettingsController.checkSetting("Database Name") == false) {
            SettingsController.setProperty("Database Name", "userlist.properties");
        }
        if(SettingsController.checkSetting("Log Directory") == false) {
            SettingsController.setProperty("Log Directory", System.getProperty("user.dir") + "/Logs");
        }
        if(SettingsController.checkSetting("Console Length") == false) {
            SettingsController.setProperty("Console Length", "5");
        }
        if(SettingsController.checkSetting("Display Error Messages") == false) {
            SettingsController.setProperty("Display Error Messages", "true");
        }
        if(SettingsController.checkSetting("Display Warning Messages") == false) {
            SettingsController.setProperty("Display Warning Messages", "true");
        }
        if(SettingsController.checkSetting("Display Info Messages") == false) {
            SettingsController.setProperty("Display Info Messages", "true");
        }
        if(SettingsController.checkSetting("Display Debug Messages") == false) {
            SettingsController.setProperty("Display Debug Messages", "false");
        }
        if(SettingsController.checkSetting("Display System Messages") == false) {
            SettingsController.setProperty("Display System Messages", "true");
        }
        if(SettingsController.checkSetting("Error Color") == false) {
            SettingsController.setProperty("Error Color", "Red");
        }
        if(SettingsController.checkSetting("Warning Color") == false) {
            SettingsController.setProperty("Warning Color", "Yellow");
        }
        if(SettingsController.checkSetting("Info Color") == false) {
            SettingsController.setProperty("Info Color", "Blue");
        }
        if(SettingsController.checkSetting("Debug Color") == false) {
            SettingsController.setProperty("Debug Color", "Purple");
        }
        if(SettingsController.checkSetting("System Color") == false) {
            SettingsController.setProperty("System Color", "Green");
        }
        


        SettingsController.saveSettings();
        SetupUserController.CreateAdminUser();
        SettingsController.ApplySettings();

        LoginController.LoginScreen();
    }
    
}
