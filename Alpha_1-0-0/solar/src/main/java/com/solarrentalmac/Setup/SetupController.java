package com.solarrentalmac.Setup;

import java.io.IOException;

import com.solarrentalmac.Assets.customScanner;
import com.solarrentalmac.Login.LoginController;
import com.solarrentalmac.Main.SettingsController;
import com.solarrentalmac.SelfUpdater.Updater;

public class SetupController {

    public static void Start() {
        Boolean needsUpdate = (Boolean) VersionController.checkForUpdates();
        if(needsUpdate){
            System.out.println("Update Available! Would you like to update now? (Y/N)");
            String answer = customScanner.nextLine();
            if(answer.equalsIgnoreCase("Y")){
                try {
                    Updater updater = new Updater();
                    updater.startUpdate();
                } catch (IOException | InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }else {
                System.out.println("Update Declined, Continuing Setup");
                finishStart();
            }
        }else{
            finishStart();
        }
    }

    public static void finishStart() {
        Boolean firstTime = FirstTimeChecker.checkFirstTime();

        if (firstTime) {
            VersionController.getVersionNumber();
            SettingsController.LoadSettings();
            SetupMenu.mainSetupMenu();
        } else {
            VersionController.getVersionNumber();
            SettingsController.LoadSettings();
            SettingsController.ApplySettings();
            LoginController.LoginScreen();
        }
    }
}
