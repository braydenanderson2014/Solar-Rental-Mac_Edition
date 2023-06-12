package com.solarrentalmac.Assets;
import java.io.IOException;

import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.NoHeadException;

import com.solarrentalmac.Logging.MessageProcessor;
import com.solarrentalmac.Main.SettingsController;
import com.solarrentalmac.SelfUpdater.Updater;
public class VersionController1 {
    public static String Version;
    public static String getVersionNumber() {
        if(SettingsController.checkSetting("Current Version") == true) {
            Version = SettingsController.getSetting("Current Version");
            return SettingsController.getSetting("Current Version");
        }else {
            return "1.0.0";
        }
    }
    public static Boolean checkUpdates() throws NoHeadException, InterruptedException, GitAPIException {
        if(SettingsController.checkSetting("Update Check") == true) {
            if(SettingsController.getSetting("Update Check").equals("true")) {
                Updater updater = new Updater();
                try {
                    if(updater.isUpdateAvailable() == true) {
                        MessageProcessor.processMessage(2, "Update Available! Current Version: " + Version, false);
                        MessageProcessor.processMessage(1, "Update Available! Current Version: " + Version, true);
                        return true;                    
                    } else {
                        MessageProcessor.processMessage(2, "No Update Available! Current Version: " + Version, false);
                        return false;
                    }
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }               
            }else {
                MessageProcessor.processMessage(-1, "Updater is disabled, Please Re-enable to check for updates", true);
                MessageProcessor.processMessage(2, "Updater is disabled, Please Re-enable to check for updates", false);
                return false;
            }
        }else {
            MessageProcessor.processMessage(-1, "Failed to find Update Check Setting, Please try running setup again to fix issue.", true);
            MessageProcessor.processMessage(2, "Failed to find Update Setting, System suggests Retrying Setup", true);
            return false;
        }
        return null;
    }
}
