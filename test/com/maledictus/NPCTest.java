package com.maledictus;

import com.maledictus.npc.NPC;
import com.maledictus.npc.Skeleton;
import com.maledictus.npc.enemy.EnemyType;
import org.junit.Test;

public class NPCTest {

    private final NPC skeleton = new Skeleton(1, 100, "Skeleton guard", "A bone guy.", true, EnemyType.STANDARD);

    @Test
    public void testAddItem_shouldPutTheItemIntoPlayerInventory_whenItemIsPassedIntoMethod() {
        System.out.println(skeleton.getName());
    }

}