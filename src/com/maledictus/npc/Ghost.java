package com.maledictus.npc;

import com.maledictus.npc.ally.Ally;
import com.maledictus.npc.ally.Quest;

import java.util.Map;

public class Ghost extends NPC implements Ally {

    Quest quest;
    Map<Integer, String> dialogue;


    public Ghost(int id, int hitPoints, String name, String description, boolean isHostile, Species species, Map<Integer, String> dialogue) {
        super(id, hitPoints, name, description, isHostile, species);
        this.dialogue = dialogue;
    }

    public Ghost(int id, int hitPoints, String name, String description, boolean isHostile, Species species, Map<Integer, String> dialogue, Quest quest) {
        super(id, hitPoints, name, description, isHostile, species);
        this.dialogue = dialogue;
        this.quest = quest;
    }

    public Map<Integer, String> getDialog() {
        return dialogue;
    }

    public Map<Integer, String> getQuestDialog() {
        return quest.getQuestDialogue();
    }

    public String getQuestWinCondition() {
        return quest.getWinCondition();
    }

    public boolean getQuestActive() {
        return quest.isActive();
    }

    public void setQuestCompleted(boolean completed) {
        this.quest.setCompleted(completed);
    }

    public void setQuestActive(boolean active) {
        this.quest.setActive(active);
    }

    public String questTalk(int playerChoice) {
        String result = null;
        for (Map.Entry<Integer, String> option : getQuestDialog().entrySet()) {
            if(playerChoice == option.getKey()) {
                result = option.getValue();
                break;
            }
        }
        return result;
    }

    public Quest getQuest() {
        return quest;
    }

    @Override
    public String talk(int playerChoice) {
        String result = null;
        for (Map.Entry<Integer, String> option : dialogue.entrySet()) {
            if(playerChoice == option.getKey()) {
                result = option.getValue();
                break;
            }
        }
        return result;
    }

    @Override
    public void assignQuest(boolean quest) {

    }

    @Override
    public void giveItem() {

    }

    @Override
    public String toString() {
        return "Ghost{" +
                "quest=" + quest +
                ", dialog=" + dialogue +
                '}';
    }
}