package com.maledictus;

import java.awt.*;

public class Printer {

    public static GUI gui = GUI.getInstance();

    public static void print(Object string) {
        String obj = string.toString() ;
        gui.addGameText(obj+"\n");
        System.out.println(string); // allows us to still observe game in console
    }

    public static void print(Color color, Object string) {
        String obj = string.toString() ;
        gui.addGameText(obj+"\n", color);
        System.out.println(string);
    }

}