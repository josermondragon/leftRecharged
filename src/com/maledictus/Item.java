package com.maledictus;

public class Item {

        String name;
        String description;
        ItemType itemType;
        WeaponType weaponType;

        public Item (String name, String description, ItemType item) {
            this.name = name;
            this.description = description;
            this.itemType = item;
        }

        public Item (String name, String description, ItemType item, WeaponType weaponType) {
            this(name, description, item);
            this.weaponType = weaponType;
        }

        @Override
        public String toString() {
            return "{" +
                    "Name = " + name + '\'' +
                    ", Description = " + description +
                    ", ItemType = " + itemType +
                    ", WeaponType = " + weaponType +
                    '}';
        }


    public enum WeaponType {
        SLASH,
        PIERCE,
        CRUSHING,
    }

    public enum ItemType {
        POTION,
        KEY,
        WEAPON
    }

}