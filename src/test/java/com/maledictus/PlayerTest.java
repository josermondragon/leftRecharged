package com.maledictus;

import com.maledictus.item.*;
import com.maledictus.item.potion.*;
import com.maledictus.item.weapon.*;
import com.maledictus.npc.Ghost;
import com.maledictus.npc.NPC;
import com.maledictus.npc.Species;
import com.maledictus.player.*;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

public class PlayerTest {

    private final File text = new File("test/PlayerTest.txt");
    String[] items;

    private Player playerOne;
    private  Ghost ghost;
    private  Item sword ;

    @Before
    public void setUp() {
        playerOne = new Player("Ryan");
        ghost = new Ghost(3, 100, "Ghost", "A ghost", true, Species.GHOST );
        sword = new Weapon("Iron Sword", "A sharp sword made of the finest iron", ItemType.WEAPON, WeaponType.SLASHING);
    }

    @Test
    public void testAddItem_shouldPutTheItemIntoPlayerInventory_whenItemIsPassedIntoMethod() {
        playerOne.addItem(sword);
        assertTrue(playerOne.getInventory().containsKey(sword.getName()));
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

    @Test
    public void attackShouldDealLessDamageWhenWeaponIsNotEquipped(){
        int ghostHp = playerOne.attack(ghost);
        assertEquals(70, ghostHp);
    }

    @Test
    public void attackShouldDealMoreDamageWhenWeaponIsEquipped(){
        playerOne.equipWeapon();
        int ghostHp = playerOne.attack(ghost);
        assertEquals(50, ghostHp);
    }

}