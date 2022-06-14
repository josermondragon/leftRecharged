package com.maledictus.item;

import com.maledictus.item.key.KeyType;
import com.maledictus.item.potion.PotionType;
import com.maledictus.item.weapon.WeaponType;

import javax.swing.*;

public class Item {

    private final String name;
    private final String description;
    private final ItemType itemType;




    public Item (String name, String description, ItemType itemType) {
        this.name = name;
        this.description = description;
        this.itemType = itemType;
    }

//    public Item (String name, String description, ItemType itemType, WeaponType weaponType) {
//        this(name, description, itemType);
//    }
//
//    public Item (String name, String description, ItemType itemType, PotionType potionType) {
//        this(name, description, itemType);
//    }

//    public Item (String name, String description, ItemType itemType, KeyType keyType) {
//        this(name, description, itemType);
//        this.keyType = keyType;
//    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public ItemType getItemType() {
        return itemType;
    }
//    public PotionType getPotionType() {
//        return potionType;
//    }
//
//    public void setPotionType(PotionType potionType) {
//        this.potionType = potionType;
//    }

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