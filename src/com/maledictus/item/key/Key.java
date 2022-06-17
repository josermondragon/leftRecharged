package com.maledictus.item.key;

import com.maledictus.item.Item;
import com.maledictus.item.ItemType;

public class Key extends Item {

    private KeyType keyType;

    public Key (String name, String description, ItemType itemType, KeyType keyType) {
        super(name, description, itemType);
        this.keyType = keyType;
    }

    public KeyType getKeyType() {
        return keyType;
    }

    public void use() {
        System.out.println("You used your Key!");
    }
}