package com.maledictus;

import java.util.ArrayList;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Game game = new Game();
        game.displaySplash();
        /* (RAM) Start New Code */
        game.createCharacter();
        /* (RAM) End1 New Code */
        game.getDirection();
    }
}
