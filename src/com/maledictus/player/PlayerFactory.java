package com.maledictus.player;

import com.maledictus.player.Player;

import java.util.Scanner;

public class PlayerFactory {

    public PlayerFactory() {

    }

    public static Player createPlayer() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your character's name:");

        String name = scanner.nextLine();
        System.out.println("Username is: " + name);

        return new Player(name);
    }

}