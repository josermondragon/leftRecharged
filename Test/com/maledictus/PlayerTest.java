package com.maledictus;

import org.junit.Test;
import static org.junit.Assert.*;

public class PlayerTest {
    // Creating variables for tests
    Player playerOne = new Player("Ryan");
    Item sword = new Item("Iron Sword", "A sharp sword made of the finest iron", Item.ItemType.WEAPON, Item.WeaponType.SLASH);
    Item hammer = new Item("Iron War Hammer", "A blunt heavy war hammer made of the finest iron", Item.ItemType.WEAPON, Item.WeaponType.CRUSHING);

    @Test
    public void testAddItem_shouldPutTheItemIntoPlayerInventory_whenItemIsPassedIntoMethod() {
        playerOne.addItem(sword);
        playerOne.addItem(hammer);
        System.out.println(playerOne.getInventory());
    }

    @Test
    public void testPlayerFactoryCreateCharacter_shouldReturnANewPlayer_whenCreateCharacterMethodIsCalled() {
        Player playerOne = PlayerFactory.createPlayer();
        System.out.println(playerOne);
    }

}