package com.solarrentalmac.Login;

import java.util.ArrayList;

public class SwitchUserController {
    public static String FocusUser;
    public static ArrayList<String> LoggedinUsers = new ArrayList<String>();
    public static void switchUser(String username) {
        if(!LoggedinUsers.contains(username)){
            LoggedinUsers.add(username);
        }
        FocusUser = username;
    }
    public static void SwitchUserMenu(){
        System.out.println("Switch User Menu");
        for (int i = 0; i < LoggedinUsers.size(); i++) {
            System.out.println(i + ". " + LoggedinUsers.get(i));
        }
        System.out.println("Please select a user to switch to: ");
        int selection = Integer.parseInt(com.solarrentalmac.Assets.customScanner.nextLine());
        if(selection < LoggedinUsers.size()){
            //Verify Password
            FocusUser = LoggedinUsers.get(selection);
            System.out.println("Switched to user: " + FocusUser);
        } else {
            System.out.println("Invalid Selection");
            SwitchUserMenu();
        }
    }
}
