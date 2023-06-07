package com.solarrentalmac.Setup;

import com.solarrentalmac.Login.LoginController;
import com.solarrentalmac.Main.SettingsController;

public class SetupController {

    public static void Start() {
        Boolean firstTime = FirstTimeChecker.checkFirstTime();
        if(firstTime){
            SettingsController.LoadSettings();
            SetupMenu.mainSetupMenu();
        } else {
            VersionController.checkForUpdates();
            VersionController.getVersionNumber();
            SettingsController.LoadSettings();
            SettingsController.ApplySettings();
            LoginController.LoginScreen();
        }
    }
    
}
