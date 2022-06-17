package com.maledictus.npc.ally;

import java.util.Map;

public interface Ally {

    Map<Integer, String> getDialog();
    String talk(int playerChoice);

}
