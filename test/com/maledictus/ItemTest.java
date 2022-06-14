package com.maledictus;

import com.maledictus.item.Item;
import com.maledictus.item.ItemFactory;
import com.maledictus.item.ItemType;
import com.maledictus.item.key.KeyType;
import com.maledictus.item.potion.Potion;
import com.maledictus.item.potion.PotionType;
import com.maledictus.item.weapon.WeaponType;
import org.junit.Test;

public class ItemTest {
//    @Test
//    public void testItem_shouldCreateItem_whenConstructorIsPassedInProperArguments() {
//        Item sword = new Item("Iron Sword", "A sharp sword made of the finest iron", ItemType.WEAPON, WeaponType.SLASHING);
//        Item sword2 = new Item("Iron Sword", "A sharp sword made of the finest iron", ItemType.WEAPON, WeaponType.PIERCING);
//        System.out.println(sword);
//        System.out.println(sword2);
//    }

    @Test
    public void testItemFactoryCreateItem_shouldReturnANewItem_whenCreateItemMethodIsCalled() {
        //Item item = ItemFactory.createItem("Iron Sword", "A sharp sword made of the finest iron", ItemType.WEAPON, WeaponType.SLASHING);
        //Item item2 = ItemFactory.createItem("Brass Key", "A key made of brass", ItemType.KEY, KeyType.DUNGEON);
        //System.out.println(item);
        //System.out.println(item2);
    }

    @Test
    public void createItemTest() {
        Potion potion = ItemFactory.createItem("Potion of healing", "healing potion", ItemType.POTION, PotionType.HEALING);
        potion.use();
    }
}
