package com.maledictus.item;

import com.maledictus.item.key.KeyType;
import com.maledictus.item.potion.Potion;
import com.maledictus.item.potion.PotionType;
import com.maledictus.item.weapon.WeaponType;

import java.util.Map;
import java.util.TreeMap;

public class ItemFactory {

    private static final Map<Integer, Item> itemMap = new TreeMap<>();

    ItemFactory () {

    }

//    public static Item createItem(String name, String description, ItemType itemType) {
//        Item item = null;
//        item = new Item(name, description, itemType);
//        return item;
//    }

//    public static Item createItem(String name, String description, ItemType itemType, WeaponType weaponType) {
//        Item item = null;
//        if(weaponType != null) {
//            item = new Item(name, description, itemType, weaponType);
//        }
//        return item;
//    }

//    public static Item createItem(String name, String description, ItemType itemType, KeyType keyType) {
//        Item item = null;
//        if(keyType != null) {
//            item = new Item(name, description, itemType, keyType);
//        }
//        return item;
//    }

    public static Potion createItem(String name, String description, ItemType itemType, PotionType potionType) {
        Potion item = null;
        if(potionType != null) {
            item = new Potion(name, description, itemType, potionType);
        }
        return item;
    }
}