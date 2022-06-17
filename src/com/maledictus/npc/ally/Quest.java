package com.maledictus.npc.ally;

import com.maledictus.item.Item;

import java.util.Map;

public class Quest {

    String title;
    String description;
    Map<Integer, String> questDialogue;
    Item reward;
    String winCondition;
    boolean isActive;
    boolean isCompleted;

    public Quest(String title, String description, Item reward, Map<Integer, String> questDialogue, String winCondition, boolean isActive, boolean isCompleted) {
        this.title = title;
        this.description = description;
        this.reward = reward;
        this.questDialogue = questDialogue;
        this.winCondition = winCondition;
        this.isActive = isActive;
        this.isCompleted = isCompleted;

    }

    public String getDescription() {
        return description;
    }

    public Item getReward() {
        return reward;
    }

    public Map<Integer, String> getQuestDialogue() {
        return questDialogue;
    }

    public String getTitle() {
        return title;
    }

    public String getWinCondition() {
        return winCondition;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    @Override
    public String toString() {
        return "Quest{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", questDialogue=" + questDialogue +
                ", reward=" + reward +
                ", winCondition='" + winCondition + '\'' +
                ", isActive=" + isActive +
                ", isCompleted=" + isCompleted +
                '}';
    }
}