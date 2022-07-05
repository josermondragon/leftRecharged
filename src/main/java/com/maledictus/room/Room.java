package com.maledictus.room;

import com.maledictus.item.Item;
import com.maledictus.item.key.KeyType;
import com.maledictus.npc.NPC;
import com.maledictus.npc.NPCFactory;

import java.util.ArrayList;
import java.util.Map;

public class Room {

    private final String name;
    private final String description;
    private final Map<String, String> directions;
    private ArrayList<Item> list;
    private boolean isLocked;
    private final KeyType requiredKeyType;
    private Map<Integer, NPC> npcMap;

    public Room(String name, String description, Map<String, String> directions, boolean isLocked, KeyType requiredKeyType){
        this.name = name;
        this.description = description;
        this.directions = directions;
        this.isLocked = isLocked;
        this.requiredKeyType = requiredKeyType;
        this.list = new ArrayList<>(); // Create empty item list if no items passed in
    }

    public Room(String name, String description, Map<String, String>  directions, boolean isLocked, KeyType requiredKeyType, ArrayList<Item> items){
        this(name, description, directions, isLocked, requiredKeyType);
        this.list = items;
    }

    public Room(String name, String description, Map<String, String>  directions, boolean isLocked, KeyType requiredKeyType, Map<Integer, NPC> npcMap){
        this(name, description, directions, isLocked, requiredKeyType);
        this.npcMap = npcMap;
    }

    public Room(String name, String description, Map<String, String>  directions, boolean isLocked, KeyType requiredKeyType, ArrayList<Item> items, Map<Integer, NPC> npcMap){
        this(name, description, directions, isLocked, requiredKeyType, items);
        this.npcMap = npcMap;
    }

    public boolean isLocked() {
        return isLocked;
    }

    public void unlockRoom(KeyType keyType) {
        if (keyType.equals(requiredKeyType)) {
            this.isLocked = false;
        }
    }

    public KeyType getRequiredKeyType() {
        return requiredKeyType;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Item> getItems () {
        return this.list;
    }

    public String getDescription() {
        return description;
    }

    public Map<String, String> getDirections() {
        return directions;
    }

    public Map<Integer, NPC> getNpcMap() {
        return this.npcMap;
    }

    @Override
    public String toString() {
        return "Room{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", directions=" + directions +
                ", list=" + list +
                ", isLocked=" + isLocked +
                ", requiredKeyType=" + requiredKeyType +
                ", npcMap=" + npcMap +
                '}';
    }

    public void addItem(Item item) {
        list.add(item);
    }
}
