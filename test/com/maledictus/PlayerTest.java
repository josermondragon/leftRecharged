package com.maledictus;

import com.maledictus.item.Item;
import com.maledictus.item.ItemType;
import com.maledictus.item.potion.Potion;
import com.maledictus.item.weapon.Weapon;
import com.maledictus.item.weapon.WeaponType;
import com.maledictus.player.Player;
import com.maledictus.player.PlayerFactory;
import org.junit.Test;

public class PlayerTest {
    Player playerOne = new Player("Ryan");
    //Item sword = new Weapon("Iron Sword", "A sharp sword made of the finest iron", ItemType.WEAPON, WeaponType.SLASHING);
    //Item hammer = new Potion("Iron War Hammer", "A blunt heavy war hammer made of the finest iron", ItemType.WEAPON, WeaponType.BLUNT);

    @Test
    public void testAddItem_shouldPutTheItemIntoPlayerInventory_whenItemIsPassedIntoMethod() {
        //playerOne.addItem(sword);
        //playerOne.addItem(hammer);
        System.out.println(playerOne.getInventory());
    }

    // Need to add input txt to scanner functionality (for testing)
    @Test
    public void testPlayerFactoryCreateCharacter_shouldReturnANewPlayer_whenCreateCharacterMethodIsCalled() {
        Player playerOne = PlayerFactory.createPlayer();
        System.out.println(playerOne);
    }

}