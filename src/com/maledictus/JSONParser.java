package com.maledictus;

import com.maledictus.item.Item;
import com.maledictus.item.ItemFactory;
import com.maledictus.room.RoomFactory;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class JSONParser {

    public static ArrayList<Item> items = new ArrayList<>();
    public static ArrayList<Item> items2 = new ArrayList<>();

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

    public static void createRoomList () {
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

        RoomFactory.createRoom("Great Hall", "The main area of the castle.", greatHall, items);

        RoomFactory.createRoom("Dining room", "A long, rectangular dining table set with the finest silver cutlery and porcelain tableware fills the room.  A fireplace is built into the east wall behind \n" +
                "the head of the table.  A large, crystal chandelier hangs perfectly centered in the room.", diningRoom, items2);

        RoomFactory.createRoom("Kitchen", "Bundles of herbs hang from the ceiling.  A large stone oven is built into the north wall. The mixed aroma of burnt wood and fresh \n" +
                "herbs linger.", kitchen);

        RoomFactory.createRoom("Courtyard", "The smell of fresh flowers fills the air and the sound of flowing water can be heard.  Four large oak trees sit in each corner surrounded by garden flowers.  \n" +
                "A hedge-lined path leads to the center of the courtyard where a large water fountain sits.", courtyard);

        RoomFactory.createRoom("Ball room", "Crystal chandeliers spiral down from the arched sky-blue ceiling illuminating the luxurious golden walls and a floor\n" +
                "so polished it looks like an iced-over lake.", ballroom);

        RoomFactory.createRoom("Great Hall hallway", "A long and narrow hallway, with a floor of solid white marble.  Numerous antique paintings hang on the wall.", greatHallHallway);

        RoomFactory.createRoom("Guard room", "A place where arms and military equipment are stored. You see a ghostly soldier sitting in a wooden chair.", guardRoom);

        RoomFactory.createRoom("Library", "You see book shelves throughout the room, and at the center of it all, you see a ghostly librarian sitting on the floor with a sea of books scattered around him. He seems to be reading something.", library);

        RoomFactory.createRoom("Secret room", "Description for the secret room", secretRoom);

        RoomFactory.createRoom("Foyer", "A large, vacant room dimly lit by a few torches lining the stone walls.  You feel an immediate drop in temperature as a freezing chill crawls up your back. You get the sense that this may be your last chance to turn back from what lies ahead...", foyer);

        RoomFactory.createRoom("Dungeon", "A dark, damp room filled with multiple iron bared cells for holding prisoners. The foundation resembles more of a cavernous system than a stone wall. The smell of death is pungent and overwhelming.", dungeon);

        RoomFactory.createRoom("Cellar", "You see rows of Wineracks as far as the eye can see. A large layer of dust sits atop the exposed wine bottles. You hear a bottle smash in the distance. You are not alone in here...", cellar);

        RoomFactory.createRoom("Crypt", "This is the description placeholder for crypt.", crypt);

    }

}