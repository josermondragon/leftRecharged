package com.maledictus;

import com.maledictus.item.*;
import com.maledictus.item.ItemFactory;
import com.maledictus.item.key.Key;
import com.maledictus.item.key.KeyType;
import com.maledictus.item.potion.PotionType;
import com.maledictus.item.weapon.*;
import com.maledictus.npc.Ghost;
import com.maledictus.npc.NPC;
import com.maledictus.npc.NPCFactory;
import com.maledictus.npc.Species;
import com.maledictus.npc.ally.Quest;
import com.maledictus.npc.enemy.EnemyType;
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
    public static ArrayList<Item> items3 = new ArrayList<>();
    public static Map<Integer, NPC> greatHallNpcMap = new HashMap<>();
    public static Map<Integer, NPC> dungeonNpcMap = new HashMap<>();

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

        Map<String, String> greatHall = new HashMap<>();
        greatHall.put("north", "Courtyard");
        greatHall.put("east", "Dining room");
        greatHall.put("west", "Great Hall hallway");
        data.put("roomDirection0", greatHall);

        Map<String, String> diningRoom = new HashMap<>();
        diningRoom.put("north", "Kitchen");
        diningRoom.put("west", "Great Hall");
        data.put("roomDirection1", diningRoom);

        Map<String, String> kitchen = new HashMap<>();
        kitchen.put("south", "Dining room");
        data.put("roomDirection2", kitchen);

        Map<String, String> courtyard = new HashMap<>();
        courtyard.put("north", "Ball room");
        courtyard.put("south", "Great Hall");
        data.put("roomDirection3", courtyard);

        Map<String, String> ballroom = new HashMap<>();
        ballroom.put("south", "Courtyard");
        data.put("roomDirection4", ballroom);

        Map<String, String> greatHallHallway = new HashMap<>();
        greatHallHallway.put("north", "Guard room");
        greatHallHallway.put("west", "Library");
        greatHallHallway.put("east", "Great Hall");
        data.put("roomDirection5", greatHallHallway);

        Map<String, String> guardRoom = new HashMap<>();
        guardRoom.put("south", "Great Hall hallway");
        data.put("roomDirection6", guardRoom);

        Map<String, String> library = new HashMap<>();
        library.put("north", "Foyer");
        library.put("east", "Great Hall hallway");
        library.put("south", "Secret room");
        data.put("roomDirection7", library);

        Map<String, String> secretRoom = new HashMap<>();
        secretRoom.put("north", "Library");
        data.put("roomDirection8", secretRoom);

        Map<String, String> foyer = new HashMap<>();
        foyer.put("south", "Library");
        foyer.put("east", "Dungeon");
        data.put("roomDirection9", foyer);

        Map<String, String> dungeon = new HashMap<>();
        dungeon.put("north", "Cellar");
        dungeon.put("east", "Crypt");
        dungeon.put("west", "Foyer");
        data.put("roomDirection10", dungeon);

        Map<String, String> cellar = new HashMap<>();
        cellar.put("south", "Dungeon");
        data.put("roomDirection11", cellar);

        Map<String, String> crypt = new HashMap<>();
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

        ///npc dialogue///

