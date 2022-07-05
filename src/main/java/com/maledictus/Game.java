package com.maledictus;

import com.maledictus.item.Item;
import com.maledictus.item.ItemType;
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
import java.awt.*;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;
import java.util.List;

import static com.maledictus.Json.items6;
import static com.maledictus.Json.returnGameText;

public class Game {

    public static final Color ANSI_RED = Color.RED;
    public static final Color ANSI_GREEN = Color.GREEN;
    public static final Color ANSI_YELLOW = Color.YELLOW;
    public static final Color ANSI_BLUE = Color.BLUE;
    private static final Map<String, Room> roomMap = RoomFactory.getRoomMap();
    private static Map<Integer, NPC> npcMap;
    private static Player playerOne;
    private static ArrayList<Item> roomItems;
    private static Map<String, String> roomDirections;
    private static Room currentRoom;
    private static String errorMsg = null;
    private static String successMsg = null;
    private static boolean inBattle = false;
    private static Battle battle;
    private static int battleEnemy;
    private static Game instance;
    private  final GameMusic gameMusic = new GameMusic();
    private final BattleMusic battleMusic = new BattleMusic();
    public boolean doesPlayerHaveHellBlade= false;
    public boolean hasPlayerWon = false;//boolean to measure win game for getting the sword
    public boolean hasPlayerLost = false;//boolean for loss, that happens when Hp hits 0

