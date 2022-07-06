package com.maledictus.player;

import com.maledictus.Printer;
import com.maledictus.item.Item;
import com.maledictus.item.ItemType;
import com.maledictus.item.key.Key;
import com.maledictus.item.key.KeyType;
import com.maledictus.npc.NPC;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Player {

    private final String playerName;
    private final Map<String, Item> inventory = new HashMap<>();
    private int hitPoints = 100;
    private boolean equipped;


    public Player(String playerName) {
    this.playerName = playerName;
    }

    public void addItem(Item item) {
        inventory.put(item.getName(), item);
    }

    public Key getDoorKey(KeyType keyType) {
        for (Item item : this.getInventory().values()) {
            if (item.getItemType() == ItemType.KEY) {
                Key key = (Key) item;
                if (key.getKeyType() == keyType) {
                    return key;
                }
            }
        }
        return null;
    }

    public void equipWeapon() {
        this.equipped = true;
    }

    public int attack(NPC npc) {
        int damage = 30;
        if(isEquipped()) {
            Printer.print(Color.ORANGE,"NPC is hit for: " + damage + " from " + npc.getHitPoints());
            damage = 50;
            npc.setHitPoints(npc.getHitPoints() - damage);
            Printer.print(Color.RED, "NPC hit points is " + npc.getHitPoints());
        } else {
            Printer.print(Color.ORANGE, "NPC is hit for: " + damage + " from " + npc.getHitPoints());
            npc.setHitPoints(npc.getHitPoints() - damage);
            Printer.print(Color.RED, "NPC hit points is " + npc.getHitPoints());
        } return npc.getHitPoints();
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    public boolean isEquipped() {
        return equipped;
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

    @Override
    public String toString() {
        return "{" +
                "Name = " + getPlayerName() + '\'' +
                ", HitPoints = " + getHitPoints() +
                ", Inventory " + getInventory() +
                '}';
    }
}