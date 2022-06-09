package com.maledictus;

import java.util.Scanner;

public class PlayerFactory {

    // FIELDS \\

    // CONSTRUCTORS \\
    public PlayerFactory() {

    }

    // BUSINESS METHODS \\
    public static Player createPlayer() {
        Scanner scanner = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Enter your character's name:");

        String name = scanner.nextLine();  // Read user input
        System.out.println("Username is: " + name);  // Output user input

        Player player = new Player(name);

        return player;
    }
    //GETTERS & SETTERS \\

    @Override
    public String toString() {
        return null;
    }
}