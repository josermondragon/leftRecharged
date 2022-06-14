package com.maledictus.room;

import com.maledictus.item.Item;
import java.util.ArrayList;
import java.util.Map;

public class Room {

    private final String name;
    private final String description;
    private final Map<String, String> directions;
    private ArrayList<Item> list;

    public Room(String name, String description, Map<String, String> directions){
        this.name = name;
        this.description = description;
        this.directions = directions;
    }

    public Room(String name, String description, Map<String, String>  directions, ArrayList<Item> items){
        this(name, description, directions);
        this.list = items;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Item> getItems () {
        return this.list;
    }

    public Map<String, String> getDirections() {
        return directions;
    }

    @Override
    public String toString() {
        return "You have entered the " + name + ".\n" + "\n" + description;
    }
}
