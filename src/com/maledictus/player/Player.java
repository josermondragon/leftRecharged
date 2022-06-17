package com.maledictus.player;

import com.maledictus.item.Item;
import java.util.HashMap;
import java.util.Map;

public class Player {

    private final String playerName;
    private final Map<String, Item> inventory = new HashMap<>();
    private int hitPoints = 100;

    public Player(String playerName) {
    this.playerName = playerName;
    }

    public void addItem(Item item) {
        inventory.put(item.getName(), item);
    }

    public void inspectItem(String item) {
        // Check to see if item exists in inventory, display the item if true
        if (inventory.containsKey(item)) {
            System.out.println("Item Object: " + getInventory().get(item));
        } else {
            System.out.println("You do not have this item in your inventory: " + item);
        }
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public void heal(int potValue) {
        if((hitPoints + potValue) > 100) {
            this.hitPoints = 100;
        } else {
            this.hitPoints += potValue;
        }
    }

    public Map<String, Item> getInventory() {
        return inventory;
    }



    public void removeItem(Item item) {
        inventory.remove(item.getName());
    }

    //    public void attack(Item item) {
    //        if (item.name.equals("sword")) {
    //            Item.WeaponType attackStyle = item.weaponType;
    //        }
    //    }

    @Override
    public String toString() {
        return "{" +
                "Name = " + getPlayerName() + '\'' +
                ", HitPoints = " + getHitPoints() +
                ", Inventory " + getInventory() +
                '}';
    }
}