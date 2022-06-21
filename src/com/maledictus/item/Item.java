package com.maledictus.item;

public class Item {

    private final String name;
    private final String description;
    private final ItemType itemType;

    public Item (String name, String description, ItemType itemType) {
        this.name = name;
        this.description = description;
        this.itemType = itemType;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public void use() {

    }

    @Override
    public String toString() {
        return "{" +
                "Name = " + name + '\'' +
                ", Description = " + description +
                ", ItemType = " + itemType +
                '}';
    }
}