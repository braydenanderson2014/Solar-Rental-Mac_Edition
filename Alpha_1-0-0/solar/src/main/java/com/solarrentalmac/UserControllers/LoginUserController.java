package com.solarrentalmac.UserControllers;

public class LoginUserController {
    String UserFileDirectory = "src/main/java/com/solarrentalmac/UserFiles/";
    public static Boolean checkCredentials(String username, String password) {
        loadUser(username);
        return true;
    }
    //used to confirm login credentials and reset passwords

    private static Boolean loadUser(String username) {
        return true;
    }
}
