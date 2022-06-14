package com.maledictus;

import com.maledictus.item.Item;
import com.maledictus.player.Player;
import com.maledictus.player.PlayerFactory;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class PlayerTest {

    private final File text = new File("test/PlayerTest.txt");
    String[] items;

    private final Player playerOne = new Player("Ryan");
    private final Item sword = new Item("Iron Sword", "A sharp sword made of the finest iron", Item.ItemType.WEAPON, Item.WeaponType.SLASH);
    private final Item hammer = new Item("Iron War Hammer", "A blunt heavy war hammer made of the finest iron", Item.ItemType.WEAPON, Item.WeaponType.CRUSHING);

    @Test
    public void testAddItem_shouldPutTheItemIntoPlayerInventory_whenItemIsPassedIntoMethod() {
        playerOne.addItem(sword);
        playerOne.addItem(hammer);
        items = new String[]{};
        System.out.println(playerOne.getInventory().keySet());
//        assertEquals(playerOne.getInventory().keySet(), "[Iron Sword, Iron War Hammer]");
    }

    @Test
    public void testPlayerFactoryCreateCharacter_shouldAssertEqualsValue_whenCreatePlayerMethodIsCalled() {
        Player playerOne = PlayerFactory.createPlayer(Input.scannerTextInput(text));
        assertEquals(playerOne.getPlayerName(), "Bryan");
    }

    @Test
    public void testPlayerFactoryCreateCharacter_shouldAssertNotEqualsValue_whenCreatePlayerMethodIsCalled() {
        Player playerOne = PlayerFactory.createPlayer(Input.scannerTextInput(text));
        assertNotEquals(playerOne.getPlayerName(), "Ryan");
    }

}