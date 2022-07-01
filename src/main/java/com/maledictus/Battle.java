package com.maledictus;

import com.maledictus.item.Item;
import com.maledictus.item.ItemType;
import com.maledictus.npc.NPC;
import com.maledictus.player.Player;

import java.util.Map;

import static com.maledictus.Input.scannerUserInput;

public class Battle {

    private final Player player;
    private final NPC npc;
    private boolean combat = true;
    private Item spoilsOfWar;


     public Battle(Player player, NPC npc) {
        this.player = player;
        this.npc = npc;
    }

    public void start() {
       while(combat && player.getHitPoints() > 0 && npc.getHitPoints() > 0) {
           // displayInventory();
           String userCommand = scannerUserInput();
           battleRound(userCommand);
       }
        //add to start method after while loop
        if(npc.getHitPoints()==0 && (npc.getItem() != null)){
            setSpoilsOfWar(npc.getItem());
            System.out.println(npc.getItem());
        }

    }

    public void battleRound(String userCommand) {
        if (userCommand.equalsIgnoreCase("attack")) {
            player.attack(npc);
            Printer.print("the npc used their weapon " + npc.getItem());
            npc.attack(player);
        } else if (userCommand.equalsIgnoreCase("run")) {
            this.player.setHitPoints(0);
            System.out.println("You attempt to run away.....");
        } else if (userCommand.equalsIgnoreCase("equip")) {
            System.out.println("CURRENT ITEMS");
            System.out.println("-------------");
            for (Map.Entry<String, Item> item : player.getInventory().entrySet()) {
                System.out.println(item.getKey());
            }
            String itemSelect = scannerUserInput();
            System.out.println("You equipped " + itemSelect);
            player.equipWeapon();
        }
    }

    public Item getSpoilsOfWar() {
        return spoilsOfWar;
    }

    public void setSpoilsOfWar(Item spoilsOfWar) {
        this.spoilsOfWar = spoilsOfWar;
    }

    public boolean areThereSpoils(){
         boolean areThere = false;
         if(this.spoilsOfWar != null){
             Printer.print("yes there are spoils!!");
             areThere=true;
         }

         return areThere;
    }

    public boolean isCombat() {
        return combat;
    }

    public void setCombat(boolean combat) {
        this.combat = combat;
    }
}