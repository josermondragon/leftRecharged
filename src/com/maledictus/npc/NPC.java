package com.maledictus.npc;

public class NPC {

    int id;
    int hitPoints;
    String name;
    String description;
    boolean isHostile;
    Species species;

    public NPC(int id, int hitPoints, String name, String description, boolean isHostile, Species species) {
        this.id = id;
        this.hitPoints = hitPoints;
        this.name = name;
        this.description = description;
        this.isHostile = isHostile;
        this.species = species;
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