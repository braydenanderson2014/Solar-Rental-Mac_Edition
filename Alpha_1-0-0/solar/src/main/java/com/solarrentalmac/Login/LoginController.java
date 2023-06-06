package com.solarrentalmac.Login;

import com.solarrentalmac.Assets.TxtColorTable;
import com.solarrentalmac.Assets.customScanner;
import com.solarrentalmac.Logging.ConsoleHandler;
import com.solarrentalmac.Logging.MessageProcessor;
import com.solarrentalmac.Main.MainSystem;
import com.solarrentalmac.UserControllers.LoginUserController;
import com.solarrentalmac.UserControllers.ProfileLoader;

public class LoginController {
    public static void LoginScreen() {
        System.out.println(TxtColorTable.RESET + "Login Screen");
        ConsoleHandler.getConsole();
        System.out.println(TxtColorTable.getRESET() + "Username: ");
        String username = customScanner.nextLine();
        System.out.println("Password: ");
        String password = customScanner.nextLine();
        Boolean success = LoginUserController.checkCredentials(username, password);
        if(success){
            System.out.println("Login Successful");
            ProfileLoader.loadProfile(username);
            SwitchUserController.switchUser(username);
            MainSystem.MainMenu();
        }else {
            MessageProcessor.processMessage(-1, "Invalid Credentials", true);
            LoginScreen();
        }
    }
    public static void LoginScreen(String username){
        System.out.println(TxtColorTable.RESET + "Login Screen");
        ConsoleHandler.getConsole();
        System.out.println(TxtColorTable.getRESET() + "Username: " + username);
        System.out.println("Password: ");
        String password = customScanner.nextLine();
        Boolean success = LoginUserController.checkCredentials(username, password);
        if(success){
            System.out.println("Login Successful");
            ProfileLoader.loadProfile(username);
            SwitchUserController.switchUser(username);
            MainSystem.MainMenu();
        } else {
            MessageProcessor.processMessage(-1, "Invalid Credentials", true);
            LoginScreen();
        }
    }
}