    public Game() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
    }


    public static Game getInstance() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        if(instance == null) {
            instance = new Game();
        }
        return instance;
    }

    private static void displayIntroText() throws IOException, org.json.simple.parser.ParseException {
        Printer.print(returnGameText("1") + "\n" + returnGameText("11")+ "\n");

    }

    public void initiateGame() throws IOException, org.json.simple.parser.ParseException, ParseException, UnsupportedAudioFileException, LineUnavailableException {
        Json.jsonWrite();
        Printer.print(returnGameText("2") + "\n");
        startGame();
        Json.createItems();
        Json.createNPCs();
        Json.createRoomList();
        currentRoom = roomMap.get("Great Hall");
        GameMusic.playMusic();

    }

    public void createCharacter() {
        Printer.print("Enter your character's name:");
        GUI gui = GUI.getInstance();
        gui.getInputtedUser().addActionListener(e -> {
            String text = gui.getInputtedUser().getText();
            gui.getInputtedUser().setText("");
            gui.getInputtedUser().removeActionListener(gui.getInputtedUser().getActionListeners()[0]);

            playerOne = PlayerFactory.createPlayer(text);
            try {
                displayConsoleCommands();
                start();
            } catch (IOException | org.json.simple.parser.ParseException | ParseException | UnsupportedAudioFileException | LineUnavailableException | InterruptedException ex) {
                ex.printStackTrace();
            }
        });

    }

    public void startGame() throws IOException, org.json.simple.parser.ParseException {
        GUI gui = GUI.getInstance();
        Printer.print(returnGameText("3"));
        Printer.print(returnGameText("4"));
        gui.getInputtedUser().addActionListener(e -> {
            String text = gui.getInputtedUser().getText();
            gui.getInputtedUser().setText("");

            try {
                gui.getInputtedUser().removeActionListener(gui.getInputtedUser().getActionListeners()[0]);
                validatePlayerInput(text);
            } catch (IOException | org.json.simple.parser.ParseException ex) {
                ex.printStackTrace();
            }
        });

    }

    public void validatePlayerInput(String startGame) throws IOException, org.json.simple.parser.ParseException {
//        ---------- VALIDATE PLAYER INPUT ---------
        if (startGame.equals("1")) {
            displayIntroText();
            createCharacter();
        } else if (startGame.equals("2")) {
            Printer.print("Exiting the game...");
            System.exit(0);
        } else {
            errorMsg =  "Invalid Selection.  Please enter [1] to start game or [2] to quit." ;
            printErrorMsg();
            startGame();
        }
    }

    public void displayGameMap() {
        try{
            WelcomePage.Map();

        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    private void start() throws IOException, org.json.simple.parser.ParseException, ParseException, UnsupportedAudioFileException, LineUnavailableException, InterruptedException {

        if(doesPlayerHaveTheHellBlade()){
            System.out.println("you actually have the award? ");
            Thread.sleep(2000);
            System.out.println("the spirit of tornomous awakens");
            Thread.sleep(2000);
            System.out.println(" a the hellblade begins to grow larger and larger, with eyes appearing on the hilt \n why thank you...");
            Thread.sleep(2000);
            System.out.println("been a while since I've seen the sun, ever since he took me to die with him... this dark decrepit place");
            Thread.sleep(3000);
            System.out.println("Can you take me to see the sun?");
            Thread.sleep(3000);
            System.out.println("YOU WON GAME OVER");
            System.exit(0);
        }

        boolean round = true;
        if (playerOne.getHitPoints() == 0) {
            round = false;
            Printer.print("You're dead, D. E. D.");
        }

        if (round && !inBattle) {
            // Methods will check if an error or success message needs to be printed

            Printer.print(ANSI_YELLOW, "\n Enter a command or enter [options] to see game options:" );


            GUI gui = GUI.getInstance();

            gui.getInputtedUser().addActionListener(e -> {
                String userCommand = gui.getInputtedUser().getText();
                gui.getInputtedUser().setText("");
                gui.getInputtedUser().removeActionListener(gui.getInputtedUser().getActionListeners()[0]);

                // Splitting userCommand into two separate strings. (Verb, Noun)
                String[] userInput = userCommand.split(" ", 2);
                try {
                    getUserInput(userInput);
                } catch (UnsupportedAudioFileException | LineUnavailableException | IOException | org.json.simple.parser.ParseException | ParseException | InterruptedException ex) {
                    ex.printStackTrace();
                }
            });
        }

        if (round && inBattle) {
            displayBattleCommands();
            npcMap.get(battleEnemy).setItem(items6.get(0));
            battle.battleStart();
        }
    }

    public void endFight() {

        battle.setCombat(false);
        if (!battle.isCombat()) {
            inBattle = false;
        }

        if(Objects.equals(npcMap.get(battleEnemy).getName(), "The King")){
            currentRoom.addItem(npcMap.get(battleEnemy).getItem());
            System.out.println("item added to room");
        }
            BattleMusic.stopMusic();
            GameMusic.playMusic();
        npcMap.remove(battleEnemy);

        try {
            this.start();
        } catch (IOException | org.json.simple.parser.ParseException | ParseException | UnsupportedAudioFileException | LineUnavailableException | InterruptedException ex) {
            ex.printStackTrace();
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
                printSuccessMsg();
                break;
            }
        }
        if (!itemFound) {
            Printer.print(ANSI_RED, "INVALID ITEM ERROR: You wrote take '" + userInput[1] + "' that is not a valid item option, please try again. (Example: 'take iron sword')");
        }
        try {
            start();
        } catch (IOException | org.json.simple.parser.ParseException | ParseException | UnsupportedAudioFileException | LineUnavailableException | InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    private void inspectItem(String[] userInput) {
        boolean itemFound = false;

            // Search for item through player inventory
            for (Map.Entry<String, Item> item : playerOne.getInventory().entrySet()) {
                if(userInput[1].equalsIgnoreCase(item.getKey())) {
                    itemFound = true;
                    successMsg = "Inspect: " +  item.getValue().getDescription();
                    printSuccessMsg();
                    break;
                }
            }
            if (!itemFound) {
                for (Item item : roomItems) {
                    if(userInput[1] != null && item.getName().equalsIgnoreCase(userInput[1])) {
                        itemFound = true;
                        successMsg = "Inspect: " + item.getDescription();
                        printSuccessMsg();
                        break;
                    }
                }
            }

            if (!itemFound) {
                errorMsg = "INVALID ITEM ERROR: You wrote inspect '" + userInput[1] + "' that is not a valid item option, please try again. (Example: 'take iron sword')" ;
                printErrorMsg();
            }
        try {
            start();
        } catch (IOException | org.json.simple.parser.ParseException | ParseException | UnsupportedAudioFileException | LineUnavailableException | InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    private void takeItem(String[] userInput) {
        boolean itemFound = false;
        for (Item item : roomItems) {
            if(userInput[1] != null && item.getName().equalsIgnoreCase(userInput[1])) {
                itemFound = true;
                playerOne.addItem(item);
                roomItems.remove(item);
                displayConsoleCommands();
                successMsg = item.getName() + " was added to your inventory.";
                printSuccessMsg();
                break;
            }
        }
        if (!itemFound) {
            errorMsg = "INVALID ITEM ERROR: You wrote take '" + userInput[1] + "' that is not a valid item option, please try again. (Example: 'take iron sword')" ;
            printErrorMsg();
        }
        try {
            start();
        } catch (IOException | org.json.simple.parser.ParseException | ParseException | UnsupportedAudioFileException | LineUnavailableException | InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    private void useItem(String[] userInput) {
        boolean itemFound = false;
        System.out.println("PLAYER HEALTH: " + playerOne.getHitPoints());
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
            printErrorMsg();
        }
        try {
            System.out.println("PLAYER HEALTH: " + playerOne.getHitPoints());
            start();
        } catch (IOException | org.json.simple.parser.ParseException | ParseException | UnsupportedAudioFileException | LineUnavailableException | InterruptedException ex) {
            ex.printStackTrace();
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
                        printSuccessMsg();
                    } else {
                        successMsg = "WARNING: You tried to go to the room located in the " + targetDirection + " direction. The door to this room is locked, you must find the proper key first. \nCome back when you have the right key." ;
                        printSuccessMsg();
                        break;
                    }
                } else if (userInput[1].equalsIgnoreCase(targetDirection) && !targetRoom.isLocked()) {
                    roomFound = true;
                    currentRoom = roomMap.get(direction.getValue());
                    roomItems = currentRoom.getItems();
                    successMsg = "You went " + targetDirection + " into the " + targetRoom.getName();
                    printSuccessMsg();
                }
            }
            if (!roomFound) {
                errorMsg =  "INVALID LOCATION ERROR: You wrote go '" + userInput[1] + "' that is not a valid room option, please try again. (Example: 'go north')" ;
                printErrorMsg();
            }
        try {
            displayConsoleCommands();
            start();
        } catch (IOException | org.json.simple.parser.ParseException | ParseException | UnsupportedAudioFileException | LineUnavailableException | InterruptedException ex) {
            ex.printStackTrace();
        }
        }


    private void getUserInput(String[] userInput) throws UnsupportedAudioFileException, LineUnavailableException, IOException, ParseException, ParseException, org.json.simple.parser.ParseException, InterruptedException {

        // Making sure the user uses the valid syntax of "verb[word]" + SPACE + "noun[word(s)]" (example: take Iron Sword)
        String input = userInput[0].toLowerCase();
        switch (input) {
            case "go" :
                moveRoom(userInput);
                break;
            case "take" :
                takeItem(userInput);
                break;
            case "inspect" :
                inspectItem(userInput);
                break;
            case "drop":
                dropItem(userInput);
                break;
            case "use":
                useItem(userInput);
                break;
            case "heal":
                healPlayer();
                break;
            case "talk" :
                talkToNpc(userInput);
                break;
            case "options" :
                displayOptions();
                break;
            case "battle" :
                Map<Integer, NPC> currentNPCs = currentRoom.getNpcMap();
                for(NPC npc : currentNPCs.values()) {
                    String current = npc.getName();
                    if(current.equalsIgnoreCase(userInput[1]) && npc.getIsHostile()) {
                        inBattle = true;
                        battleEnemy = npc.getId();
                        battle = new Battle(playerOne, npc);
                        GameMusic.stopMusic();
                        battleMusic.playMusic();
//                        call to start - may not work
                        start();
                    }
                }
                break;
            default:
                errorMsg =  "INVALID ACTION ERROR: user input of '" + userInput[0] + "' is an invalid action input. (Example: 'go', 'take')" ;
                printErrorMsg();
                start();
                break;
        }

    }

    private void healPlayer() {

        boolean itemFound = false;
        for(Item item : playerOne.getInventory().values()) {
            if(item.getName().equalsIgnoreCase("Healing Potion")) {
                itemFound = true;
                playerOne.removeItem(item);
                playerOne.heal(50);
                successMsg = "You feel rejuvenated! You now have: " + playerOne.getHitPoints() + " hit points";
                printSuccessMsg();
                break;
            }
        }
        if (!itemFound) {
            Printer.print(ANSI_RED, "You do not have any potions at this time");
        }
        try {
            start();
        } catch (IOException | org.json.simple.parser.ParseException | ParseException | UnsupportedAudioFileException | LineUnavailableException | InterruptedException ex) {
            ex.printStackTrace();
        }

    }

    //return boolean value after validating if character has that or not
    public boolean doesPlayerHaveTheHellBlade() {
        return playerOne.getInventory().containsKey("Hell Blade");
    }

    private void talkToNpc(String[] userInput) throws UnsupportedAudioFileException, LineUnavailableException, IOException, org.json.simple.parser.ParseException, ParseException, InterruptedException {
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
            errorMsg = "INVALID NPC ERROR: You wrote talk '" + userInput[1] + "' that is not a valid NPC option, please try again. (Example: 'talk ghostly soldier')";
            printErrorMsg();
        }
    }

    private void chooseDialog(NPC targetNpc) throws UnsupportedAudioFileException, LineUnavailableException, IOException, org.json.simple.parser.ParseException, ParseException, InterruptedException {
        Ghost npc = (Ghost) targetNpc;
        GUI gui = GUI.getInstance();

        if (npc.getQuest() == null || (!npc.getQuestStatus() && !npc.getQuest().isCompleted())) {

            Printer.print(npc.getName() + ": " + npc.talk(1));
            if (npc.getQuest() != null) {
                Printer.print("Press [1] Accept Quest? \nPress [2] to exit.");
                gui.getInputtedUser().addActionListener(e -> {
                    String questDialogChoice = gui.getInputtedUser().getText();
                    gui.getInputtedUser().setText("");
                    gui.getInputtedUser().removeActionListener(gui.getInputtedUser().getActionListeners()[0]);
                    if (questDialogChoice.equals("1")) {
                        System.out.println("===== ASSIGNING QUEST TO TRUE =====");
                        npc.assignQuest(true);
                        try {
                            start();
                        } catch (IOException | org.json.simple.parser.ParseException | UnsupportedAudioFileException | ParseException | LineUnavailableException | InterruptedException ex) {
                            ex.printStackTrace();
                        }
                    }
                });
            }
            else {
                start();
            }
        }
        // active quest
        else if (npc.getQuestStatus() && !npc.getQuest().isCompleted()) {
            successMsg = npc.getName() + ": " + npc.questTalk(1);
            printSuccessMsg();

            Printer.print("Press [1] give " + npc.getQuestWinCondition() + "\nPress [2] to exit.");

            gui.getInputtedUser().addActionListener(e -> {
                String questDialogChoice = gui.getInputtedUser().getText();
                gui.getInputtedUser().setText("");
                gui.getInputtedUser().removeActionListener(gui.getInputtedUser().getActionListeners()[0]);
                if (questDialogChoice.equals("1") && playerOne.getInventory().containsKey(npc.getQuestWinCondition())) {
                    playerOne.removeItem(playerOne.getInventory().get(npc.getQuestWinCondition()));
                    successMsg = npc.getName() + ": " + npc.questTalk(2) + "\nYou received a(n) " + npc.getQuest().getReward().getName() + " from " + npc.getName();
                    printSuccessMsg();
                    npc.setQuestCompleted(true);
                    npc.assignQuest(false);
                    playerOne.addItem(npc.giveQuestReward());
                    Printer.print("\nYou received a(n) " + npc.getQuest().getReward().getName() + " from " + npc.getName());
                    try {
                        start();
                    } catch (IOException | org.json.simple.parser.ParseException | ParseException | UnsupportedAudioFileException | LineUnavailableException | InterruptedException ex) {
                        ex.printStackTrace();
                    }
                } else {
                    successMsg = npc.getName() + ": " + npc.questTalk(3);
                    printSuccessMsg();
                    try {
                        start();
                    } catch (IOException | org.json.simple.parser.ParseException | ParseException | UnsupportedAudioFileException | LineUnavailableException | InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            });

        } else {
            successMsg = npc.getName() + ": " + npc.questTalk(4);
            printSuccessMsg();
            start();
        }

    }

    private void displayOptions() {
        GUI gui = GUI.getInstance();
        Printer.print("Press [1] to start a new game.\nPress [2] to quit.\nPress [3] for game info.\nPress [4] to stop Music.\nPress [5] to play Music.\nPress [6] to display game maps.\nPress [7] to resume game.\n");
        gui.getInputtedUser().addActionListener(e -> {
            String optionInput = gui.getInputtedUser().getText();
            gui.getInputtedUser().setText("");
            gui.getInputtedUser().removeActionListener(gui.getInputtedUser().getActionListeners()[0]);

            switch (optionInput) {
                case "1":
                    RoomFactory.clearRoomMap();
                    Json.items.clear();
                    Json.items2.clear();
                    try {
                        initiateGame();
                    } catch (IOException | org.json.simple.parser.ParseException | ParseException | UnsupportedAudioFileException | LineUnavailableException ex) {
                        ex.printStackTrace();
                    }
                    break;
                case "2":
                    Printer.print("Exiting game. Thank you for playing.");
                    System.exit(0);
                    break;
                case "3":
                    Printer.print("Maledictus is a console text-adventure game. You are a treasure hunter in seek of riches.  Your goal is to traverse the map, discover what lies within, and make it out alive!\nGame created by team Lefties: Ryan Mosser, Michael Herman, and Nikko Colby\n which was then taken into further production by Marcos Cardoso, Jose Mondragon and Samekh Resh");
                    try {
                        start();
                    } catch (IOException | org.json.simple.parser.ParseException | ParseException | UnsupportedAudioFileException | LineUnavailableException | InterruptedException ex) {
                        ex.printStackTrace();
                    }
                    break;
                case "4":
                    GameMusic.stopMusic();
                    try {
                        start();
                    } catch (IOException | org.json.simple.parser.ParseException | ParseException | UnsupportedAudioFileException | LineUnavailableException | InterruptedException ex) {
                        ex.printStackTrace();
                    }
                    break;
                case "5":
                    GameMusic.playMusic();
                    try {
                        start();
                    } catch (IOException | org.json.simple.parser.ParseException | ParseException | UnsupportedAudioFileException | LineUnavailableException | InterruptedException ex) {
                        ex.printStackTrace();
                    }
                    break;
                case "6":
                    displayGameMap();
                    try {
                        start();
                    } catch (IOException | org.json.simple.parser.ParseException | ParseException | UnsupportedAudioFileException | LineUnavailableException | InterruptedException ex) {
                        ex.printStackTrace();
                    }
                    break;
                case "7":
                    try {
                        start();
                    } catch (IOException | org.json.simple.parser.ParseException | ParseException | UnsupportedAudioFileException | LineUnavailableException | InterruptedException ex) {
                        ex.printStackTrace();
                    }
                    break;
                default:
                    errorMsg =  "Invalid Selection. Please try again." ;
                    printErrorMsg();
                    try {
                        start();
                    } catch (IOException | org.json.simple.parser.ParseException | ParseException | UnsupportedAudioFileException | LineUnavailableException | InterruptedException ex) {
                        ex.printStackTrace();
                    }
                    break;
            }
        });
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
            Printer.print(ANSI_RED, "\n" + errorMsg);
            errorMsg = null;
        }
    }

    private void printSuccessMsg() {
        if (successMsg != null) {
            Printer.print(ANSI_GREEN, "\n" + successMsg);
            successMsg = null;
        }
    }

    private void displayInventory() {
        Printer.print(playerOne.getInventory().keySet());
    }

    private void displayConsoleCommands() {
        Printer.print("-------------");
        Printer.print(ANSI_GREEN , "CURRENT ROOM:" );
        Printer.print("-------------");
        Printer.print(currentRoom.getName());
        Printer.print(currentRoom.getDescription());

        Printer.print("-------------");
        Printer.print(ANSI_BLUE, "INVENTORY:" );
        Printer.print("-------------");
        displayInventory();

        Printer.print("-------------");
        Printer.print(ANSI_YELLOW, "COMMANDS:" );
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