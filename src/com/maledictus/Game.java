package com.maledictus;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class Game {

    /* (RAM) START NEW CODE */
    Player playerOne;
    /* (RAM) END NEW CODE */
    ArrayList<Item> roomItems;
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

    /* (RAM) START NEW CODE */
    public void createCharacter() {
        playerOne = PlayerFactory.createPlayer();
    }
    /* (RAM) END NEW CODE */

    public void getDirection() {
        /* (RAM) START NEW CODE */
        // (RAM) Temporarily hard coding items into existence
        ArrayList<Item> items = new ArrayList<>();
        Item sword = new Item("Iron Sword", "A sharp sword made of the finest iron", Item.ItemType.WEAPON, Item.WeaponType.SLASH);
        Item potion = new Item("Healing Potion", "A vial filled with red liquid", Item.ItemType.POTION);
        items.add(sword);
        items.add(potion);
        /* (RAM) END NEW CODE */

        ArrayList<Room> map = new ArrayList<>();

        map.add(new Room("Great Hall", "The main area of the castle.", 3, 0, 0, 1));
        //0

        map.add(new Room("Dining room", "A long, rectangular dining table set with the finest silver cutlery and porcelain tableware fills the room.  A fireplace is built into the east wall behind \n" +
                "the head of the table.  A large, crystal chandelier hangs perfectly centered in the room.\n", 2, 1, 0, 1, items));
        //1
        map.add(new Room("Kitchen", "Bundles of herbs hang from the ceiling.  A large stone oven is built into the north wall. The mixed aroma of burnt wood and fresh \n" +
                "herbs linger.", 2, 1, 2, 2, null));
        //2
        map.add(new Room("Courtyard", "The smell of fresh flowers fills the air and the sound of flowing water can be heard.  Four large oak trees sit in each corner surrounded by garden flowers.  \n" +
                "A hedge-lined path leads to the center of the courtyard where a large water fountain sits.\n", 4, 0, 3, 3, null));
        //3
        map.add(new Room("Ball room", "Crystal chandeliers spiral down from the arched sky-blue ceiling illuminating the luxurious golden walls and a floor\n" +
                "so polished it looks like an iced-over lake.\n", 4, 3, 4, 4, null));
        //4
        map.add(new Room("Great Hall hallway", "A long and narrow hallway, with a floor of solid white marble.  Numerous antique paintings hang on the wall.\n", 6, 5, 7, 0));
        //5
        map.add(new Room("Guard room", "A place where arms and military equipment are stored. You see a ghostly soldier sitting in a wooden chair.", 6, 5, 6, 6, null));
        //6
        map.add(new Room("Library", "You see book shelves throughout the room, and at the center of it all, you see a ghostly librarian sitting on the floor with a sea of books scattered around him. He seems to be reading something.\n", 9, 8, 7, 5, null));
        //7
        map.add(new Room("Secret room", "Description for the secret room\n", 7, 8, 8, 8, null));
        //8
        map.add(new Room("Foyer", "A large, vacant room dimly lit by a few torches lining the stone walls.  You feel an immediate drop in temperature as a freezing chill crawls up your back. You get the sense that this may be your last chance to turn back from what lies ahead...\n", 9, 9, 7, 10, null));
        //9
        map.add(new Room("Dungeon", "A dark, damp room filled with multiple iron bared cells for holding prisoners. The foundation resembles more of a cavernous system than a stone wall. The smell of death is pungent and overwhelming.  \n", 11, 10, 9, 12, null));
        //10
        map.add(new Room("Cellar", "You see rows of Wineracks as far as the eye can see. A large layer of dust sits atop the exposed wine bottles. You hear a bottle smash in the distance. You are not alone in here...\n", 11, 10, 11, 11, null));
        //11
        map.add(new Room("Crypt", "This is the description placeholder for crypt\n", 12, 12, 10, 12, null));
        //12


        Room currentRoom = map.get(0); //sets the current room to the great hall(0) for initial start of game

        System.out.println(currentRoom.toString());
        System.out.println("\nAvailable commands:\n[go north]\n[go west]\n[go east]\n");

        boolean round = true;
        while (round) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("\nEnter a command or enter [options] to see game options: \n>>>");
            String userCommand = scanner.nextLine();
            String[] userInput = userCommand.split(" ", 2);

            if (userCommand.equalsIgnoreCase("options")){
                displayOptions();
            }
            if (direction == 0){ //great hall
            /* (RAM) START NEW CODE */
            switch(userInput[0].toLowerCase()) {
                case "go":
                    if(userInput[1].equals("north")) {
                        direction = 3;
                        break;
                    } else if (userInput[1].equals("east")) {
                        direction = 1;
                        break;
                    } else if (userInput[1].equals("west")) {
                        direction = 5;
                        break;
                    }
                case "take":
                    System.out.println("Working on it");
                    for (Item item : roomItems) {
                        if(item.name.equals(userInput[1])) {
                            // (RAM) Remove the item if found, and add it to the player inventory
                            playerOne.addItem(item);
                            roomItems.remove(item);
                            System.out.println(userInput[1] + " was added to your inventory.");
                            System.out.println("Remaining Items: " + roomItems);
                            break;
                        }
                    }
                }
            }
            else if (direction == 1){ //dining room
                switch(userInput[0].toLowerCase()) {
                    case "go":
                        if(userInput[1].equals("north")) {
                            direction = 2;
                            break;
                        } else if (userInput[1].equals("west")) {
                            direction = 0;
                            break;
                        }
                    case "take":
                        for (Item item : roomItems) {
                            if(item.name.equals(userInput[1])) {
                                // (RAM) Remove the item if found, and add it to the player inventory
                                playerOne.addItem(item);
                                roomItems.remove(item);
                                System.out.println(userInput[1] + " was added to your inventory.");
                                System.out.println("Remaining Items: " + roomItems);
                                break;
                            }
                        }
                }
            }
            else if (direction ==2){ //Kitchen
                switch(userInput[0].toLowerCase()) {
                    case "go":
                        if(userInput[1].equals("south")) {
                            direction = 1;
                            break;
                        }
                    case "take":
                        for (Item item : roomItems) {
                            if(item.name.equals(userInput[1])) {
                                // (RAM) Remove the item if found, and add it to the player inventory
                                playerOne.addItem(item);
                                roomItems.remove(item);
                                System.out.println(userInput[1] + " was added to your inventory.");
                                System.out.println("Remaining Items: " + roomItems);
                                break;
                            }
                        }
                }
            }
            else if (direction == 3){ //Courtyard
                switch(userInput[0].toLowerCase()) {
                    case "go":
                        if(userInput[1].equals("north")) {
                            direction = 4;
                            break;
                        } else if (userInput[1].equals("south")) {
                            direction = 0;
                            break;
                        }
                    case "take":
                        for (Item item : roomItems) {
                            if(item.name.equals(userInput[1])) {
                                // (RAM) Remove the item if found, and add it to the player inventory
                                playerOne.addItem(item);
                                roomItems.remove(item);
                                System.out.println(userInput[1] + " was added to your inventory.");
                                System.out.println("Remaining Items: " + roomItems);
                                break;
                            }
                        }
                }
            }

            else if (direction == 4){ //Ball room
                switch(userInput[0].toLowerCase()) {
                    case "go":
                        if(userInput[1].equals("south")) {
                            direction = 3;
                            break;
                        }
                    case "take":
                        for (Item item : roomItems) {
                            if(item.name.equals(userInput[1])) {
                                // (RAM) Remove the item if found, and add it to the player inventory
                                playerOne.addItem(item);
                                roomItems.remove(item);
                                System.out.println(userInput[1] + " was added to your inventory.");
                                System.out.println("Remaining Items: " + roomItems);
                                break;
                            }
                        }
                }
            }

            else if (direction == 5){ //Great Hall hallway
                switch(userInput[0].toLowerCase()) {
                    case "go":
                        if(userInput[1].equals("north")) {
                            direction = 6;
                            break;
                        } else if (userInput[1].equals("east")) {
                            direction = 0;
                            break;
                        } else if (userInput[1].equals("west")) {
                            direction = 7;
                            break;
                        }
                    case "take":
                        System.out.println("Working on it");
                        for (Item item : roomItems) {
                            if(item.name.equals(userInput[1])) {
                                // (RAM) Remove the item if found, and add it to the player inventory
                                playerOne.addItem(item);
                                roomItems.remove(item);
                                System.out.println(userInput[1] + " was added to your inventory.");
                                System.out.println("Remaining Items: " + roomItems);
                                break;
                            }
                        }
                }
            }

            else if (direction == 6){ //Guard room
                switch(userInput[0].toLowerCase()) {
                    case "go":
                        if(userInput[1].equals("south")) {
                            direction = 5;
                            break;
                        }
                    case "take":
                        System.out.println("Working on it");
                        for (Item item : roomItems) {
                            if(item.name.equals(userInput[1])) {
                                // (RAM) Remove the item if found, and add it to the player inventory
                                playerOne.addItem(item);
                                roomItems.remove(item);
                                System.out.println(userInput[1] + " was added to your inventory.");
                                System.out.println("Remaining Items: " + roomItems);
                                break;
                            }
                        }
                }
            }
            else if (direction == 7){ //Library
                switch(userInput[0].toLowerCase()) {
                    case "go":
                        if(userInput[1].equals("north")) {
                            direction = 9;
                            break;
                        } else if (userInput[1].equals("east")) {
                            direction = 5;
                            break;
                        } else if (userInput[1].equals("south")) {
                            direction = 8;
                            break;
                        }
                    case "take":
                        System.out.println("Working on it");
                        for (Item item : roomItems) {
                            if(item.name.equals(userInput[1])) {
                                // (RAM) Remove the item if found, and add it to the player inventory
                                playerOne.addItem(item);
                                roomItems.remove(item);
                                System.out.println(userInput[1] + " was added to your inventory.");
                                System.out.println("Remaining Items: " + roomItems);
                                break;
                            }
                        }
                }
            }
            else if (direction == 8){ //Secret room
                switch(userInput[0].toLowerCase()) {
                    case "go":
                        if(userInput[1].equals("north")) {
                            direction = 7;
                            break;
                        }
                    case "take":
                        System.out.println("Working on it");
                        for (Item item : roomItems) {
                            if(item.name.equals(userInput[1])) {
                                // (RAM) Remove the item if found, and add it to the player inventory
                                playerOne.addItem(item);
                                roomItems.remove(item);
                                System.out.println(userInput[1] + " was added to your inventory.");
                                System.out.println("Remaining Items: " + roomItems);
                                break;
                            }
                        }
                }
            }
            else if (direction ==9){ //Foyer
                switch(userInput[0].toLowerCase()) {
                    case "go":
                        if(userInput[1].equals("east")) {
                            direction = 10;
                            break;
                        } else if (userInput[1].equals("west")) {
                            direction = 7;
                            break;
                        }
                    case "take":
                        System.out.println("Working on it");
                        for (Item item : roomItems) {
                            if(item.name.equals(userInput[1])) {
                                // (RAM) Remove the item if found, and add it to the player inventory
                                playerOne.addItem(item);
                                roomItems.remove(item);
                                System.out.println(userInput[1] + " was added to your inventory.");
                                System.out.println("Remaining Items: " + roomItems);
                                break;
                            }
                        }
                }
            }
            else if (direction == 10){ //Dungeon
                switch(userInput[0].toLowerCase()) {
                    case "go":
                        if(userInput[1].equals("north")) {
                            direction = 11;
                            break;
                        } else if (userInput[1].equals("east")) {
                            direction = 12;
                            break;
                        } else if (userInput[1].equals("west")) {
                            direction = 9;
                            break;
                        }
                    case "take":
                        System.out.println("Working on it");
                        for (Item item : roomItems) {
                            if(item.name.equals(userInput[1])) {
                                // (RAM) Remove the item if found, and add it to the player inventory
                                playerOne.addItem(item);
                                roomItems.remove(item);
                                System.out.println(userInput[1] + " was added to your inventory.");
                                System.out.println("Remaining Items: " + roomItems);
                                break;
                            }
                        }
                }
            }
            else if (direction == 11){ //Cellar
                switch(userInput[0].toLowerCase()) {
                    case "go":
                        if(userInput[1].equals("south")) {
                            direction = 10;
                            break;
                        }
                    case "take":
                        System.out.println("Working on it");
                        for (Item item : roomItems) {
                            if(item.name.equals(userInput[1])) {
                                // (RAM) Remove the item if found, and add it to the player inventory
                                playerOne.addItem(item);
                                roomItems.remove(item);
                                System.out.println(userInput[1] + " was added to your inventory.");
                                System.out.println("Remaining Items: " + roomItems);
                                break;
                            }
                        }
                }
            }
            else if (direction == 12){ //Crypt
                switch(userInput[0].toLowerCase()) {
                    case "go":
                        if(userInput[1].equals("east")) {
                            direction = 10;
                            break;
                        }
                    case "take":
                        System.out.println("Working on it");
                        for (Item item : roomItems) {
                            if(item.name.equals(userInput[1])) {
                                // (RAM) Remove the item if found, and add it to the player inventory
                                playerOne.addItem(item);
                                roomItems.remove(item);
                                System.out.println(userInput[1] + " was added to your inventory.");
                                System.out.println("Remaining Items: " + roomItems);
                                break;
                            }
                        }
                }
            }

            currentRoom = map.get(direction);
            System.out.println(currentRoom.toString());

            switch (direction) {
                case 0: //great hall
                    System.out.println("\nAvailable commands:\n[go north]\n[go west]\n[go east]\n");
                    if (currentRoom.getItems() != null) {
                        roomItems = currentRoom.getItems();
                        for (Item item : roomItems) {
                            System.out.println("[take " + item.name + "]");
                        }
                    }
                    //combat encounter method called
                    //getting items logic called --> item added to list/map whatever the case may be
                    //approach npc method called --> json file for npc dialogue is read
                    break;
                case 1: //dining room
                    System.out.println("\nAvailable commands:\n[go north]\n[go west]\n");
                    if (currentRoom.getItems() != null) {
                        roomItems = currentRoom.getItems();
                        for (Item item : roomItems) {
                            System.out.println("[take " + item.name + "]");
                        }
                    }
                    break;
                case 2: //kitchen
                    System.out.println("\nAvailable commands:\n[go south]\n");
                    if (currentRoom.getItems() != null) {
                        roomItems = currentRoom.getItems();
                        for (Item item : roomItems) {
                            System.out.println("[take " + item.name + "]");
                        }
                    }
                    break;
                case 3: //courtyard
                    System.out.println("\nAvailable commands:\n[go south]\n[go north]\n");
                    if (currentRoom.getItems() != null) {
                        roomItems = currentRoom.getItems();
                        for (Item item : roomItems) {
                            System.out.println("[take " + item.name + "]");
                        }
                    }
                    break;
                case 4://Ballroom
                    System.out.println("\nAvailable commands:\n[go south]\n");
                    if (currentRoom.getItems() != null) {
                        roomItems = currentRoom.getItems();
                        for (Item item : roomItems) {
                            System.out.println("[take " + item.name + "]");
                        }
                    }
                    break;
                case 5://Great Hall Hallway
                    System.out.println("\nAvailable commands:\n[go north]\n[go west]\n[go east]\n");
                    if (currentRoom.getItems() != null) {
                        roomItems = currentRoom.getItems();
                        for (Item item : roomItems) {
                            System.out.println("[take " + item.name + "]");
                        }
                    }
                    break;
                case 6://Guard room
                    System.out.println("\nAvailable commands:\n[go south]\n");
                    if (currentRoom.getItems() != null) {
                        roomItems = currentRoom.getItems();
                        for (Item item : roomItems) {
                            System.out.println("[take " + item.name + "]");
                        }
                    }
                    break;
                case 7://Library
                    System.out.println("\nAvailable commands:\n[go south]\n[go north]\n[go east]\n");
                    if (currentRoom.getItems() != null) {
                        roomItems = currentRoom.getItems();
                        for (Item item : roomItems) {
                            System.out.println("[take " + item.name + "]");
                        }
                    }
                    break;
                case 8://Secret room
                    System.out.println("\nAvailable commands:\n[go north]\n");
                    if (currentRoom.getItems() != null) {
                        roomItems = currentRoom.getItems();
                        for (Item item : roomItems) {
                            System.out.println("[take " + item.name + "]");
                        }
                    }
                    break;
                case 9://Foyer
                    System.out.println("\nAvailable commands:\n[go east]\n[go south]\n");
                    if (currentRoom.getItems() != null) {
                        roomItems = currentRoom.getItems();
                        for (Item item : roomItems) {
                            System.out.println("[take " + item.name + "]");
                        }
                    }
                    break;
                case 10://Dungeon
                    System.out.println("\nAvailable commands:\n[go north]\n[go east]\n[go west]\n");
                    if (currentRoom.getItems() != null) {
                        roomItems = currentRoom.getItems();
                        for (Item item : roomItems) {
                            System.out.println("[take " + item.name + "]");
                        }
                    }
                    break;
                case 11://Cellar
                    System.out.println("\nAvailable commands:\n[go south]\n");
                    if (currentRoom.getItems() != null) {
                        roomItems = currentRoom.getItems();
                        for (Item item : roomItems) {
                            System.out.println("[take " + item.name + "]");
                        }
                    }
                    break;
                case 12://Crypt
                    System.out.println("\nAvailable commands:\n[go west]\n");
                    if (currentRoom.getItems() != null) {
                        roomItems = currentRoom.getItems();
                        for (Item item : roomItems) {
                            System.out.println("[take " + item.name + "]");
                        }
                    }
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