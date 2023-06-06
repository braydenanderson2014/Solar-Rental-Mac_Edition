package com.solarrentalmac.Assets;

import java.util.Scanner;

public class customScanner {
    private static Scanner scan = new Scanner(System.in);
    
    public static String nextLine(){
        return scan.nextLine();
    }
    public static int nextInt(){
        return scan.nextInt();
    }
    public static double nextDouble(){
        return scan.nextDouble();
    }
    public static float nextFloat(){
        return scan.nextFloat();
    }
    public static boolean nextBoolean(){
        return scan.nextBoolean();
    }
    public static byte nextByte(){
        return scan.nextByte();
    }
    public static short nextShort(){
        return scan.nextShort();
    }
    public static long nextLong(){
        return scan.nextLong();
    }
    public static void close(){
        scan.close();
    }
}
