package com.maledictus.npc;

import com.maledictus.item.Item;
import com.maledictus.item.ItemFactory;
import com.maledictus.item.ItemType;
import com.maledictus.item.potion.PotionType;

import java.util.HashMap;
import java.util.Map;
class npcCreate {






    public static void main(String[] args) {
        Map<Integer, String> bannyDialogue = new HashMap<>();
        bannyDialogue.put(1, "");
        bannyDialogue.put(2, "....AHHHHHHH......");
        bannyDialogue.put(3, "....ahhhhhhh......");
        bannyDialogue.put(4, "aiyeeeeeee");
        bannyDialogue.put(5, "....I have the king's key if you want to get tyher......");
        bannyDialogue.put(6, "....battle me for it......");

        Item healPotion = ItemFactory.createItem("health potion", "a small health potion", ItemType.POTION, PotionType.HEALING);

        Banshee banny = new Banshee(8, 50, "mareger"," an AMAZING banshee", true, Species.BANSHEE, bannyDialogue, healPotion);




    }
}