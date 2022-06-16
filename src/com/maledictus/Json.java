package com.maledictus;

import com.maledictus.item.*;
import com.maledictus.item.ItemFactory;
import com.maledictus.item.key.KeyType;
import com.maledictus.item.potion.PotionType;
import com.maledictus.item.weapon.*;
import com.maledictus.room.RoomFactory;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.*;

public class Json {

    public static ArrayList<Item> items = new ArrayList<>();
    public static ArrayList<Item> items2 = new ArrayList<>();

    public static void jsonWrite() throws FileNotFoundException {
        JSONObject data = new JSONObject();
        data.put("room0", "Great Hall");
        data.put("room0Description", "The main area of the castle.");

        data.put("room1", "Dining room");
        data.put("room1Description", "A long, rectangular dining table set with the finest silver cutlery and porcelain tableware fills the room.  A fireplace is built into the east wall behind the head of the table.  A large, crystal chandelier hangs perfectly centered in the room.");

        data.put("room2", "Kitchen");
        data.put("room2Description", "Bundles of herbs hang from the ceiling.  A large stone oven is built into the north wall. The mixed aroma of burnt wood and fresh herbs linger.");

        data.put("room3", "Courtyard");
        data.put("room3Description", "The smell of fresh flowers fills the air and the sound of flowing water can be heard.  Four large oak trees sit in each corner surrounded by garden flowers. A hedge-lined path leads to the center of the courtyard where a large water fountain sits.");

        data.put("room4", "Ball room");
        data.put("room4Description", "Crystal chandeliers spiral down from the arched sky-blue ceiling illuminating the luxurious golden walls and a floor so polished it looks like an iced-over lake.");

        data.put("room5", "Great Hall hallway");
        data.put("room5Description", "A long and narrow hallway, with a floor of solid white marble.  Numerous antique paintings hang on the wall.");

        data.put("room6", "Guard room");
        data.put("room6Description", "A place where arms and military equipment are stored. You see a ghostly soldier sitting in a wooden chair.");

        data.put("room7", "Library");
        data.put("room7Description", "You see book shelves throughout the room, and at the center of it all, you see a ghostly librarian sitting on the floor with a sea of books scattered around him. He seems to be reading something.");

        data.put("room8", "Secret room");
        data.put("room8Description", "Description placeholder for secret room");

        data.put("room9", "Foyer");
        data.put("room9Description", "A large, vacant room dimly lit by a few torches lining the stone walls.  You feel an immediate drop in temperature as a freezing chill crawls up your back. You get the sense that this may be your last chance to turn back from what lies ahead...");

        data.put("room10", "Dungeon");
        data.put("room10Description", "A dark, damp room filled with multiple iron bared cells for holding prisoners. The foundation resembles more of a cavernous system than a stone wall. The smell of death is pungent and overwhelming.");

        data.put("room11", "Cellar");
        data.put("room11Description", "You see rows of Wine racks as far as the eye can see. A large layer of dust sits atop the exposed wine bottles. You hear a bottle smash in the distance. You are not alone in here...");

        data.put("room12", "Crypt");
        data.put("room12Description", "Description placeholder for the crypt.");

        ///game text///

        data.put("text1", "You made it, the castle looks old and abandoned, but is an immaculate piece of architecture. " +
                "There is an uneasy feeling in the air, a rush of wind picks up the leaves around you. Will you be the first to claim King Berengars treasure? Or will you join the cursed souls that linger within...");
        data.put("text2", "Welcome to Maledictus.  A game created by Lefties.");
        data.put("text3", "Select [1] to start game.");
        data.put("text4", "Select [2] to quit game.");

        //room direction maps///

        Map greatHall = new HashMap();
        greatHall.put("north", "Courtyard");
        greatHall.put("east", "Dining room");
        greatHall.put("west", "Great Hall hallway");
        data.put("roomDirection0", greatHall);

        Map diningRoom = new HashMap();
        diningRoom.put("north", "Kitchen");
        diningRoom.put("west", "Great Hall");
        data.put("roomDirection1", diningRoom);

        Map kitchen = new HashMap();
        kitchen.put("south", "Dining room");
        data.put("roomDirection2", kitchen);

        Map courtyard = new HashMap();
        courtyard.put("north", "Ball room");
        courtyard.put("south", "Great Hall");
        data.put("roomDirection3", courtyard);

        Map ballroom = new HashMap();
        ballroom.put("south", "Courtyard");
        data.put("roomDirection4", ballroom);

        Map greatHallHallway = new HashMap();
        greatHallHallway.put("north", "Guard room");
        greatHallHallway.put("east", "Great Hall");
        greatHallHallway.put("west", "Library");
        data.put("roomDirection5", greatHallHallway);

        Map guardRoom = new HashMap();
        guardRoom.put("south", "Great Hall hallway");
        data.put("roomDirection6", guardRoom);

        Map library = new HashMap();
        library.put("north", "Foyer");
        library.put("east", "Great Hall hallway");
        library.put("south", "Secret room");
        data.put("roomDirection7", library);

        Map secretRoom = new HashMap();
        secretRoom.put("north", "Library");
        data.put("roomDirection8", secretRoom);

        Map foyer = new HashMap();
        foyer.put("south", "Library");
        foyer.put("east", "Dungeon");
        data.put("roomDirection9", foyer);

        Map dungeon = new HashMap();
        dungeon.put("north", "Cellar");
        dungeon.put("east", "Crypt");
        dungeon.put("west", "Foyer");
        data.put("roomDirection10", dungeon);

        Map cellar = new HashMap();
        cellar.put("south", "Dungeon");
        data.put("roomDirection11", cellar);

        Map crypt = new HashMap();
        crypt.put("west", "Dungeon");
        data.put("roomDirection12", crypt);

        ////items/////
        data.put("item1", "Iron Sword");
        data.put("item1Description", "A sharp sword made of the finest iron");

        data.put("item2", "Healing Potion");
        data.put("item2Description", "A vial filled with red liquid");

        data.put("item3", "Iron Spear");
        data.put("item3Description", "A sharp pointy spear made of the finest iron");

        data.put("item4", "Brass Key");
        data.put("item4Description", "A key");



        PrintWriter pw = new PrintWriter("GameData.json");
        pw.write(data.toJSONString());

        pw.flush(); //clear the stream of any element that may be or may not be inside the stream
        pw.close(); //closes the stream
    }

