package com.solarrentalmac.Main;

import com.solarrentalmac.UserControllers.ProfileLoader;

public class MainSystem {
    public static void MainMenu(){
        System.out.println("Welcome to Solar Rental Mac!" + ProfileLoader.getProfileName());
    }
}
