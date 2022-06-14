package com.maledictus.item.key;

import com.maledictus.item.Item;
import com.maledictus.item.ItemType;

class Key extends Item {

    public Key (String name, String description, ItemType itemType, KeyType keyType) {
        super(name, description, itemType);
    }

    public void use() {
        System.out.println("You used your Key!");
    }

}