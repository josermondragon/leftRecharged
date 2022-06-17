package com.maledictus;

import com.maledictus.npc.*;
import com.maledictus.npc.enemy.EnemyType;
import org.junit.Before;
import org.junit.Test;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class NPCTest {

    private final Map<Integer, NPC> npcMap = NPCFactory.getNPCList();
    Map<Integer, NPC> npcMap2 = new HashMap<>();
    Map<Integer, String> dialog;

    @Before
    public void setUp() {
        dialog = new HashMap<>();
        dialog.put(1, "I am a gosh dang ghost.");
        dialog.put(2, "Let's Duel!");

        NPCFactory.createNPC(1,100, "ghostly librarian", "A semi translucent librarian, it seems to be focused on something.", false, Species.GHOST, dialog, null);
        NPCFactory.createNPC(2, 100, "Skeleton guard", "A bone guy.", true, Species.SKELETON, EnemyType.STANDARD);
        Ghost ghost = (Ghost) npcMap.get(1); // Grab NPC by ID
        ghost.talk(1);
        ghost.talk(2);

        NPC npc = new Ghost(1,100, "ghostly librarian", "A semi translucent librarian, it seems to be focused on something.", false, Species.GHOST, dialog);
        Skeleton npc2 = new Skeleton(2, 100, "Skeleton guard", "A bone guy.", true, Species.SKELETON, EnemyType.STANDARD);
        npcMap2.put(1, npc);
        npcMap2.put(2, npc2);
    }

    @Test
    public void testPlayerFactoryCreateNPC_shouldCreateListOfNPCs_whenCreateNPCMethodIsCalled() {
         assertEquals(npcMap.keySet(), npcMap2.keySet());
    }

}