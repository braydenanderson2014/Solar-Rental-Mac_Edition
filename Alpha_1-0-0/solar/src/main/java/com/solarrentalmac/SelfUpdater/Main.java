package com.solarrentalmac.SelfUpdater;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import com.solarrentalmac.Logging.MessageProcessor;
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
                MessageProcessor.processMessage(-1, "Updater Failed to Start, Please try again later.", true);
                MessageProcessor.processMessage(-2, "Updater Failed to Start, Please try again later." + e.toString(), false);
                e.printStackTrace();
                StringWriter sw = new StringWriter();
                PrintWriter pw = new PrintWriter(sw);
                e.printStackTrace(pw);
                String sStackTrace = sw.toString(); // stack trace as a string
                MessageProcessor.processMessage(2, sStackTrace, false);
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
