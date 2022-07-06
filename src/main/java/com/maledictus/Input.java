package com.maledictus;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Input {

    // For taking in scanner input (txt/or user)
    private Input() {

    }


    public static String scannerTextInput(File text) {
        Scanner scanner;
        String line = "If you see this.. you FAILED!";
        try {
            scanner = new Scanner(text);
            line = scanner.nextLine();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return line;
    }

}