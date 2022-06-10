package com.maledictus;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class GameV2 {

    /* (RAM) START NEW CODE */
    Player playerOne;
    /* (RAM) END NEW CODE */

    public void displaySplash() {

        boolean play = true;

        while (play) {
            String titleBanner = null;
            try {
                titleBanner = Files.readString(Path.of("data/splash_banner.txt"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(titleBanner);
            System.out.println("Welcome to Maledictus.  A game created by Lefties.\n");

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
        System.out.println("\nthis will be the introduction text\n");
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
        map.add(new Room("Great Hall", "The main area of the castle.", 3, 0, 0, 1, items));

        map.add(new Room("Dining room", "A long, rectangular dining table set with the finest silver cutlery and porcelain tableware fills the room.  A fireplace is built into the east wall behind \n" +
                "the head of the table.  A large, crystal chandelier hangs perfectly centered in the room.", 2, 1, 0, 1));

        map.add(new Room("Kitchen", "Bundles of herbs hang from the ceiling.  A large stone oven is built into the north wall. The mixed aroma of burnt wood and fresh \n" +
                "herbs linger.", 2, 1, 2, 2));

        map.add(new Room("Courtyard", "The smell of fresh flowers fills the air and the sound of flowing water can be heard.  Four large oak trees sit in each corner surrounded by garden flowers.  \n" +
                "A hedge-lined path leads to the center of the courtyard where a large water fountain sits.", 4, 0, 3, 3));

        map.add(new Room("Ball room", "Crystal chandeliers spiral down from the arched sky-blue ceiling illuminating the luxurious golden walls and a floor\n" +
                "so polished it looks like an iced-over lake.", 4, 3, 4, 4));

        boolean round = true;
        Room currentRoom = map.get(0);
        System.out.println(currentRoom.toString());
        System.out.println("\nAvailable commands:\n[go north]\n[go west]\n[go south]\n");

        while (round) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("\nEnter a direction: \n>>>");
            int direction = scanner.nextInt();

            currentRoom = map.get(direction); //get the specific room you need by the arraylist (map) index
            //wrap this in a while loop to repeat the process
            System.out.println(currentRoom.toString()); //print the room name and the description in the toString.

            switch (direction) {
                case 0:
                    /* (RAM) START NEW CODE */
                    ArrayList<Item> roomItems = currentRoom.getItems();
                    // (RAM) Remains false until user chooses a valid option
                    boolean validInput = false;
                    while (!validInput) {
                        System.out.println("\nAvailable commands:\n[go north]\n[go west]\n[go south]");
                        // (RAM) List out the item(s) in the room.
                        for (Item item : roomItems) {
                            System.out.println("[take " + item.name + "]");
                        }
                        // (RAM) new scanner to take in user input
                        Scanner scanner1 = new Scanner(System.in);
                        System.out.println("\nChoose/Type a valid command:");
                        String userInput = scanner1.nextLine();
                        // (RAM) save user input into an array of two strings (example: [take, Healing Potion])
                        String[] userChoice = userInput.split(" ", 2);
                        if (userChoice[0].equals("take") ) {
                            // (RAM) If the user provides the command of "take", we loop through the items in the
                            // (RAM) current room and look for the item.name that matches the userChoice[1] (example: "Healing Potion")
                            for (Item item : roomItems) {
                                if(item.name.equals(userChoice[1])) {
                                    // (RAM) Remove the item if found, and add it to the player inventory
                                    playerOne.addItem(item);
                                    roomItems.remove(item);
                                    System.out.println(userChoice[1] + " was added to your inventory.");
                                    System.out.println("Remaining Items: " + roomItems);
                                    validInput = true;
                                    break;
                                }
                            }
                        } else if (userChoice[0].equals("inspect")) {
                            //TODO
                            //playerOne.inspectItem();
                        }
                        if(!validInput) {
                            System.out.println("You did not choose a valid option, you wrote: " + userInput);
                        }
                    }
                    /* (RAM) END NEW CODE */
                    break;
                    // TODO:
                    //combat encounter method called
                    //getting items logic called --> item added to list/map whatever the case may be
                    //approach npc method called --> json file for npc dialogue is read
                case 1:
                    System.out.println("Case 1: \nAvailable commands:\n[go north]\n[go west]\n[go east]\n");
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;


            }






            //SET THE ROUND TO FALSE IF PLAYER HP <= 0 TO BREAK THE LOOP

            //the numbers represent a direction AND the index value of the map array for the corresponding room
//
        }
    }

    public void displayCommands() {
    }
}