package com.maledictus;

import com.maledictus.item.Item;
import com.maledictus.item.ItemFactory;
import com.maledictus.room.RoomFactory;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Json {

    public static ArrayList<Item> items = new ArrayList<>();
    public static ArrayList<Item> items2 = new ArrayList<>();

    public static void jsonWrite() throws FileNotFoundException {
        JSONObject rooms = new JSONObject();
        rooms.put("room0", "Great Hall");
        rooms.put("room0Description", "The main area of the castle.");

        rooms.put("room1", "Dining room");
        rooms.put("room1Description", "A long, rectangular dining table set with the finest silver cutlery and porcelain tableware fills the room.  A fireplace is built into the east wall behind the head of the table.  A large, crystal chandelier hangs perfectly centered in the room.");

        rooms.put("room2", "Kitchen");
        rooms.put("room2Description", "Bundles of herbs hang from the ceiling.  A large stone oven is built into the north wall. The mixed aroma of burnt wood and fresh herbs linger.");

        rooms.put("room3", "Courtyard");
        rooms.put("room3Description", "The smell of fresh flowers fills the air and the sound of flowing water can be heard.  Four large oak trees sit in each corner surrounded by garden flowers. A hedge-lined path leads to the center of the courtyard where a large water fountain sits.");

        rooms.put("room4", "Ball room");
        rooms.put("room4Description", "Crystal chandeliers spiral down from the arched sky-blue ceiling illuminating the luxurious golden walls and a floor so polished it looks like an iced-over lake.");

        rooms.put("room5", "Great Hall hallway");
        rooms.put("room5Description", "A long and narrow hallway, with a floor of solid white marble.  Numerous antique paintings hang on the wall.");

        rooms.put("room6", "Guard room");
        rooms.put("room6Description", "A place where arms and military equipment are stored. You see a ghostly soldier sitting in a wooden chair.");

        rooms.put("room7", "Library");
        rooms.put("room7Description", "You see book shelves throughout the room, and at the center of it all, you see a ghostly librarian sitting on the floor with a sea of books scattered around him. He seems to be reading something.");

        rooms.put("room8", "Secret room");
        rooms.put("room8Description", "Description placeholder for secret room");

        rooms.put("room9", "Foyer");
        rooms.put("room9Description", "A large, vacant room dimly lit by a few torches lining the stone walls.  You feel an immediate drop in temperature as a freezing chill crawls up your back. You get the sense that this may be your last chance to turn back from what lies ahead...");

        rooms.put("room10", "Dungeon");
        rooms.put("room10Description", "A dark, damp room filled with multiple iron bared cells for holding prisoners. The foundation resembles more of a cavernous system than a stone wall. The smell of death is pungent and overwhelming.");

        rooms.put("room11", "Cellar");
        rooms.put("room11Description", "You see rows of Wine racks as far as the eye can see. A large layer of dust sits atop the exposed wine bottles. You hear a bottle smash in the distance. You are not alone in here...");

        rooms.put("room12", "Crypt");
        rooms.put("room12Description", "Description placeholder for the crypt.");

        PrintWriter pw = new PrintWriter("GameData.json");
        pw.write(rooms.toJSONString());

        pw.flush(); //clear the stream of any element that may be or may not be inside the stream
        pw.close(); //closes the stream
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

    public static void createItems() {
        Item ironSword = ItemFactory.createItem("Iron Sword", "A sharp sword made of the finest iron", Item.ItemType.WEAPON, Item.WeaponType.SLASH);
        Item potion = ItemFactory.createItem("Healing Potion", "A vial filled with red liquid", Item.ItemType.POTION);
        Item spear = ItemFactory.createItem("Iron Spear", "A sharp pointy spear made of the finest iron", Item.ItemType.WEAPON, Item.WeaponType.PIERCE);
        Item key = ItemFactory.createItem("Brass Key", "A key", Item.ItemType.KEY, Item.KeyType.DUNGEON);
        items.add(ironSword);
        items.add(potion);
        items2.add(spear);
        items2.add(key);
    }

    public static void createRoomList () throws IOException, ParseException, org.json.simple.parser.ParseException {

        Map<String, String> greatHall = new HashMap<>();
        greatHall.put("north", "Courtyard");
        greatHall.put("east", "Dining room");
        greatHall.put("west", "Great Hall hallway");

        Map<String, String> diningRoom = new HashMap<>();
        diningRoom.put("north", "Kitchen");
        diningRoom.put("west", "Great Hall");

        Map<String, String> kitchen = new HashMap<>();
        kitchen.put("south", "Dining room");

        Map<String, String> courtyard = new HashMap<>();
        courtyard.put("north", "Ball room");
        courtyard.put("south", "Great Hall");

        Map<String, String> ballroom = new HashMap<>();
        ballroom.put("south", "Courtyard");

        Map<String, String> greatHallHallway = new HashMap<>();
        greatHallHallway.put("north", "Guard room");
        greatHallHallway.put("east", "Great Hall");
        greatHallHallway.put("west", "Library");

        Map<String, String> guardRoom = new HashMap<>();
        guardRoom.put("south", "Great Hall hallway");

        Map<String, String> library = new HashMap<>();
        library.put("north", "Foyer");
        library.put("east", "Great Hall hallway");
        library.put("south", "Secret room");

        Map<String, String> secretRoom = new HashMap<>();
        secretRoom.put("north", "Library");

        Map<String, String> foyer = new HashMap<>();
        foyer.put("south", "Library");
        foyer.put("east", "Dungeon");

        Map<String, String> dungeon = new HashMap<>();
        dungeon.put("north", "Cellar");
        dungeon.put("east", "Crypt");
        dungeon.put("west", "Foyer");

        Map<String, String> cellar = new HashMap<>();
        cellar.put("south", "Dungeon");

        Map<String, String> crypt = new HashMap<>();
        crypt.put("west", "Dungeon");

        RoomFactory.createRoom(returnRoomName("0"), returnRoomDescription("0"), greatHall, items);

        RoomFactory.createRoom(returnRoomName("1"), returnRoomDescription("1"), diningRoom, items2);

        RoomFactory.createRoom(returnRoomName("2"), returnRoomDescription("2"), kitchen);

        RoomFactory.createRoom(returnRoomName("3"), returnRoomDescription("3"), courtyard);

        RoomFactory.createRoom(returnRoomName("4"), returnRoomDescription("4"), ballroom);

        RoomFactory.createRoom(returnRoomName("5"), returnRoomDescription("5"), greatHallHallway);

        RoomFactory.createRoom(returnRoomName("6"), returnRoomDescription("6"), guardRoom);

        RoomFactory.createRoom(returnRoomName("7"), returnRoomDescription("7"), library);

        RoomFactory.createRoom(returnRoomName("8"), returnRoomDescription("8"), secretRoom);

        RoomFactory.createRoom(returnRoomName("9"), returnRoomDescription("9"), foyer);

        RoomFactory.createRoom(returnRoomName("10"), returnRoomDescription("10"), dungeon);

        RoomFactory.createRoom(returnRoomName("11"), returnRoomDescription("11"), cellar);

        RoomFactory.createRoom(returnRoomName("12"), returnRoomDescription("12"), crypt);

    }

}