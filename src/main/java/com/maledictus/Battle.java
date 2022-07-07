package com.maledictus;

import com.maledictus.npc.NPC;
import com.maledictus.player.Player;
import jdk.swing.interop.SwingInterOpUtils;
import org.json.simple.parser.ParseException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.io.IOException;

public  class Battle {

    private final Player player;
    private final NPC npc;
    private boolean combat = true;

     public Battle(Player player, NPC npc) {
        this.player = player;
        this.npc = npc;
    }

    public void battleStart() throws UnsupportedAudioFileException, LineUnavailableException, IOException, ParseException, java.text.ParseException, InterruptedException {

        if(combat && player.getHitPoints()>0 && npc.getHitPoints() > 0){

            GUI gui = GUI.getInstance();
            gui.getInputtedUser().addActionListener(e -> {
                String userCommand = gui.getInputtedUser().getText();
                gui.getInputtedUser().setText("");
                gui.getInputtedUser().removeActionListener(gui.getInputtedUser().getActionListeners()[0]);
                battleRound(userCommand);
                try {
                    battleStart();
                } catch (UnsupportedAudioFileException | IOException | ParseException | java.text.ParseException | LineUnavailableException | InterruptedException ex) {
                    ex.printStackTrace();
                }
            });
        } else {
            if(player.getHitPoints() <=0) {
                Printer.print(Color.LIGHT_GRAY, "Just now you saw it... Peering into the abyss she was there... Death");
            }
            Game game = Game.getInstance();
            game.endFight();
        }

    }

    public void battleRound(String userCommand) {
        if (userCommand.equalsIgnoreCase("attack")) {
            player.attack(npc);
            if(npc.getHitPoints() > 0){
                npc.attack(player);
            }
        } else if (userCommand.equalsIgnoreCase("run")) {
            this.player.setHitPoints(0);
            Printer.print(Color.RED, "You attempt to run away..... THERE IS NO RUNNING FROM FATE!");
        } else if (userCommand.equalsIgnoreCase("equip")) {
            Printer.print("You equipped your weapon");
            player.equipWeapon();
        }

    }

    public boolean isCombat() {
        return combat;
    }

    public void setCombat(boolean combat) {
        this.combat = combat;
    }
}