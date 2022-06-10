package com.maledictus;

import java.util.ArrayList;

public class Room {

    private final String name;
    private final String description;
    private int north, south, west, east, up, down;
    /* (RAM) START NEW CODE */
    private ArrayList<Item> list;
    /* (RAM) END NEW CODE */

    public Room(String name, String description, int north, int south, int west, int east){
        this.name = name;
        this.description = description;
        this.north = north;
        this.south = south;
        this.west = west;
        this.east = east;
    }

    /* (RAM) START NEW CODE */
    public Room(String name, String description, int north, int south, int west, int east, ArrayList<Item> items){
        this(name, description, north, south, west, east);
        this.list = items;
    }

    public ArrayList<Item> getItems () {
        return this.list;
    }
    /* (RAM) END NEW CODE */

//    Game direction = new Game();
    @Override
    public String toString() {
        return "You have entered the " + name + ".\n" + "\n" + description;
    }
}
