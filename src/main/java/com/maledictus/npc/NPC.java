package com.maledictus.npc;

import com.maledictus.item.Item;
import com.maledictus.player.Player;

public class NPC {

    int id;
    int hitPoints;
    String name;
    String description;
    boolean isHostile;
    Species species;
    Item item;

    public NPC(int id, int hitPoints, String name, String description, boolean isHostile, Species species) {
        this.id = id;
        this.hitPoints = hitPoints;
        this.name = name;
        this.description = description;
        this.isHostile = isHostile;
        this.species = species;
    }

    public NPC(int id, int hitPoints, String name, String description, boolean isHostile, Species species, Item item){
        this(id, hitPoints, name, description, isHostile, species);
        this.item=item;

    }

    public int attack(Player player) {
        System.out.println("Player hit points is " + player.getHitPoints());
        player.setHitPoints(player.getHitPoints() - 8);
        System.out.println("Player hit points is " + player.getHitPoints());
        return player.getHitPoints();
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getId() {
        return id;
    }

    public boolean getIsHostile() {
        return isHostile;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    @Override
    public String toString() {
        return "NPC{" +
                "id=" + id +
                ", hitPoints=" + hitPoints +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", isHostile=" + isHostile +
                ", species=" + species +
                '}';
    }
}