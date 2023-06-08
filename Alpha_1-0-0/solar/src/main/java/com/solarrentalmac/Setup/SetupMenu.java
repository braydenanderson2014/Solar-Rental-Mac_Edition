package com.solarrentalmac.Setup;

import com.solarrentalmac.Assets.TxtColorTable;
import com.solarrentalmac.Assets.customScanner;
import com.solarrentalmac.Logging.AutoGenerateLog;
import com.solarrentalmac.Logging.ConsoleHandler;
import com.solarrentalmac.Logging.MessageProcessor;

public class SetupMenu {

    public static void mainSetupMenu() {
        System.out.println(TxtColorTable.RESET + "Welcome to Solar Rental Company");
        System.out.println("Please select an option:");
        System.out.println("1. Manual Setup");
        System.out.println("2. Automatic Setup");
        System.out.println("3. Exit");
        ConsoleHandler.getConsole();
        System.out.println(TxtColorTable.RESET + "Enter your option: ");
        String option = customScanner.nextLine();
        switch(option){
            case "1":
                ManualSetup.ManualSetupMenu();
                break;
            case "2":
                AutoSetup.StartAutomaticSetup();
                break;
            case "3":
                AutoGenerateLog.autoDump("all");
                System.exit(0);
                break;
            default:
                MessageProcessor.processMessage(-1, "Invalid option, try again!", true);
                //AutoGenerateLog.autoDump("all");
                mainSetupMenu();
                break;
        }
    }
    
}
