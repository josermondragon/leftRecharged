package com.maledictus.npc.ally;

import java.util.Queue;

public interface Ally {

    void assignQuest(String quest);
    void talk(int playerChoice);
    void giveItem();

}