    ///JSON read methods///

    public static Map returnRoomDirections(String roomNumber) throws IOException, org.json.simple.parser.ParseException {
        Object obj = new JSONParser().parse(new FileReader("GameData.json"));
        JSONObject jo = (JSONObject) obj;
        Map roomDirections = ((Map)jo.get("roomDirection" + roomNumber));

        return roomDirections;
    }

    public static String returnRoomName(String roomNumber) throws IOException, ParseException, org.json.simple.parser.ParseException {
        Object obj = new JSONParser().parse(new FileReader("GameData.json"));
        JSONObject jo = (JSONObject) obj;
        String room;
        room = (String) jo.get("room" + roomNumber);
        return room;
    }

    public static String returnRoomDescription(String roomNumber) throws IOException, org.json.simple.parser.ParseException {
        Object obj = new JSONParser().parse(new FileReader("GameData.json"));
        JSONObject jo = (JSONObject) obj;
        String roomDescription;
        roomDescription = (String) jo.get("room" + roomNumber + "Description");
        return roomDescription;
    }

    public static String returnGameText(String textNumber) throws IOException, org.json.simple.parser.ParseException {
        Object obj = new JSONParser().parse(new FileReader("GameData.json"));
        JSONObject jo = (JSONObject) obj;
        String gameText;
        gameText = (String) jo.get("text" + textNumber);
        return gameText;
    }

    public static Map returnNpcDialogue(String dialogueNumber) throws IOException, org.json.simple.parser.ParseException {
        Object obj = new JSONParser().parse(new FileReader("GameData.json"));
        JSONObject jo = (JSONObject) obj;
        Map npcDialogue = ((Map)jo.get(""));

        return npcDialogue;
    }

    public static String returnItemName(String itemNumber) throws IOException, ParseException, org.json.simple.parser.ParseException {
        Object obj = new JSONParser().parse(new FileReader("GameData.json"));
        JSONObject jo = (JSONObject) obj;
        String itemName;
        itemName = (String) jo.get("item" + itemNumber);
        return itemName;
    }

    public static String returnItemDescription(String itemNumber) throws IOException, org.json.simple.parser.ParseException {
        Object obj = new JSONParser().parse(new FileReader("GameData.json"));
        JSONObject jo = (JSONObject) obj;
        String itemDescription;
        itemDescription = (String) jo.get("item" + itemNumber + "Description");
        return itemDescription;
    }

    /////////create item/room methods/////////

    public static void createItems() throws IOException, ParseException, org.json.simple.parser.ParseException {
        Item ironSword = ItemFactory.createItem(returnItemName("1"), returnItemDescription("1"), ItemType.WEAPON, WeaponType.SLASHING);
        Item potion = ItemFactory.createItem(returnItemName("2"), returnItemDescription("2"), ItemType.POTION, PotionType.HEALING);
        Item spear = ItemFactory.createItem(returnItemName("3"), returnItemDescription("3"), ItemType.WEAPON, WeaponType.PIERCING);
        Item key = ItemFactory.createItem(returnItemName("4"), returnItemDescription("4"), ItemType.KEY, KeyType.DUNGEON);
        items.add(ironSword);
        items.add(potion);
        items2.add(spear);
        items2.add(key);
    }

    public static void createRoomList () throws IOException, ParseException, org.json.simple.parser.ParseException {


        RoomFactory.createRoom(returnRoomName("0"), returnRoomDescription("0"), returnRoomDirections("0"), items);

        RoomFactory.createRoom(returnRoomName("1"), returnRoomDescription("1"), returnRoomDirections("1"), items2);

        RoomFactory.createRoom(returnRoomName("2"), returnRoomDescription("2"), returnRoomDirections("2"));

        RoomFactory.createRoom(returnRoomName("3"), returnRoomDescription("3"), returnRoomDirections("3"));

        RoomFactory.createRoom(returnRoomName("4"), returnRoomDescription("4"), returnRoomDirections("4"));

        RoomFactory.createRoom(returnRoomName("5"), returnRoomDescription("5"), returnRoomDirections("5"));

        RoomFactory.createRoom(returnRoomName("6"), returnRoomDescription("6"), returnRoomDirections("6"));

        RoomFactory.createRoom(returnRoomName("7"), returnRoomDescription("7"), returnRoomDirections("7"));

        RoomFactory.createRoom(returnRoomName("8"), returnRoomDescription("8"), returnRoomDirections("8"));

        RoomFactory.createRoom(returnRoomName("9"), returnRoomDescription("9"), returnRoomDirections("9"));

        RoomFactory.createRoom(returnRoomName("10"), returnRoomDescription("10"), returnRoomDirections("10"));

        RoomFactory.createRoom(returnRoomName("11"), returnRoomDescription("11"), returnRoomDirections("11"));

        RoomFactory.createRoom(returnRoomName("12"), returnRoomDescription("12"), returnRoomDirections("12"));

    }

}