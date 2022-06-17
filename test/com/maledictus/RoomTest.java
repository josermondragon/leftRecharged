package com.maledictus;

import com.maledictus.item.Item;
import com.maledictus.item.ItemFactory;
import com.maledictus.item.ItemType;
import com.maledictus.item.key.KeyType;
import com.maledictus.item.weapon.WeaponType;
import com.maledictus.npc.*;
import com.maledictus.npc.enemy.EnemyType;
import com.maledictus.room.RoomFactory;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class RoomTest {

    private  Map<String, String> directions;
    private ArrayList<Item> list;
    private Map<Integer, NPC> npcMap;
    Map<Integer, String> dialog;

    @Before
    public void setup() {
        directions = new HashMap<>();
        directions.put("North", "Hallway");
        directions.put("South", "Bathroom");

        dialog = new HashMap<>();
        dialog.put(1, "I am a gosh dang ghost.");
        dialog.put(2, "Let's Duel!");

        npcMap = new HashMap<>();
        NPCFactory.createNPC(1,100, "ghostly librarian", "A semi translucent librarian, it seems to be focused on something.", false, Species.GHOST, dialog, null);
        NPCFactory.createNPC(2, 100, "Skeleton guard", "A bone guy.", true, Species.SKELETON, EnemyType.STANDARD);
        npcMap = NPCFactory.getNPCList();

        list = new ArrayList<>();
        Item item = ItemFactory.createItem("Iron Sword", "A sharp sword made of the finest iron", ItemType.WEAPON, WeaponType.SLASHING);
        Item item2 = ItemFactory.createItem("Brass Key", "A key made of brass", ItemType.KEY, KeyType.DUNGEON);
        list.add(item);
        list.add(item2);
    }

    @Test
    public void testRoomFactoryCreateRoom_shouldCreateListOfRooms_whenCreateRoomMethodIsCalled() {
        RoomFactory.createRoom("Ball room", "A room for balls.", directions, false, null);
        RoomFactory.createRoom("Dungeon", "A scary place.", directions, true, KeyType.DUNGEON);
        RoomFactory.createRoom("Cellar", "Wine and stuff.", directions, true, KeyType.DUNGEON, npcMap);
        Ghost ghost = (Ghost) RoomFactory.getRoomMap().get("Cellar").getNpcMap().get(1);
        ghost.talk(1);
        System.out.println(RoomFactory.getRoomMap().get("Cellar").getNpcMap().get(1));
    }
}