package com.maledictus.npc;

import com.maledictus.item.Item;
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
    public Ghost(int id, int hitPoints, String name, String description, boolean isHostile, Species species, Map<Integer, String> dialogue, Item item) {
        super(id, hitPoints, name, description, isHostile, species);
        this.dialogue = dialogue;
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

    public void assignQuest(boolean active) {
        this.quest.setActive(active);
    }

    public String getQuestWinCondition() {
        return quest.getWinCondition();
    }

    public boolean getQuestStatus() {
        return quest.isActive();
    }

    public void setQuestCompleted(boolean completed) {
        this.quest.setCompleted(completed);
    }

    public Item giveQuestReward() {
        return quest.getReward();
    }

    public Map<Integer, String> getQuestDialog() {
        return quest.getQuestDialogue();
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
    public Map<Integer, String> getDialog() {
        return dialogue;
    }

    @Override
    public String toString() {
        return "Ghost{" +
                "quest=" + quest +
                ", dialog=" + dialogue +
                '}';
    }
}