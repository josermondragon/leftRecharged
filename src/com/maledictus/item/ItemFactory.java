package com.maledictus.item;

import com.maledictus.item.key.*;
import com.maledictus.item.potion.*;
import com.maledictus.item.weapon.*;
import java.util.Map;
import java.util.TreeMap;

public class ItemFactory {

    private static final Map<Integer, Item> itemMap = new TreeMap<>();

    ItemFactory () {

    }

    public static Weapon createItem(String name, String description, ItemType itemType, WeaponType weaponType) {
        Weapon item = null;
        if(weaponType != null) {
            item = new Weapon(name, description, itemType, weaponType);
        }
        return item;
    }

    public static Key createItem(String name, String description, ItemType itemType, KeyType keyType) {
        Key item = null;
        if(keyType != null) {
            item = new Key(name, description, itemType, keyType);
        }
        return item;
    }

    public static Potion createItem(String name, String description, ItemType itemType, PotionType potionType) {
        Potion item = null;
        if(potionType != null) {
            item = new Potion(name, description, itemType, potionType);
        }
        return item;
    }
}