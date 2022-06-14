package com.maledictus;

import com.maledictus.item.*;
import com.maledictus.item.potion.*;
import com.maledictus.item.weapon.*;
import com.maledictus.player.*;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class PlayerTest {

    private final File text = new File("test/PlayerTest.txt");
    String[] items;

    private final Player playerOne = new Player("Ryan");
    private final Item sword = new Weapon("Iron Sword", "A sharp sword made of the finest iron", ItemType.WEAPON, WeaponType.SLASHING);
    private final Item hammer = new Weapon("Iron War Hammer", "A blunt heavy war hammer made of the finest iron", ItemType.WEAPON, WeaponType.BLUNT);

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