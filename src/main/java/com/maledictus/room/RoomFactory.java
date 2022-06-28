package com.maledictus.room;

import com.maledictus.item.Item;
import com.maledictus.item.key.KeyType;
import com.maledictus.npc.NPC;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RoomFactory {

    private static final Map<String, Room> roomMap = new HashMap<>();

    private RoomFactory() {

    }

    public static void createRoom(String name, String description, Map<String, String> directions, boolean isLocked, KeyType requiredKeyType) {
        Room room = new Room(name, description, directions, isLocked, requiredKeyType);
        roomMap.put(room.getName(), room);
    }

    public static void createRoom(String name, String description, Map<String, String> directions, boolean isLocked, KeyType requiredKeyType, Map<Integer, NPC> npcMap) {
        Room room = new Room(name, description, directions, isLocked, requiredKeyType, npcMap);
        roomMap.put(room.getName(), room);
    }

    public static void createRoom(String name, String description, Map<String, String> directions, boolean isLocked, KeyType requiredKeyType, ArrayList<Item> items) {
        Room room = new Room(name, description, directions, isLocked, requiredKeyType, items);
        roomMap.put(room.getName(), room);
    }

    public static void createRoom(String name, String description, Map<String, String> directions, boolean isLocked, KeyType requiredKeyType, ArrayList<Item> items, Map<Integer, NPC> npcMap) {
        Room room = new Room(name, description, directions, isLocked, requiredKeyType, items, npcMap);
        roomMap.put(room.getName(), room);
    }

    public static Map<String, Room> getRoomMap() {
        return roomMap;
    }

    public static void clearRoomMap() {
       roomMap.clear();
    }

}