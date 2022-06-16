package com.maledictus;

import com.maledictus.item.Item;
import com.maledictus.player.Player;
import com.maledictus.player.PlayerFactory;
import com.maledictus.room.Room;
import com.maledictus.room.RoomFactory;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

import static com.maledictus.Input.scannerUserInput;
import static com.maledictus.Json.returnGameText;
import static com.maledictus.Json.returnNpcDialogue;

public class Game {

    private final Map<String, Room> roomMap = RoomFactory.getRoomMap();
    private Player playerOne;
    private ArrayList<Item> roomItems;
    private Map<String, String> roomDirections;
    private Room currentRoom;
    private String errorMsg = null;
    private String successMsg = null;

    public void initiateGame() throws IOException, org.json.simple.parser.ParseException, java.text.ParseException {
        Json.jsonWrite();
        displaySplash();
        createCharacter();
        Json.createItems();
        Json.createRoomList();
        currentRoom = roomMap.get("Great Hall");
        displayConsoleCommands();
        start();
    }

    public void createCharacter() {
        playerOne = PlayerFactory.createPlayer();
    }

    public void displaySplash() throws IOException, org.json.simple.parser.ParseException {

        boolean play = true;

        String titleBanner = null;
        try {
            titleBanner = Files.readString(Path.of("data/splash_banner.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(titleBanner);
        System.out.println(returnGameText("2") + "\n");

        while (play) {

            System.out.println(returnGameText("3"));
            System.out.println(returnGameText("4"));

            String startGame = scannerUserInput();

            if (startGame.equals("1")) {
                displayIntroText();
                play = false;
            } else if (startGame.equals("2")) {
                System.out.println("Exiting the game...");
                System.exit(1);
            } else {
                errorMsg = "Invalid Selection.  Please enter [1] to start game or [2] to quit.";
            }
        }
    }

    private void start() throws IOException, org.json.simple.parser.ParseException, java.text.ParseException {
        boolean round = true;
        while (round) {

            // Methods will check if an error or success message needs to be printed
            printSuccessMsg();
            printErrorMsg();
            System.out.println("\nEnter a command or enter [options] to see game options: ");

            // Take in user input and run through scanner
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
                errorMsg = "INVALID COMMAND ERROR: user input of '" + userCommand + "' is invalid usage of the command syntax. (Example: 'go south')";
            }

            if (errorMsg == null && successMsg == null) {
                displayConsoleCommands();
            }

            // TODO: Finish end game scenario
            if (playerOne.getHitPoints() == 0) {
                round = false;
            }

        }
    }
    // TODO: inspect item applies to all items in room and inventory, currently no way for user to know about inv.
    private void inspectItem(String[] userInput) {
        boolean itemFound = false;

        while (!itemFound) {
            // Search for item through player inventory
            for (Map.Entry<String, Item> invItem : playerOne.getInventory().entrySet()) {
                if(userInput[1].equalsIgnoreCase(invItem.getKey())) {
                    itemFound = true;
                    System.out.println(invItem.getValue().getDescription());
                    break;
                }
            }
            if (itemFound) break;

            // Search for item in current room
            for (Item item : roomItems) {
                if(userInput[1] != null && item.getName().equalsIgnoreCase(userInput[1])) {
                    itemFound = true;
                    successMsg = item.getDescription();
                    break;
                }
            }
            if (itemFound) {
                break;
            } else if (!itemFound) {
                errorMsg = "INVALID ITEM ERROR: You wrote inspect '" + userInput[1] + "' that is not a valid item option, please try again. (Example: 'take iron sword')";
                break;
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
                successMsg = userInput[1] + " was added to your inventory.";
                break;
            }
        }
        if (!itemFound) {
            errorMsg = "INVALID ITEM ERROR: You wrote take '" + userInput[1] + "' that is not a valid item option, please try again. (Example: 'take iron sword')";
        }
    }

    private void useItem(String[] userInput) {
        boolean itemFound = false;
        for (Item item : playerOne.getInventory().values()) {
            if(userInput[1] != null && item.getName().equalsIgnoreCase(userInput[1])) {
                itemFound = true;
                playerOne.removeItem(item);
                break;
            }
        }
        if(!itemFound) {
            errorMsg = userInput[1] + " is not in your inventory!";
        }
    }

    private void moveRoom (String[] userInput) {
        boolean roomFound = false;
            roomDirections = currentRoom.getDirections();
            for (Map.Entry<String, String> direction : roomDirections.entrySet()) {
                if (userInput[1].equalsIgnoreCase(direction.getKey())) {
                    roomFound = true;
                    currentRoom = roomMap.get(direction.getValue());
                    break;
                }
            }
            if (!roomFound) {
                errorMsg = "INVALID LOCATION ERROR: You wrote go '" + userInput[1] + "' that is not a valid room option, please try again. (Example: 'go north')";
            }
        }

    private void getUserInput(String[] userInput) {
        // Making sure the user uses the valid syntax of "verb[word]" + SPACE + "noun[word(s)]" (example: take Iron Sword)
            if(userInput[0].equalsIgnoreCase("go")) {
                moveRoom(userInput);
            } else if(userInput[0].equalsIgnoreCase("take") || userInput[0].equalsIgnoreCase("grab")) {
                takeItem(userInput);
            } else if(userInput[0].equalsIgnoreCase("inspect")) {
                inspectItem(userInput);
            } else if(userInput[0].equalsIgnoreCase("use")) {
                useItem(userInput);
            } else if(userInput[0].equalsIgnoreCase("heal")) {
                useItem(userInput);
            }  else {
                errorMsg = "INVALID ACTION ERROR: user input of '" + userInput[0] + "' is an invalid action input. (Example: 'go', 'take')";
            }
    }

    private void displayOptions() throws IOException, org.json.simple.parser.ParseException, java.text.ParseException {
        boolean waitingOnInput = true;
        while (waitingOnInput) {

            System.out.println("Press [1] to start a new game.\nPress [2] to quit.\nPress [3] for game info.\nPress [4] to resume game.");
            String optionInput = scannerUserInput();

            if (optionInput.equals("1")) {
                // Still needs work.
                RoomFactory.clearRoomMap();
                Json.items.clear(); // temp
                Json.items2.clear(); // temp
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
                errorMsg = "Invalid Selection.  Please try again.";
            }
        }
    }

    private void displayIntroText() throws IOException, org.json.simple.parser.ParseException {
        System.out.println(returnGameText("1") + "\n");
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
                System.out.println("[take/inspect " + item.getName() + "]");
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

    private void displayNpcDialogue(String npcNumber, String dialogueNumber) throws IOException, ParseException {
            Map npc;
            String npcDialogue;
            npc = returnNpcDialogue(npcNumber);
            npcDialogue =  npc.get("dialogue" + dialogueNumber).toString();
            System.out.println(npcDialogue);
    }


    private void printErrorMsg() {
        if (errorMsg != null) {
            System.out.println(errorMsg);
            errorMsg = null;
        }
    }

    private void printSuccessMsg() {
        if (successMsg != null) {
            System.out.println(successMsg);
            successMsg = null;
        }
    }

    private void displayConsoleCommands() {
        System.out.println("-----------");
        System.out.println("DESCRIPTION:");
        System.out.println("-----------");
        System.out.println(currentRoom.toString());
        System.out.println("--------");
        System.out.println("COMMANDS:");
        System.out.println("--------");
        displayCurrentRoomActions();
    }

}