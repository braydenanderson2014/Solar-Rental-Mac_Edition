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
        
    }

}