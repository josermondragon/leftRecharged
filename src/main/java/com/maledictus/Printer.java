package com.maledictus;

import java.awt.*;

public class Printer {

    public static GUI gui = GUI.getInstance();

    public static Component print(Object string) {
        String obj = string.toString() ;
        gui.buttonAddText(obj+"\n");
        System.out.println(string);
        return null;
    }

    public static Component print(Color color, Object string) {
        String obj = string.toString() ;
        gui.buttonAddText(obj+"\n", color);
        System.out.println(string);
        return null;
    }

}