package com.maledictus.npc;

import com.maledictus.npc.ally.Quest;
import com.maledictus.npc.enemy.EnemyType;
import java.util.HashMap;
import java.util.Map;

public class NPCFactory {

    private static final Map<Integer, NPC> npcList = new HashMap<>();

    private NPCFactory() {

    }

    public static void createNPC(int id, int hitPoints, String name, String description, boolean isHostile, Species species, Map<Integer, String> dialog, Quest quest) {

        Ghost npc = null;

        if (quest == null) {
            npc = new Ghost(id, hitPoints, name, description, false, species, dialog);
        } else {
            npc = new Ghost(id, hitPoints, name, description, false, species, dialog, quest);
        }
        if (npc != null) {
            npcList.put(npc.getId(), npc);
        }

    }

    public static void createNPC(int id, int hitPoints, String name, String description, boolean isHostile, Species species, EnemyType enemyType) {

        NPC npc = null;

         switch (species) {
            case BANSHEE:
                npc = new Banshee(id, hitPoints, name, description, true, species, enemyType);
                break;
            case SKELETON:
                npc = new Skeleton(id, hitPoints, name, description, true, species, enemyType);
                break;
            case SPIDER:
                npc = new Spider(id, hitPoints, name, description, true, species, enemyType);
                break;
            case GHOUL:
                npc = new Ghoul(id, hitPoints, name, description, true, species, enemyType);
                break;
            case ZOMBIE:
                npc = new Zombie(id, hitPoints, name, description, true, species, enemyType);
                break;
        }
        if (npc != null) {
            npcList.put(npc.getId(), npc);
        }
    }

    public static Map<Integer, NPC> getNPCList() {
        return npcList;
    }

    public static void clearRoomMap() {
        npcList.clear();
    }

}