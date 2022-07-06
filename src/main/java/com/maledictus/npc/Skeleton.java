package com.maledictus.npc;

import com.maledictus.Printer;
import com.maledictus.item.Item;
import com.maledictus.npc.enemy.Enemy;
import com.maledictus.npc.enemy.EnemyType;
import com.maledictus.player.Player;

import java.awt.*;
import java.util.Map;

public class Skeleton extends NPC {

    EnemyType enemyType;
    Map<Integer, String> dialogue;
    Item item;

    public Skeleton(int id, int hitPoints, String name, String description, boolean isHostile, Species species, EnemyType enemyType) {
        super(id, hitPoints, name, description, isHostile, species);
    }
    public Skeleton(int id, int hitPoints, String name, String description, boolean isHostile, Species species, Map<Integer, String> dialogue, Item item, EnemyType theType) {
        super(id, hitPoints, name, description, isHostile, species);
        this.dialogue = dialogue;
    }
//what we used to create the king
    public Skeleton(int id, int hitPoints, String name, String description, boolean b, Species species, EnemyType enemyType, Item item) {
        super(id, hitPoints, name, description, b, species);
        this.enemyType = enemyType;
        this.item = item;
    }


    @Override
    public int attack(Player player) {
        int damage = 30;
        Printer.print(Color.GREEN, "Player hit points is for: " + damage + " from: " + player.getHitPoints());
        player.setHitPoints(player.getHitPoints() - damage);
        Printer.print(Color.MAGENTA, "Player HP is: " + player.getHitPoints());
        return player.getHitPoints();
    }

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

}