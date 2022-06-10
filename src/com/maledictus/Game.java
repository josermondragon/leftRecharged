package com.maledictus;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class Game {


    int direction;


    public void displaySplash() {

        boolean play = true;


        String titleBanner = null;
        try {
            titleBanner = Files.readString(Path.of("data/splash_banner.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(titleBanner);
        System.out.println("Welcome to Maledictus.  A game created by Lefties.\n");

        while (play) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Select [1] to start game.");
            System.out.println("Select [2] to quit game.\n>>>");
            String startGame = scanner.nextLine();

            if (startGame.equals("1")) {
                displayIntroText();
                play = false;
            } else if (startGame.equals("2")) {
                System.out.println("Exiting the game...");
                System.exit(1);
            } else {
                System.out.println("Invalid Selection.  Please enter [1] to start game or [2] to quit.\n>>>");
            }
        }
    }


    public void displayIntroText() {
        System.out.println("\nYou made it, the castle looks old and abandoned, but is an immaculate piece of architecture. There is an uneasy feeling in the air, a rush of\n" +
                "wind picks up the leaves around you. Will you be the first to claim King Berengars treasure? Or will you join the cursed souls that linger\n" +
                "within...\n");
    }

    public void getDirection() {

        ArrayList<Room> map = new ArrayList<>();

        map.add(new Room("Great Hall", "The main area of the castle.", 3, 0, 0, 1));
        //0

        map.add(new Room("Dining room", "A long, rectangular dining table set with the finest silver cutlery and porcelain tableware fills the room.  A fireplace is built into the east wall behind \n" +
                "the head of the table.  A large, crystal chandelier hangs perfectly centered in the room.\n", 2, 1, 0, 1));
        //1
        map.add(new Room("Kitchen", "Bundles of herbs hang from the ceiling.  A large stone oven is built into the north wall. The mixed aroma of burnt wood and fresh \n" +
                "herbs linger.", 2, 1, 2, 2));
        //2
        map.add(new Room("Courtyard", "The smell of fresh flowers fills the air and the sound of flowing water can be heard.  Four large oak trees sit in each corner surrounded by garden flowers.  \n" +
                "A hedge-lined path leads to the center of the courtyard where a large water fountain sits.\n", 4, 0, 3, 3));
        //3
        map.add(new Room("Ball room", "Crystal chandeliers spiral down from the arched sky-blue ceiling illuminating the luxurious golden walls and a floor\n" +
                "so polished it looks like an iced-over lake.\n", 4, 3, 4, 4));
        //4
        map.add(new Room("Great Hall hallway", "A long and narrow hallway, with a floor of solid white marble.  Numerous antique paintings hang on the wall.\n", 6, 5, 7, 0));
        //5
        map.add(new Room("Guard room", "A place where arms and military equipment are stored. You see a ghostly soldier sitting in a wooden chair.", 6, 5, 6, 6));
        //6
        map.add(new Room("Library", "You see book shelves throughout the room, and at the center of it all, you see a ghostly librarian sitting on the floor with a sea of books scattered around him. He seems to be reading something.\n", 9, 8, 7, 5));
        //7
        map.add(new Room("Secret room", "Description for the secret room\n", 7, 8, 8, 8));
        //8
        map.add(new Room("Foyer", "A large, vacant room dimly lit by a few torches lining the stone walls.  You feel an immediate drop in temperature as a freezing chill crawls up your back. You get the sense that this may be your last chance to turn back from what lies ahead...\n", 9, 9, 7, 10));
        //9
        map.add(new Room("Dungeon", "A dark, damp room filled with multiple iron bared cells for holding prisoners. The foundation resembles more of a cavernous system than a stone wall. The smell of death is pungent and overwhelming.  \n", 11, 10, 9, 12));
        //10
        map.add(new Room("Cellar", "You see rows of Wineracks as far as the eye can see. A large layer of dust sits atop the exposed wine bottles. You hear a bottle smash in the distance. You are not alone in here...\n", 11, 10, 11, 11));
        //11
        map.add(new Room("Crypt", "This is the description placeholder for crypt\n", 12, 12, 10, 12));
        //12


        Room currentRoom = map.get(0); //sets the current room to the great hall(0) for initial start of game

        System.out.println(currentRoom.toString());
        System.out.println("\nAvailable commands:\n[go north]\n[go west]\n[go south]\n");

        boolean round = true;
        while (round) {


//            Scanner scanner = new Scanner(System.in);
//            System.out.println("\nEnter a command or enter [options] to see game options: \n>>>");
//            String userCommand = scanner.nextLine();
//
//            if (userCommand.equalsIgnoreCase("options")){
//                displayOptions();
//            }
//            if (direction == 0){ //great hall
//
//            switch(userCommand.toLowerCase()) {
//                case "go north":
//                    direction = 3;
//                    break;
//                case "go east":
//                    direction = 1;
//                    break;
//                case "go west":
//                    direction = 5;
//                    break;
//                }
//            }
//
//            else if (direction == 1){ //dining room
//                switch(userCommand.toLowerCase()) {
//                    case "go north":
//                        direction = 2; //kitchen
//                        break;
//                    case "go west":
//                        direction = 0; //great hall
//                        break;
//                }
//            }
//
//            else if (direction ==2){ //Kitchen
//                switch(userCommand.toLowerCase()) {
//                    case "go south":
//                        direction = 1; //dining room
//                        break;
//                }
//            }
//
//            else if (direction == 3){ //Courtyard
//                switch(userCommand.toLowerCase()) {
//                    case "go north":
//                        direction = 4; //ball room
//                        break;
//                    case "go south":
//                        direction = 0; //great hall
//                        break;
//                }
//            }
//
//            else if (direction == 4){ //Ball room
//                switch(userCommand.toLowerCase()) {
//                    case "go south":
//                        direction = 3; //courtyard
//                        break;
//                }
//            }
//
//            else if (direction == 5){ //Great Hall hallway
//                switch(userCommand.toLowerCase()) {
//                    case "go north":
//                        direction = 6; //guard room
//                        break;
//                    case "go west":
//                        direction = 7; //library
//                        break;
//                    case "go east":
//                        direction = 0; //great hall
//                        break;
//                }
//            }
//
//            else if (direction == 6){ //Guard room
//                switch(userCommand.toLowerCase()) {
//                    case "go south":
//                        direction = 5; //great hall hallway
//                        break;
//                }
//            }
//
//            else if (direction == 7){ //Library
//                switch(userCommand.toLowerCase()) {
//                    case "go north":
//                        direction = 9; //foyer
//                        break;
//                    case "go east":
//                        direction = 5; //great hall hallway
//                        break;
//                    case "go south":
//                        direction = 8; //secret room
//                }
//            }
//
//            else if (direction == 8){ //Secret room
//                switch(userCommand.toLowerCase()) {
//                    case "go north":
//                        direction = 7; //library
//                        break;
//                }
//            }
//
//            else if (direction ==9){ //Foyer
//                switch(userCommand.toLowerCase()) {
//                    case "go east":
//                        direction = 10; //kitchen
//                        break;
//                    case "go west":
//                        direction = 7; //library
//                        break;
//                }
//            }
//
//            else if (direction == 10){ //Dungeon
//                switch(userCommand.toLowerCase()) {
//                    case "go north":
//                        direction = 11; //cellar
//                        break;
//                    case "go west":
//                        direction = 9; //foyer
//                        break;
//                    case "go east":
//                        direction = 12; //crypt
//                        break;
//                }
//            }
//
//            else if (direction == 11){ //Cellar
//                switch(userCommand.toLowerCase()) {
//                    case "go south":
//                        direction = 10; //dungeon
//                        break;
//                }
//            }
//
//            else if (direction == 12){ //Crypt
//                switch(userCommand.toLowerCase()) {
//                    case "go east":
//                        direction = 10; //dungeon
//                        break;
//                }
//            }
//
//            currentRoom = map.get(direction);
//            System.out.println(currentRoom.toString());


            switch (direction) {
                case 0: //great hall
                    System.out.println("\nAvailable commands:\n[go north]\n[go west]\n[go east]\n");

                    //combat encounter method called
                    //getting items logic called --> item added to list/map whatever the case may be
                    //approach npc method called --> json file for npc dialogue is read
                    break;
                case 1: //dining room
                    System.out.println("\nAvailable commands:\n[go north]\n[go west]\n");
                    break;
                case 2: //kitchen
                    System.out.println("\nAvailable commands:\n[go south]\n");
                    break;
                case 3: //courtyard
                    System.out.println("\nAvailable commands:\n[go south]\n[go north]\n");
                    break;
                case 4://Ballroom
                    System.out.println("\nAvailable commands:\n[go south]\n");
                    break;
                case 5://Great Hall Hallway
                    System.out.println("\nAvailable commands:\n[go north]\n[go west]\n[go east]\n");
                    break;
                case 6://Guard room
                    System.out.println("\nAvailable commands:\n[go south]\n");
                    break;
                case 7://Library
                    System.out.println("\nAvailable commands:\n[go south]\n[go north]\n[go east]\n");
                    break;
                case 8://Secret room
                    System.out.println("\nAvailable commands:\n[go north]\n");
                    break;
                case 9://Foyer
                    System.out.println("\nAvailable commands:\n[go east]\n[go south]\n");
                    break;
                case 10://Dungeon
                    System.out.println("\nAvailable commands:\n[go north]\n[go east]\n[go west]\n");
                    break;
                case 11://Cellar
                    System.out.println("\nAvailable commands:\n[go south]\n");
                    break;
                case 12://Crypt
                    System.out.println("\nAvailable commands:\n[go west]\n");
                    break;
            }
            //SET THE ROUND TO FALSE IF PLAYER HP <= 0 TO BREAK THE LOOP
        }
    }

    public void displayOptions() {
        boolean waitingOnInput = true;
        while (waitingOnInput) {

            Scanner scanner = new Scanner(System.in);
            System.out.println("Press [1] to start a new game.\nPress [2] to quit.\nPress [3] for game info.\nPress [4] to resume game.");
            String optionInput = scanner.nextLine();
            if (optionInput.equals("1")) {
                displaySplash(); //starts a new game
                waitingOnInput = false;
            } else if (optionInput.equals("2")) {
                System.out.println("Exiting game.  Thank you for playing.");
                System.exit(1);
            }
            else if (optionInput.equals("3")) {
                System.out.println("Maledictus is a console text-adventure game.  You are a treasure hunter is seek of riches.  Your goal is to traverse the map, discover what lies within, and make it out alive!\nGame created by team Lefties: Ryan Mosser, Michael Herman, and Nikko Colby\n");
            }
            else if (optionInput.equals("4")){
                break; //resumes current game
            }
            else {
                System.out.println("Invalid Selection.  Please try again.");
            }
        }
    }

}