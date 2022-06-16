package com.maledictus.item.potion;

import com.maledictus.item.*;
import com.maledictus.player.Player;


public class Potion extends Item {

    private PotionType potionType;

    public Potion (String name, String description, ItemType itemType, PotionType potionType) {
        super(name, description, itemType);
        this.potionType = potionType;
    }

    public void use(Player player, Potion potion) {
        if(potionType == PotionType.HEALING) {
            player.heal(10);
        }
        player.removeItem(potion);
        System.out.println("You used your Potion!");
    }
}