package com.maledictus;

import java.awt.*;

public class Printer {

    public static GUI gui = GUI.getInstance();

    public static Component print(Object string) {
        String obj = string.toString() ;
        gui.buttonAddText(obj+"\n");
       // gui.MainFrame();
        System.out.println(string);
        return null;
    }

}