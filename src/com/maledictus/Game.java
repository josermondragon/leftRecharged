package com.maledictus;

import com.maledictus.item.Item;
import com.maledictus.player.Player;
import com.maledictus.player.PlayerFactory;
import com.maledictus.room.Room;
import com.maledictus.room.RoomFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

import static com.maledictus.Input.scannerUserInput;

public class Game {

    private final Map<String, Room> roomMap = RoomFactory.getRoomMap();
    private Player playerOne;
    private ArrayList<Item> roomItems;
    private Map<String, String> roomDirections;
    private Room currentRoom;

    public void initiateGame() {
        displaySplash();
        createCharacter();
        JSONParser.createItems();
        JSONParser.createRoomList();
        currentRoom = roomMap.get("Great Hall");
        displayConsoleCommands();

        start();
    }

    public void createCharacter() {
        playerOne = PlayerFactory.createPlayer();
    }

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
//            Scanner scanner = new Scanner(System.in);
            System.out.println("Select [1] to start game.");
            System.out.println("Select [2] to quit game.\n>>>");
//            String startGame = scanner.nextLine();
            String startGame = scannerUserInput();

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

    private void start() {
        boolean round = true;
        while (round) {
            // TODO: Put scanner logic into separate class
//            Scanner scanner = new Scanner(System.in);
            System.out.println("\nEnter a command or enter [options] to see game options: \n>>>");
            String userCommand = scannerUserInput();

            if (userCommand.equalsIgnoreCase("options")){
                displayOptions();
            }

            // Splitting userCommand into two separate strings. (Verb, Noun)
            String[] userInput = userCommand.split(" ", 2);

            // Check to see if user input is expected array format
            if (userInput.length == 2) {
                getUserInput(userInput);
            }  else {
                System.out.println("INVALID COMMAND ERROR: user input of '" + userCommand + "' is invalid usage of the command syntax. (Example: 'go south')");
            }

            displayConsoleCommands();

            //Temporary false logic to stop loop
            if (playerOne.getHitPoints() == 0) {
                round = false;
            }
        }
    }

    private void takeItem(String[] userInput) {
        boolean itemFound = false;
        for (Item item : roomItems) {
            if(userInput[1] != null && item.getName().equalsIgnoreCase(userInput[1])) {
                itemFound = true;
                playerOne.addItem(item);
                roomItems.remove(item);
                System.out.println(userInput[1] + " was added to your inventory.");
                System.out.println("Remaining Items: " + roomItems); //TODO: Need to work on toString for better display of items.
                break;
            }
        }
        if (!itemFound) {
            System.out.println("INVALID ITEM ERROR: You wrote take '" + userInput[1] + "' that is not a valid item option, please try again. (Example: 'take iron sword')");
        }
    }

    private void moveRoom (String[] userInput) {
        boolean roomFound = false;
            roomDirections = currentRoom.getDirections();
            for (Map.Entry<String, String> direction : roomDirections.entrySet()) {
                if (userInput[1].equals(direction.getKey())) {
                    roomFound = true;
                    currentRoom = roomMap.get(direction.getValue());
                    break;
                }
            }
            if (!roomFound) {
                System.out.println("INVALID ITEM ERROR: You wrote go '" + userInput[1] + "' that is not a valid room option, please try again. (Example: 'go north')");
            }
        }

    private void getUserInput(String[] userInput) {
        // Making sure the user uses the valid syntax of "verb[word]" + SPACE + "noun[word(s)]" (example: take Iron Sword)
            if(userInput[0].equalsIgnoreCase("go")) {
                moveRoom(userInput);
            } else if(userInput[0].equalsIgnoreCase("take")) {
                takeItem(userInput);
            } else {
                System.out.println("INVALID ACTION ERROR: user input of '" + userInput[0] + "' is an invalid action input. (Example: 'go', 'take')");
            }
    }

    private void displayOptions() {
        boolean waitingOnInput = true;
        while (waitingOnInput) {

//            Scanner scanner = new Scanner(System.in);
            System.out.println("Press [1] to start a new game.\nPress [2] to quit.\nPress [3] for game info.\nPress [4] to resume game.");
//            String optionInput = scanner.nextLine();
            String optionInput = scannerUserInput();
            if (optionInput.equals("1")) {
                // Still needs work.
                RoomFactory.clearRoomMap();
                JSONParser.items.clear(); // temp
                JSONParser.items2.clear(); // temp
                // Reset player inventory too.
                initiateGame();
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

    private void displayIntroText() {
        System.out.println("\nYou made it, the castle looks old and abandoned, but is an immaculate piece of architecture. There is an uneasy feeling in the air, a rush of\n" +
                "wind picks up the leaves around you. Will you be the first to claim King Berengars treasure? Or will you join the cursed souls that linger\n" +
                "within...\n");
    }

    private void displayCurrentRoomActions() {
        if(currentRoom != null) {
            displayRoomDirections();
            displayRoomItems();
        }
    }

    private void displayRoomItems() {
        if (currentRoom.getItems() != null) {
            roomItems = currentRoom.getItems();
            for (Item item : roomItems) {
                System.out.println("[take " + item.getName() + "]");
            }
        }
    }

    private void displayRoomDirections() {
        if (currentRoom.getDirections() != null) {
            roomDirections = currentRoom.getDirections();
            for (Map.Entry<String, String> direction : roomDirections.entrySet()) {
                System.out.println("[go " + direction.getKey() + "]");
            }
        }
    }

    private void displayConsoleCommands() {
        System.out.println("============================================================DESCRIPTION===========================================================");
        System.out.println(currentRoom.toString());
        System.out.println("==================================================================================================================================");
        System.out.println("=============================================================COMMANDS=============================================================");
        displayCurrentRoomActions();
        System.out.println("==================================================================================================================================");
    }

}