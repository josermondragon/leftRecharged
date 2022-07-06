package com.maledictus.npc;

import com.maledictus.item.Item;
import com.maledictus.item.weapon.WeaponType;
import com.maledictus.npc.enemy.Enemy;
import com.maledictus.npc.enemy.EnemyType;
import com.maledictus.player.Player;
import java.util.Map;

public class Banshee extends NPC implements Enemy {

    // int hitPoints;
    // WeaponType weakness = WeaponType.SLASHING;

    public Banshee(int id, int hitPoints, String name, String description, boolean isHostile, Species species, EnemyType enemyType) {
        super(id, hitPoints, name, description, isHostile, species);
    }
//created another constructor so they hold items.
    public Banshee(int id, int hitPoints, String name, String description, boolean isHostile, Species species, Map<Integer, String> dialogue, Item item) {

        super(id,hitPoints, name, description, isHostile, species, item);
    }

    @Override
    public int attack(Player player) {
        System.out.println("Player hit points is " + player.getHitPoints());
        player.setHitPoints(player.getHitPoints() - 8);
        System.out.println("Player hit points is " + player.getHitPoints());
        return player.getHitPoints();
    }

    @Override
    public void dropItem() {

    }

//    enemy methods we had to inherit
    @Override
    public void takeDamage() {

    }

    @Override
    public void attack() {

    }

}