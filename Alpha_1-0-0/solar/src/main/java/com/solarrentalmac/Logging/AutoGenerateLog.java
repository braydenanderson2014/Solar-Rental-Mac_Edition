package com.solarrentalmac.Logging;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.nio.file.*;

import java.time.LocalDateTime;

import com.solarrentalmac.Main.SettingsController;

public class AutoGenerateLog {
    public static ArrayList<String> log = new ArrayList<String>();
    public static String LogDirectory = SettingsController.getSetting("Log Directory");
    public static Random ran = new Random();
    public static String FileName;
    public static String FileLogTypes = "all";

    public static void autoDump(String FileLogType) {
        FileLogTypes = SettingsController.getSetting("Log Type");
        List<String> log = new ArrayList<>();
        String logTime = "Report was Generated at: " + LocalDateTime.now();

        for (int i = 0; i < MessageProcessor.messages.size(); i++) {
            String messageType = MessageProcessor.messageTypes.get(i);
            if (FileLogType.equals("all") || messageType.toLowerCase().equals(FileLogType)) {
                String message = MessageProcessor.messages.get(i);
                String color = ConsoleHandler.getColorByMessageType(messageType);
                String time = MessageProcessor.datetime.get(i);
                log.add(color + time + messageType + ": " + message);
            }
        }

        if (log.isEmpty()) {
            return; // no messages to log
        }

        log.add(logTime); // Add log generated time at the end of the log

        String filenamePrefix = FileLogType.equals("all") ? "log" : FileLogType + "-log";
        String FileName = LogDirectory + filenamePrefix + "-" + ran.nextInt(100000) + ".txt";

        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(FileName))) {
            for (String line : log) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}