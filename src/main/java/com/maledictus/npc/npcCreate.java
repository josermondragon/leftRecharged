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
        bannyDialogue.put(2, "....ahhhhhahahaahhh......");
        bannyDialogue.put(3, "....aha......");
        bannyDialogue.put(4, "....i've been hearing you traipsing around my kingdom......");
        bannyDialogue.put(5, "....looting my kingdom. Killing my subjects......");
        bannyDialogue.put(6, "I think you need a real battle");
        Item healPotion = ItemFactory.createItem("health potion", "a small health potion", ItemType.POTION, PotionType.HEALING);

        Banshee banny = new Banshee(8, 50, "mareger"," an AMAZING banshee", true, Species.BANSHEE, bannyDialogue, healPotion);




    }
}