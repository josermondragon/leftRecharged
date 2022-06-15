package com.maledictus.room;

import com.maledictus.item.Item;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RoomFactory {

    private static final Map<String, Room> roomMap = new HashMap<>();

    private RoomFactory() {

    }

    public static void createRoom(String name, String description, Map<String, String> directions) {
        Room room = new Room(name, description, directions);
        roomMap.put(room.getName(), room);
    }

    public static void createRoom(String name, String description, Map<String, String> directions, ArrayList<Item> items) {
        Room room = new Room(name, description, directions, items);
        roomMap.put(room.getName(), room);
    }

    public static Map<String, Room> getRoomMap() {
        return roomMap;
    }

    public static void clearRoomMap() {
       roomMap.clear();
    }
}