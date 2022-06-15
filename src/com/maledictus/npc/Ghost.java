package com.maledictus.npc;

import com.maledictus.npc.ally.Ally;

public class Ghost extends NPC implements Ally {

    Ghost(int id, String name, String description, boolean isHostile) {
        super(id, name, description, isHostile);
    }

    @Override
    public void giveQuest(String quest) {

    }

    @Override
    public void talk() {

    }

    @Override
    public void giveItem() {

    }
}