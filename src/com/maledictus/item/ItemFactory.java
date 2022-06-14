package com.maledictus.item;

import java.util.Map;
import java.util.TreeMap;

public class ItemFactory {

    private static final Map<Integer, Item> itemMap = new TreeMap<>();

    ItemFactory () {

    }

    public static Item createItem(String name, String description, Item.ItemType itemType) {
        Item item = null;
        item = new Item(name, description, itemType);
        return item;
    }

    public static Item createItem(String name, String description, Item.ItemType itemType, Item.WeaponType weaponType) {
        Item item = null;
        if(weaponType != null) {
            item = new Item(name, description, itemType, weaponType);
        }
        return item;
    }

    public static Item createItem(String name, String description, Item.ItemType itemType, Item.KeyType keyType) {
        Item item = null;
        if(keyType != null) {
            item = new Item(name, description, itemType, keyType);
        }
        return item;
    }
}