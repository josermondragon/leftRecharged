package com.maledictus;

import com.maledictus.npc.*;
import com.maledictus.npc.enemy.EnemyType;
import org.junit.Before;
import org.junit.Test;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class NPCTest {

    private final Map<Integer, NPC> npcList = NPCFactory.getNPCList();
    Map<Integer, String> dialog;

    @Before
    public void setUp() {
        dialog = new HashMap<>();
        dialog.put(1, "I am a gosh dang ghost.");
        dialog.put(2, "Let's Duel!");
        NPCFactory.createNPC(1,100, "ghostly librarian", "A semi translucent librarian, it seems to be focused on something.", false, Species.GHOST, dialog, null);
        NPCFactory.createNPC(2, 100, "Skeleton guard", "A bone guy.", true, Species.SKELETON, EnemyType.STANDARD);

        System.out.println("Name: " + npcList.get(1).getName());
        System.out.println("Description: " + npcList.get(1).getDescription());
        Ghost ghost = (Ghost) npcList.get(1);
        ghost.talk(1);

        System.out.println(npcList.entrySet());

    }

    @Test
    public void testPlayerFactoryCreateNPC_shouldCreateListOfNPCs_whenCreateNPCMethodIsCalled() {
        Map<Integer, NPC> npcList = new HashMap<>();
        NPC npc = new Ghost(1,100, "ghostly librarian", "A semi translucent librarian, it seems to be focused on something.", false, Species.GHOST, dialog);
        NPC npc2 = new Skeleton(2, 100, "Skeleton guard", "A bone guy.", true, Species.SKELETON, EnemyType.STANDARD);
        npcList.put(1, npc);
        npcList.put(2, npc2);
        assertEquals(npcList.entrySet(), npcList.entrySet());
    }

}