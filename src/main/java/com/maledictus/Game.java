package com.maledictus;

import com.maledictus.item.Item;
import com.maledictus.item.key.Key;
import com.maledictus.music.BattleMusic;
import com.maledictus.music.GameMusic;
import com.maledictus.npc.Ghost;
import com.maledictus.npc.NPC;
import com.maledictus.player.Player;
import com.maledictus.player.PlayerFactory;
import com.maledictus.room.Room;
import com.maledictus.room.RoomFactory;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.maledictus.Input.scannerUserInput;
import static com.maledictus.Json.returnGameText;

public class Game {

    private final Map<String, Room> roomMap = RoomFactory.getRoomMap();
    private final GameMusic gameMusic = new GameMusic();
    private final BattleMusic battleMusic = new BattleMusic();
    private Map<Integer, NPC> npcMap;
    private Player playerOne;
    private ArrayList<Item> roomItems;
    private Map<String, String> roomDirections;
    private Room currentRoom;
    private String errorMsg = null;
    private String successMsg = null;
    private boolean inBattle = false;
    private Battle battle;
    private int battleEnemy;
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public boolean hasPlayerWon = false;//boolean to measure win game

    public Game() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
    }

    public void initiateGame() throws IOException, org.json.simple.parser.ParseException, ParseException, UnsupportedAudioFileException, LineUnavailableException {
        Json.jsonWrite();
        displaySplash();
        createCharacter();
        Json.createItems();
        Json.createNPCs();
        Json.createRoomList();
        currentRoom = roomMap.get("Great Hall");
        gameMusic.playMusic();

        start();

    }

    public void createCharacter() {
        playerOne = PlayerFactory.createPlayer();
    }

    public static void displaySplash() throws IOException, org.json.simple.parser.ParseException {

        boolean play = true;

//        //URL titleBanner = null;
        URL titleBanner = Game.class.getResource("data/splash_banner.txt");
        //Printer.print(titleBanner);
        Printer.print(returnGameText("2") + "\n");

        while (play) {

            Printer.print(returnGameText("3"));
            Printer.print(returnGameText("4"));

            String startGame = scannerUserInput();

            if (startGame.equals("1")) {
                displayIntroText();
                play = false;
            } else if (startGame.equals("2")) {
                Printer.print("Exiting the game...");
                System.exit(1);
            } else {
                String errorMsg = ANSI_RED+ "Invalid Selection.  Please enter [1] to start game or [2] to quit." +ANSI_RESET;
            }
        }
    }

    public void displayGameMap() {
        boolean invalidSelection = true;
        while (invalidSelection) {
            String gameMap = null;
            Printer.print("Enter [1] for Main floor map, [2] for downstairs map");
            try {
                String displayMap = scannerUserInput();
                if (displayMap.equals("1")) {
                    gameMap = Files.readString(Path.of("resources/data/mainfloor_map.txt"));
                    Printer.print(gameMap);
                    invalidSelection = false;
                } else if (displayMap.equals("2")) {
                    gameMap = Files.readString(Path.of("resources/data/downstairs-map.txt"));
                    Printer.print(gameMap);
                    invalidSelection = false;
                } else {
                    Printer.print(ANSI_RED + "Invalid Selection.  Please enter [1] to display Main floor map, or  [2] to display downstairs map." + ANSI_RESET);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void start() throws IOException, org.json.simple.parser.ParseException, ParseException, UnsupportedAudioFileException, LineUnavailableException {

        boolean round = true;
        if (playerOne.getHitPoints() == 0) {
            round = false;
            Printer.print("You're dead, D. E. D.");
        }
        while (round && !this.inBattle) {

            displayConsoleCommands();
            // Methods will check if an error or success message needs to be printed
            printSuccessMsg();
            printErrorMsg();
            Printer.print("\n" + ANSI_YELLOW + "Enter a command or enter [options] to see game options: " + ANSI_RESET);

            // Take in user input and run through scanner
            String userCommand = scannerUserInput();

            // Splitting userCommand into two separate strings. (Verb, Noun)
            String[] userInput = userCommand.split(" ", 2);

            // Check to see if user input is expected array format

            getUserInput(userInput);
        }
        while (round && this.inBattle) {
            displayBattleCommands();
            this.battle.start();
            this.battle.setCombat(false);
            if(!this.battle.isCombat()) {
                this.inBattle = false;
            }
            this.battleMusic.stopMusic();
            this.gameMusic.playMusic();
            Printer.print("is it getting here?");
            npcMap.remove(battleEnemy);
            this.start();
            // Take in user input and run through scanner
            // String userCommand = scannerUserInput();

            // Splitting userCommand into two separate strings. (Verb, Noun)
            // String[] userInput = userCommand.split(" ", 2);

            // Check to see if user input is expected array format

            // getUserInput(userInput);
        }
    }

    private void dropItem(String[] userInput) {
        boolean itemFound = false;
        for(Item item : playerOne.getInventory().values()) {
            if(item.getName().equalsIgnoreCase(userInput[1])) {
                itemFound = true;
                playerOne.removeItem(item);
                roomItems.add(item);
                successMsg = item.getName() + " was dropped from your inventory.";
                break;
            }
        }
        if (!itemFound) {
            Printer.print(ANSI_RED+ "INVALID ITEM ERROR: You wrote take '" + userInput[1] + "' that is not a valid item option, please try again. (Example: 'take iron sword')" + ANSI_RESET);
        }
    }

    private void inspectItem(String[] userInput) {
        boolean itemFound = false;

            // Search for item through player inventory
            for (Map.Entry<String, Item> item : playerOne.getInventory().entrySet()) {
                if(userInput[1].equalsIgnoreCase(item.getKey())) {
                    itemFound = true;
                    successMsg = "Inspect: " +  item.getValue().getDescription();
                    break;
                }
            }
            if (!itemFound) {
                for (Item item : roomItems) {
                    if(userInput[1] != null && item.getName().equalsIgnoreCase(userInput[1])) {
                        itemFound = true;
                        successMsg = "Inspect: " + item.getDescription();
                        break;
                    }
                }
            }

            if (!itemFound) {
                errorMsg = ANSI_RED + "INVALID ITEM ERROR: You wrote inspect '" + userInput[1] + "' that is not a valid item option, please try again. (Example: 'take iron sword')" + ANSI_RESET;
            }
    }

    private void takeItem(String[] userInput) {
        boolean itemFound = false;
        for (Item item : roomItems) {
            if(userInput[1] != null && item.getName().equalsIgnoreCase(userInput[1])) {
                itemFound = true;
                playerOne.addItem(item);
                roomItems.remove(item);
                successMsg = item.getName() + " was added to your inventory.";
                break;
            }
        }
        if (!itemFound) {
            errorMsg = ANSI_RED+ "INVALID ITEM ERROR: You wrote take '" + userInput[1] + "' that is not a valid item option, please try again. (Example: 'take iron sword')" + ANSI_RESET;
        }
    }

    private void useItem(String[] userInput) {
        boolean itemFound = false;
        for (Item item : playerOne.getInventory().values()) {
            if(userInput[1] != null && item.getName().equalsIgnoreCase(userInput[1])) {
                itemFound = true;
                item.use();
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
                Room targetRoom = roomMap.get(direction.getValue());
                String targetDirection = direction.getKey();
                if (userInput[1].equalsIgnoreCase(targetDirection) && targetRoom.isLocked()) {
                    roomFound = true;
                    Key foundKey = playerOne.getDoorKey(targetRoom.getRequiredKeyType());
                    if (foundKey != null) {
                        targetRoom.unlockRoom(foundKey.getKeyType());
                        currentRoom = roomMap.get(direction.getValue());
                        roomItems = currentRoom.getItems();
                        successMsg = "You used the " + foundKey.getName() + " and unlocked the door to the " + targetRoom.getName() + ".\nYou went " + targetDirection + " into the " + targetRoom.getName();
                    } else {
                        successMsg = ANSI_RED + "WARNING: You tried to go to the room located in the " + targetDirection + " direction. The door to this room is locked, you must find the proper key first. \nCome back when you have the right key." + ANSI_RESET;
                        break;
                    }
                } else if (userInput[1].equalsIgnoreCase(targetDirection) && !targetRoom.isLocked()) {
                    roomFound = true;
                    currentRoom = roomMap.get(direction.getValue());
                    roomItems = currentRoom.getItems();
                    successMsg = "You went " + targetDirection + " into the " + targetRoom.getName();
                }
            }
            if (!roomFound) {
                errorMsg = ANSI_RED + "INVALID LOCATION ERROR: You wrote go '" + userInput[1] + "' that is not a valid room option, please try again. (Example: 'go north')" + ANSI_RESET;
            }
        }

    private void getUserInput(String[] userInput) throws UnsupportedAudioFileException, LineUnavailableException, IOException, ParseException, ParseException, org.json.simple.parser.ParseException {
        // Making sure the user uses the valid syntax of "verb[word]" + SPACE + "noun[word(s)]" (example: take Iron Sword)
            if(userInput[0].equalsIgnoreCase("go")) {
                moveRoom(userInput);
            } else if(userInput[0].equalsIgnoreCase("take") || userInput[0].equalsIgnoreCase("grab")) {
                takeItem(userInput);
            } else if(userInput[0].equalsIgnoreCase("inspect")) {
                inspectItem(userInput);
            } else if(userInput[0].equalsIgnoreCase("drop")) {
                dropItem(userInput);
            } else if(userInput[0].equalsIgnoreCase("use")) {
                useItem(userInput);
            } else if(userInput[0].equalsIgnoreCase("heal")) {
                useItem(userInput);
            } else if(userInput[0].equalsIgnoreCase("talk")) {
                talkToNpc(userInput);
            } else if(userInput[0].equalsIgnoreCase("options")) {
                displayOptions();
            } else if(userInput[0].equalsIgnoreCase("battle")) {
                Map<Integer, NPC> currentNPCs = currentRoom.getNpcMap();
                for(NPC npc : currentNPCs.values()) {
                    String current = npc.getName();
                    if(current.equalsIgnoreCase(userInput[1]) && npc.getIsHostile()) {
                        this.inBattle = true;
                        this.battleEnemy = npc.getId();
                        battle = new Battle(playerOne, npc);
                        gameMusic.stopMusic();
                        battleMusic.playMusic();
                    }
                }
            } else {
                errorMsg = ANSI_RED + "INVALID ACTION ERROR: user input of '" + userInput[0] + "' is an invalid action input. (Example: 'go', 'take')" + ANSI_RESET;
            }
    }

    private void talkToNpc(String[] userInput) {
        boolean npcFound = false;
        for (Map.Entry<Integer, NPC> npc : npcMap.entrySet()) {
            if (userInput[1].equalsIgnoreCase(npc.getValue().getName())) {
                NPC targetNpc = npc.getValue();
                chooseDialog(targetNpc);
                npcFound = true;
                break;
            }
        }
        if (!npcFound) {
            errorMsg = ANSI_RED + "INVALID NPC ERROR: You wrote go '" + userInput[1] + "' that is not a valid NPC option, please try again. (Example: 'talk ghostly soldier')" + ANSI_RESET;
        }
    }

    private void chooseDialog(NPC targetNpc) {
        Ghost npc = (Ghost) targetNpc;

        if (npc.getQuest() == null || (!npc.getQuestStatus() && !npc.getQuest().isCompleted())) {
            Printer.print(npc.getName() + ": " + npc.talk(1));
            for (int i = 2; i < npc.getDialog().entrySet().size() + 1; i++) {
                Printer.print("Press [1] to continue talking. \nPress [2] to exit.");
                 String dialogChoice = scannerUserInput();
                if (dialogChoice.equals("1")) {
                    Printer.print(npc.getName() + ": " + npc.talk(i));
                 } else if (dialogChoice.equals("2")) {
                     break;
                }
            }
            if (npc.getQuest() != null) {
                Printer.print("Press [1] Accept Quest? \nPress [2] to exit.");
                String questDialogChoice = scannerUserInput();
                if (questDialogChoice.equals("1")) {
                    npc.assignQuest(true);
                }
            } else {
                Printer.print("Press [2] to exit.");
                scannerUserInput();
            }
        } else if (npc.getQuestStatus() && !npc.getQuest().isCompleted()) {
               successMsg = npc.getName() + ": " + npc.questTalk(1);
                Printer.print("Press [1] give " + npc.getQuestWinCondition() + "\nPress [2] to exit.");
                String questDialogChoice = scannerUserInput();
                if (questDialogChoice.equals("1") && playerOne.getInventory().containsKey(npc.getQuestWinCondition())) {
                    playerOne.removeItem(playerOne.getInventory().get(npc.getQuestWinCondition()));
                    successMsg = npc.getName() + ": " + npc.questTalk(2) + "\nYou received a(n) " + npc.getQuest().getReward().getName() + " from " + npc.getName();
                    npc.setQuestCompleted(true);
                    npc.assignQuest(false);
                    playerOne.addItem(npc.giveQuestReward());
                    Printer.print("\nYou received a(n) " + npc.getQuest().getReward().getName() + " from " + npc.getName());
                } else  {
                   successMsg = npc.getName() + ": " + npc.questTalk(3);
                }
        } else {
           successMsg = npc.getName() + ": " + npc.questTalk(4);
        }
    }

    private void displayOptions() throws IOException, org.json.simple.parser.ParseException, ParseException, UnsupportedAudioFileException, LineUnavailableException {
        boolean waitingOnInput = true;

        while (waitingOnInput) {

            Printer.print("Press [1] to start a new game.\nPress [2] to quit.\nPress [3] for game info.\nPress [4] to stop Music.\nPress [5] to play Music.\nPress [6] to display game maps.\nPress [7] to resume game.\n8 to change game volume");
            String optionInput = scannerUserInput();

            switch (optionInput) {
                case "1":
                    //TODO: Test and confirm reset works.
                    RoomFactory.clearRoomMap();
                    Json.items.clear();
                    Json.items2.clear();
                    initiateGame();
                    waitingOnInput = false;
                    break;
                case "2":
                    Printer.print("Exiting game. Thank you for playing.");
                    System.exit(1);
                    break;
                case "3":
                    Printer.print("Maledictus is a console text-adventure game. You are a treasure hunter is seek of riches.  Your goal is to traverse the map, discover what lies within, and make it out alive!\nGame created by team Lefties: Ryan Mosser, Michael Herman, and Nikko Colby\n which was then taken into further production by Marcos Cardoso, Jose Mondragon and Samekh Resh");
                    break;
                case "4":
                    gameMusic.stopMusic();
                    break;
                case "5":
                    gameMusic.playMusic();
                    break;
                case "6":
                    displayGameMap();
                    break;
                case "7":
                    waitingOnInput = false;
                    break;
                case "8":
                    changeVolume();
                default:
                    errorMsg = ANSI_RED + "Invalid Selection. Please try again." + ANSI_RESET;
                    break;
            }
        }
    }

    private void changeVolume() {
        Printer.print("1 for low\n2 for med low\n3 for medium\n4 for med-high\n5 for high");
        String choice = scannerUserInput();
        if (choice.equals("1")){
            gameMusic.setMusicLow();
        }else if (choice.equals("2")){
            gameMusic.setMusicMidLow();
        }else if (choice.equals("3")){
            gameMusic.setMusicMidRange();
        }else if (choice.equals("4")){
            gameMusic.setMusicMidHigh();
        }else if (choice.equals("5")){
            gameMusic.setMusicHigh();
        }else {
            Printer.print(ANSI_RED + "INVALID ENTRY: must use the letters 1 through 5 for volume manipulation");
            changeVolume();
        }
    }

    private static void displayIntroText() throws IOException, org.json.simple.parser.ParseException {
        Printer.print(returnGameText("1") + "\n" + returnGameText("11")+ "\n");
    }

    private void displayCurrentRoomActions() {
        if(currentRoom != null) {
            displayRoomDirections();
            displayRoomItems();
            displayInventoryActions();
            displayAllRoomNpc();
        }
    }

    private void displayRoomItems() {
        List<String> displayList = new ArrayList<>();
        if (currentRoom.getItems() != null) {
            roomItems = currentRoom.getItems();
            for (Item item : roomItems) {
                displayList.add("take/inspect " + item.getName());
            }
        }
        Printer.print("Room Items: " + displayList);
    }

    private void displayInventoryActions() {
        List<String> displayList = new ArrayList<>();
        if (playerOne.getInventory() != null) {
            for (Map.Entry<String, Item> item  : playerOne.getInventory().entrySet()) {
                displayList.add("use/drop/inspect " + item.getValue().getName());
            }
        }
        Printer.print("Inventory Items: " + displayList);
    }

    private void displayRoomDirections() {
        List<String> displayList = new ArrayList<>();
        if (currentRoom.getDirections() != null) {
            roomDirections = currentRoom.getDirections();
            for (Map.Entry<String, String> direction : roomDirections.entrySet()) {
                displayList.add("go " + direction.getKey());
            }
        }
        Printer.print("Directions: " + displayList);
    }

    private void displayAllRoomNpc() {
        List<String> displayList = new ArrayList<>();
        if (currentRoom.getNpcMap() != null) {
            npcMap = currentRoom.getNpcMap();
            for (Map.Entry<Integer, NPC> npc : npcMap.entrySet()) {
                if (!npc.getValue().getIsHostile()) {
                    displayList.add("talk " + npc.getValue().getName());
                } else {
                    displayList.add("battle " + npc.getValue().getName());
                }
            }
        }
        Printer.print("NPCs: " +displayList);
    }

    private void printErrorMsg() {
        if (errorMsg != null) {
            Printer.print("\n" + errorMsg);
            errorMsg = null;
        }
    }

    private void printSuccessMsg() {
        if (successMsg != null) {
            Printer.print("\n" + successMsg);
            successMsg = null;
        }
    }

    private void displayInventory() {
        Printer.print(playerOne.getInventory().keySet());
    }

    private void displayConsoleCommands() {
        Printer.print("-------------");
        Printer.print(ANSI_GREEN + "CURRENT ROOM:" + ANSI_RESET);
        Printer.print("-------------");
        Printer.print(currentRoom.getName());
        Printer.print(currentRoom.getDescription());

        Printer.print("-------------");
        Printer.print(ANSI_BLUE+ "INVENTORY:" + ANSI_RESET);
        Printer.print("-------------");
        displayInventory();

        Printer.print("-------------");
        Printer.print(ANSI_YELLOW+ "COMMANDS:" + ANSI_RESET);
        Printer.print("-------------");
        displayCurrentRoomActions();
        Printer.print("-------------");
    }

    private void displayBattleActions() {
        Printer.print("[attack], [run] or [equip]");
    }

    private void displayBattleCommands() {
        Printer.print("----------");
        Printer.print("IN BATTLE");
        Printer.print("----------");
        Printer.print("INVENTORY:");
        Printer.print("----------");
        displayInventory();

        Printer.print("-------------");
        Printer.print("COMMANDS:");
        Printer.print("-------------");
        displayBattleActions();
        Printer.print("-------------");
    }
}