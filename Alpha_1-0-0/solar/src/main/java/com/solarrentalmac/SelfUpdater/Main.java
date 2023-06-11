package com.solarrentalmac.SelfUpdater;

import java.io.IOException;

import com.solarrentalmac.Main.ProgramStart;

public class Main {

    public static void main(String[] args) throws IOException {
        // First, check for updates
        Updater updater = new Updater();
        if (updater.isUpdateAvailable()) {
            // If an update is available, start the updater process and exit
            try {
                updater.startUpdate();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.exit(0);
        }

        // If no update is available, start the normal application logic
        startApp();
    }

    private static void startApp() {
        // Your application logic here
        ProgramStart.main(new String[0]);
    }
}
