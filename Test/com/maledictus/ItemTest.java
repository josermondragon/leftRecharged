package com.maledictus;

import org.junit.Test;
import static org.junit.Assert.*;

public class ItemTest {

    @Test
    public void testItem_shouldCreateItem_whenConstructorIsPassedInProperArguments() {
        Item sword = new Item("Iron Sword", "A sharp sword made of the finest iron", Item.ItemType.WEAPON, Item.WeaponType.SLASH);
        Item sword2 = new Item("Iron Sword", "A sharp sword made of the finest iron", Item.ItemType.WEAPON, Item.WeaponType.PIERCE);
        System.out.println(sword);
        System.out.println(sword2);
    }
}
