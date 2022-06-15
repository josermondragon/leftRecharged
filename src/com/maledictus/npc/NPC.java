package com.maledictus.npc;

public class NPC {

    int id;
    int hitPoints;
    String name;
    String description;
    boolean isHostile;

    NPC(int id, String name, String description, boolean isHostile) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.isHostile = isHostile;
    }

    NPC(int id, int hitPoints, String name, String description, boolean isHostile) {
        this(id, name, description, isHostile);
        this.hitPoints = hitPoints;
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

    @Override
    public String toString() {
        return "NPC{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", isHostile=" + getIsHostile() +
                '}';
    }
}