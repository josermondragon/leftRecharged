package com.maledictus;

import com.maledictus.item.Item;
import com.maledictus.npc.NPC;
import com.maledictus.player.Player;
import org.json.simple.parser.ParseException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.Map;

public  class Battle {

    private final Player player;
    private final NPC npc;
    private boolean combat = true;
    private Item spoilsOfWar;


     public Battle(Player player, NPC npc) {
        this.player = player;
        this.npc = npc;
    }

    public boolean battleStart() throws UnsupportedAudioFileException, LineUnavailableException, IOException, ParseException, java.text.ParseException {

        boolean result = false;
        if(combat && player.getHitPoints() > 0 && npc.getHitPoints() > 0){

            GUI gui = GUI.getInstance();
            gui.getInputtedUser().addActionListener(e -> {
                String userCommand = gui.getInputtedUser().getText();
                gui.getInputtedUser().setText("");
                gui.getInputtedUser().removeActionListener(gui.getInputtedUser().getActionListeners()[0]);

                battleRound(userCommand);
                try {
                    battleStart();
                } catch (UnsupportedAudioFileException | IOException | ParseException | java.text.ParseException | LineUnavailableException ex) {
                    ex.printStackTrace();
                }
            });
        } else {
            result = true;
            Game game = Game.getInstance();
            game.endfight();
        }

//        //add to start method after while loop
//        if(npc.getHitPoints()==0 && (npc.getItem() != null)){
//            setSpoilsOfWar(npc.getItem());
//            System.out.println(npc.getItem());
//        }

        return result;
    }

    public void battleRound(String userCommand) {
        if (userCommand.equalsIgnoreCase("attack")) {
            player.attack(npc);
            if(npc.getHitPoints() > 0){
                npc.attack(player);
            }

        } else if (userCommand.equalsIgnoreCase("run")) {
            this.player.setHitPoints(0);
            Printer.print("You attempt to run away.....");
        } else if (userCommand.equalsIgnoreCase("equip")) {
            Printer.print("You equipped your weapon");
            player.equipWeapon();
//            Printer.print("CURRENT ITEMS");
//            Printer.print("-------------");
//
//            for (Map.Entry<String, Item> item : player.getInventory().entrySet()) {
//                Printer.print(item.getKey());
//            }
//            GUI gui = GUI.getInstance();
//            gui.getInputtedUser().addActionListener(e -> {
//                String itemSelect = gui.getInputtedUser().getText();
//                gui.getInputtedUser().setText("");
//                gui.getInputtedUser().removeActionListener(gui.getInputtedUser().getActionListeners()[0]);
//                Printer.print("You equipped " + itemSelect);
//                player.equipWeapon();
//            });
        }

//        if (userCommand.equalsIgnoreCase("attack")) {
//            player.attack(npc);
//            Printer.print("the npc used their weapon " + npc.getItem());
//            npc.attack(player);
//        } else if (userCommand.equalsIgnoreCase("run")) {
//            this.player.setHitPoints(0);
//            System.out.println("You attempt to run away.....");
//        } else if (userCommand.equalsIgnoreCase("equip")) {
//            System.out.println("CURRENT ITEMS");
//            System.out.println("-------------");
//            for (Map.Entry<String, Item> item : player.getInventory().entrySet()) {
//                System.out.println(item.getKey());
//            }
//            String itemSelect = scannerUserInput();
//            System.out.println("You equipped " + itemSelect);
//            player.equipWeapon();
//        }
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