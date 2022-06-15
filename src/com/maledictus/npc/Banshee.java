package com.maledictus.npc;

import com.maledictus.item.weapon.WeaponType;
import com.maledictus.npc.enemy.Enemy;
import com.maledictus.npc.enemy.EnemyType;

public class Banshee extends NPC implements Enemy {

    int hitPoints;
    WeaponType weakness = WeaponType.SLASHING;

    Banshee(int id, int hitPoints, String name, String description, boolean isHostile, Species species, EnemyType enemyType) {
        super(id, hitPoints, name, description, isHostile, species);
    }

    @Override
    public void takeDamage() {

    }

    @Override
    public void attack() {

    }

    @Override
    public void dropItem() {

    }
}