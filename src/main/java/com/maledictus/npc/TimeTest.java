package com.maledictus.npc;

import java.awt.event.*;
import javax.swing.*;

public class TimeTest {
    public static void main(String [] args) throws Exception{
        ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                //...Perform a task...

                System.out.println("Reading SMTP Info.");
            }
        };
        Timer timer = new Timer(1000 ,taskPerformer);
        timer.setRepeats(false);
        timer.start();
        Thread.sleep(5000);
    }
}