package com.maledictus;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Input {

    // For taking in scanner input (txt/or user)
    private Input() {

    }

    public static String scannerUserInput() {
//        Scanner scanner;
//        String line = "If you see this.. you FAILED!";
//        System.out.print(">>>");
//        scanner = new Scanner( System.in);
//        line = scanner.nextLine();
//        return line;
        System.out.println("DEBUG:::: ORIGINAL SCANNER CALL");
        return "hi";

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

//    public static String getMultiLineTextInput() {
//        Scanner scanner;
//        String paragraph = "If you see this.. you FAILED!";
//        try {
//            scanner = new Scanner(text);
//            paragraph = scanner.nextLine();
//            System.out.println(paragraph);
//            int lineNumber = 1;
//            while(scanner.hasNextLine()){
//                String line = scanner.nextLine();
//                System.out.println("line " + lineNumber + " :" + line);
//                lineNumber++;
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        return paragraph;
//    }
}