//        ghost man/woman -- kitchen//

        data.put("npc1", "Ghost man chef");
        Map<String, String> dialogue1 = new HashMap<>();
        dialogue1.put("dialogue1", "I knew I had it on in the kitchen before I prepared the stone oven to heat up a civet.");
        dialogue1.put("dialogue2", "Oh my...you've found my wedding ring! Gramercy! You don't know how much trouble and heartache you've put me out of stranger.  I'd like to give you a reward for your kindness. Hale be thou!");
        data.put("npc1dialogue", dialogue1);


        data.put("npc2", "Ghost woman chef");
        Map<String, String> dialogue2 = new HashMap<>();
        dialogue2.put("dialogue1", "I can't believe you lost it!  After all these years...hundreds of years! I told you to never take it off.  Where did you last see it?");
        data.put("npc2dialogue", dialogue2);

        //ballroom ghost -- ballroom//
        data.put("npc3", "Ballroom ghost");
        Map<String, String> dialogue3 = new HashMap<>();
        dialogue3.put("dialogue1", "This room has hosted some of the grandest parties the kingdom has ever known.  Indeed, years and years of delightful memories I have had here.  Now its just a vacant, lifeless room.  Kind of sounds like my soul! hah! ehhem..... Folks don't come around here anymore, ever since the old king lost his wits.  Shame, I tell ya! By my troth, the king used to be a honorable and respected man!");
        dialogue3.put("dialogue2", "Ah yes, an old key you say? hah, I do remember coming across this old thing.  It was sitting right in the middle of this ballroom floor, as if it were placed! stuck out like a sore thumb.  Had it in my pocket ever since.  Here, you can take it off my hands! Go thy way! ");
        data.put("npc3dialogue", dialogue3);

        //Soldier -- guard room//
        data.put("npc4", "Soldier");
        Map<String, String> dialogue4 = new HashMap<>();
        dialogue4.put("dialogue1", "This castle used to be a beacon of hope, now it's filled with dread. But that was years ago, it's hard to remember what life was like before all of this...madness.");
        dialogue4.put("dialogue2", "King Berengar was such a great King, he was always so involved in our lives, a genuine and kind man. He would listen to his subjects no matter their status, he really cared about all citizens under his rule. But not anymore.. now he's locked away in his keep, and will not let anyone inside. It's like this castle took on a mind of it's own.. the dead began to come alive, and the living unable to die. Those who were able to leave in time... they suffered the worst fate of all. As soon you step foot outside the castle walls your mind is completely lost while your body slowly begins to rot, now they roam the world aimlessly feasting on other human's flesh, never to be satisfied.");
        dialogue4.put("dialogue3", "Hey what do you think you're doing? You want a weapon? You could've just asked. But before you do, how about we test out your sword skills with a friendly duel?");
        dialogue4.put("dialogue4", "Certain weapons are more efficient depending on the enemy. A true Warrior is willing to explore all of the options they have at their disposal. Best of luck, this is truly a dangerous place you've come to..");
        data.put("npc4dialogue", dialogue4);

        //Librarian -- library//
        data.put("npc5", "Librarian");
        Map<String, String> dialogue5 = new HashMap<>();
        dialogue5.put("dialogue1", "Look, I'm very busy, I have read every book at least 10,038... *closes book* okay make that 10,039 times, and I am still not any closer to figuring out a cure for this treacherous curse.");
        dialogue5.put("dialogue2", "If you want to go down to the dungeon you will need a key. But I don't have it! I hid it away! Isn't it already bad enough I have to be near the entrance? Why would I want to let whatever is down there up here? It's locked for a reason.. death, death and more death... no thank you. I mean it's almost as bad as the ballroom, too many people and not enough books, that is the LAST place I would want to be right now. The only benefit of the ballroom is that if you hide something there they wouldn't think to bring it here. Ballroom people and libraries just don't mix.");
        dialogue5.put("dialogue3", "The curse? Yeah I'm not sure if you noticed but a human is not supposed to be translucent. It's been this way ever since King Berengar came back from his last expedition 200 years ago. The king seems to have gotten the worst of it, he hasn't been the same ever since...");
        data.put("npc5dialogue", dialogue5);

        //Prisoner -- dungeon//
        data.put("npc6", "Prisoner");
        Map<String, String> dialogue6 = new HashMap<>();
        dialogue6.put("dialogue1", "You there! I don't believe it...a human visitor! Please, please! come hither.  I've been locked away in this cage for centuries! The king himself damned thee here.  Accused me of being a glos pautonnier.  Afraid i'd steal his riches! Sentenced me to rot away here for all eternity. Madness I tell you! Worst of all, the cellar sits across thee! Tempting me for a taste of wine.  Enough to drive a man mad! Could you do me a favor, and retrieve some wine for thee from the Cellar?");
        dialogue6.put("dialogue2", "Ahhh, blessed be! I've longed for the taste of wine so fine.  Just imagine, I watched it age for 200 years! No one deserves a taste more than thee. Damned it be! I've forgotten, i'm a hollow ghost who can't consume anything! Why am I cursed with such fate! By god's bone, I am innocent!");


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

    ////game text method////

    public static String returnGameText(String textNumber) throws IOException, org.json.simple.parser.ParseException {
        Object obj = new JSONParser().parse(new FileReader("GameData.json"));
        JSONObject jo = (JSONObject) obj;
        String gameText;
        gameText = (String) jo.get("text" + textNumber);
        return gameText;
    }

    ////item methods////

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

    ////npc methods////
    public static String returnNpcName(String npcNumber) throws IOException, ParseException, org.json.simple.parser.ParseException {
        Object obj = new JSONParser().parse(new FileReader("GameData.json"));
        JSONObject jo = (JSONObject) obj;
        String npcName;
        npcName = (String) jo.get("item" + npcNumber);
        return npcName;
    }

    public static Map returnNpcDialogue(String dialogueNumber) throws IOException, org.json.simple.parser.ParseException {
        Object obj = new JSONParser().parse(new FileReader("GameData.json"));
        JSONObject jo = (JSONObject) obj;
        Map npcDialogue = ((Map)jo.get("npc" + dialogueNumber +"dialogue" ));

        return npcDialogue;
    }

    public static void createNPCs() throws IOException, org.json.simple.parser.ParseException, ParseException {
        Map<Integer, String> librarianDialogue = new HashMap<>();
        librarianDialogue.put(1, "Look, I'm very busy, I have read every book at least 10,038... *closes book* okay make that 10,039 times, and I am still not any closer to figuring out a cure for this treacherous curse.");
        librarianDialogue.put(2, "If you want to go down to the dungeon you will need a key. But I don't have it! I hid it away! Isn't it already bad enough I have to be near the entrance? Why would I want to let whatever is down there up here? It's locked for a reason.. death, death and more death... no thank you. I mean it's almost as bad as the ballroom, too many people and not enough books, that is the LAST place I would want to be right now. The only benefit of the ballroom is that if you hide something there they wouldn't think to bring it here. Ballroom people and libraries just don't mix.");
        librarianDialogue.put(3, "The curse? Yeah I'm not sure if you noticed but a human is not supposed to be translucent. It's been this way ever since King Berengar came back from his last expedition 200 years ago. The king seems to have gotten the worst of it, he hasn't been the same ever since...");

        Map<Integer, String> prisonerDialog = new HashMap<>();
        Map<Integer, String> questPrisonerDialog = new HashMap<>();
        prisonerDialog.put(1, "You there! I don't believe it...a human visitor! Please, please! come hither.  I've been locked away in this cage for centuries! The king himself damned thee here.  Accused me of being a glos pautonnier.  Afraid i'd steal his riches! Sentenced me to rot away here for all eternity. Madness I tell you! Worst of all, the cellar sits across thee! Tempting me for a taste of wine.  Enough to drive a man mad! Could you do me a favor, and retrieve some wine for thee from the Cellar?");
        questPrisonerDialog.put(1, "Hey, It's you again! Please tell me you found my precious wine!");
        questPrisonerDialog.put(2, "Ahhh, blessed be! I've longed for the taste of wine so fine.  Just imagine, I watched it age for 200 years! No one deserves a taste more than thee. Damned it be! I've forgotten, i'm a hollow ghost who can't consume anything! Why am I cursed with such fate! By god's bone, I am innocent!");
        questPrisonerDialog.put(3, "Well hurry back when you have it!");
        questPrisonerDialog.put(4, "Please! Just leave me be.");

        Item ironWarHammer = ItemFactory.createItem("Iron War Hammer", "BIG OL HAMMER", ItemType.WEAPON, WeaponType.BLUNT);

        Quest quest = new Quest("Search for Wine", "Retrieve wine from the cellar and give to the prisoner.", ironWarHammer, questPrisonerDialog, "Wine", false, false);

        NPC librarian = NPCFactory.createNPC(1,100, "ghostly librarian", "A semi translucent librarian, it seems to be focused on something.", false, Species.GHOST, librarianDialogue, null);
        NPC prisoner = NPCFactory.createNPC(2,100, "ghostly prisoner", "A semi translucent prisoner, it seems to be focused on something.", false, Species.GHOST, prisonerDialog, quest);
        NPC skeleton = NPCFactory.createNPC(3, 100, "Skeleton guard", "A bone guy.", true, Species.SKELETON, EnemyType.STANDARD);

        greatHallNpcMap.put(librarian.getId(), librarian);
        greatHallNpcMap.put(skeleton.getId(), skeleton);

        dungeonNpcMap.put(prisoner.getId(), prisoner);

    }

    ////////create item/room methods/////////
    public static void createItems() throws IOException, ParseException, org.json.simple.parser.ParseException {
        Item ironSword = ItemFactory.createItem(returnItemName("1"), returnItemDescription("1"), ItemType.WEAPON, WeaponType.SLASHING);
        Item potion = ItemFactory.createItem(returnItemName("2"), returnItemDescription("2"), ItemType.POTION, PotionType.HEALING);
        Item spear = ItemFactory.createItem(returnItemName("3"), returnItemDescription("3"), ItemType.WEAPON, WeaponType.PIERCING);
        Item key = ItemFactory.createItem(returnItemName("4"), returnItemDescription("4"), ItemType.KEY, KeyType.DUNGEON);
        Item wine = ItemFactory.createItem("Wine", "bottle of the finest red.", ItemType.POTION, PotionType.HEALING);
        items.add(ironSword);
        items.add(potion);
        items2.add(spear);
        items2.add(key);
        items3.add(wine);
    }


    public static void createRoomList () throws IOException, ParseException, org.json.simple.parser.ParseException {
        RoomFactory.createRoom(returnRoomName("0"), returnRoomDescription("0"), returnRoomDirections("0"), false, KeyType.DUNGEON, items, greatHallNpcMap);

        RoomFactory.createRoom(returnRoomName("1"), returnRoomDescription("1"), returnRoomDirections("1"), false, KeyType.DUNGEON, items2);

        RoomFactory.createRoom(returnRoomName("2"), returnRoomDescription("2"), returnRoomDirections("2"), false, KeyType.DUNGEON);

        RoomFactory.createRoom(returnRoomName("3"), returnRoomDescription("3"), returnRoomDirections("3"), true, KeyType.DUNGEON, items3, dungeonNpcMap);

        RoomFactory.createRoom(returnRoomName("4"), returnRoomDescription("4"), returnRoomDirections("4"), false, KeyType.DUNGEON);

        RoomFactory.createRoom(returnRoomName("5"), returnRoomDescription("5"), returnRoomDirections("5"), false, KeyType.DUNGEON);

        RoomFactory.createRoom(returnRoomName("6"), returnRoomDescription("6"), returnRoomDirections("6"), false, KeyType.DUNGEON);

        RoomFactory.createRoom(returnRoomName("7"), returnRoomDescription("7"), returnRoomDirections("7"), false, KeyType.DUNGEON);

        RoomFactory.createRoom(returnRoomName("8"), returnRoomDescription("8"), returnRoomDirections("8"), false, KeyType.DUNGEON);

        RoomFactory.createRoom(returnRoomName("9"), returnRoomDescription("9"), returnRoomDirections("9"), false, KeyType.DUNGEON);

        RoomFactory.createRoom(returnRoomName("10"), returnRoomDescription("10"), returnRoomDirections("10"), false, KeyType.DUNGEON);

        RoomFactory.createRoom(returnRoomName("11"), returnRoomDescription("11"), returnRoomDirections("11"), false, KeyType.DUNGEON);

        RoomFactory.createRoom(returnRoomName("12"), returnRoomDescription("12"), returnRoomDirections("12"), false, KeyType.DUNGEON);

    }

}