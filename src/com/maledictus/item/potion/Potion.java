package com.maledictus.item.potion;

import com.maledictus.item.Item;
import com.maledictus.item.ItemType;

public class Potion extends Item {

    private PotionType potionType;

    public Potion (String name, String description, ItemType itemType, PotionType potionType) {
        super(name, description, itemType);
        this.potionType = potionType;
    }
    @Override
    public void use() {
        System.out.println("You used your Potion!");
    }
}