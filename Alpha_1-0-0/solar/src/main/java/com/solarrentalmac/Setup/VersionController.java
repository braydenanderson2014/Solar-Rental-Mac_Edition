package com.solarrentalmac.Setup;

import java.io.IOException;

import org.eclipse.jgit.api.errors.GitAPIException;

import com.solarrentalmac.SelfUpdater.Updater;

public class VersionController {
    public static Boolean checkForUpdates(){
    Updater updater = new Updater();
    try {
        if(updater.isUpdateAvailable()){
            return true;
        }
        return false;
    } catch (IOException | InterruptedException | GitAPIException e) {
        System.err.println("Error during update check: " + e.getMessage());
        e.printStackTrace();
        return false;
    }
}

    public static String getVersionNumber(){
        return "0.0.1";
    }
}
