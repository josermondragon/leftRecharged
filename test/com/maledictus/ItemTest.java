package com.maledictus;

import com.maledictus.item.Item;
import com.maledictus.item.ItemFactory;
import org.junit.Test;

public class ItemTest {

    @Test
    public void testItem_shouldCreateItem_whenConstructorIsPassedInProperArguments() {
        Item sword = new Item("Iron Sword", "A sharp sword made of the finest iron", Item.ItemType.WEAPON, Item.WeaponType.SLASH);
        Item sword2 = new Item("Iron Sword", "A sharp sword made of the finest iron", Item.ItemType.WEAPON, Item.WeaponType.PIERCE);
        System.out.println(sword);
        System.out.println(sword2);
    }

    @Test
    public void testItemFactoryCreateItem_shouldReturnANewItem_whenCreateItemMethodIsCalled() {
        Item item = ItemFactory.createItem("Iron Sword", "A sharp sword made of the finest iron", Item.ItemType.WEAPON, Item.WeaponType.SLASH);
        Item item2 = ItemFactory.createItem("Brass Key", "A key made of brass", Item.ItemType.KEY, Item.KeyType.DUNGEON);
        System.out.println(item);
        System.out.println(item2);
    }
}
