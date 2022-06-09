package com.maledictus;

import java.util.HashMap;
import java.util.Map;

public class Player {

    // FIELDS \\
    String playerName;
    int hitPoints = 100;
    Map<String, Item> inventory = new HashMap<>();

    // CONSTRUCTORS \\
    public Player (String playerName) {
    setPlayerName(playerName);
    }

    // BUSINESS METHODS \\
    public void addItem(Item item) {
        inventory.put(item.name, item);
    }

    public void inspectItem(String item) {
        // Check to see if item exists in inventory
        if (inventory.containsKey(item)) {
            System.out.println("Item Object: " + getInventory().get(item));
        } else {
            System.out.println("You do not have this item in your inventory: " + item);
        }
    }

    public void attack(Item item) {
        if (item.name.equals("sword")) {
            Item.WeaponType attackStyle = item.weaponType; // Break here
        }
    }

    //GETTERS & SETTERS \\
    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public Map<String, Item> getInventory() {
        return inventory;
    }

    @Override
    public String toString() {
        return "{" +
                "Name = " + getPlayerName() + '\'' +
                ", HitPoints = " + getHitPoints() +
                ", Inventory " + getInventory() +
                '}';
    }

    private class Monster {
        String name;
        String description;
        Weaknesses weaknesses;
    }

    public enum Weaknesses {
        SLASH,
        PIERCE,
        CRUSHING
    }

}