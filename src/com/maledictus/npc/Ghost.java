package com.maledictus.npc;

import com.maledictus.item.Item;
import com.maledictus.npc.ally.Ally;
import com.maledictus.npc.ally.Quest;

import java.util.Map;

public class Ghost extends NPC implements Ally {

    Quest quest;
    Map<Integer, String> dialog;

    public Ghost(int id, int hitPoints, String name, String description, boolean isHostile, Species species, Map<Integer, String> dialog) {
        super(id, hitPoints, name, description, isHostile, species);
        this.dialog = dialog;
    }

    public Ghost(int id, int hitPoints, String name, String description, boolean isHostile, Species species, Map<Integer, String> dialog, Quest quest) {
        super(id, hitPoints, name, description, isHostile, species);
        this.dialog = dialog;
        this.quest = quest;
    }

    @Override
    public void assignQuest(String quest) {

    }

    @Override
    public void talk(int playerChoice) {
        for (Map.Entry<Integer, String> option : dialog.entrySet()) {
            if(playerChoice == option.getKey()) {
                System.out.println(option.getValue());
                break;
            }
        }
    }

    @Override
    public void giveItem() {

    }
}