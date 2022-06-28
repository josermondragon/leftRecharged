package com.maledictus.npc;

import com.maledictus.npc.enemy.Enemy;
import com.maledictus.npc.enemy.EnemyType;
import com.maledictus.player.Player;

public class Skeleton extends NPC {

    EnemyType enemyType;

    public Skeleton(int id, int hitPoints, String name, String description, boolean isHostile, Species species, EnemyType enemyType) {
        super(id, hitPoints, name, description, isHostile, species);
    }

//    @Override
//    public void takeDamage() {
//
//    }

    @Override
    public int attack(Player player) {
        System.out.println("Player hit points is " + player.getHitPoints());
        player.setHitPoints(player.getHitPoints() - 8);
        System.out.println("Player hit points is " + player.getHitPoints());
        return player.getHitPoints();
    }

//    @Override
//    public void dropItem() {
//
//    }
}