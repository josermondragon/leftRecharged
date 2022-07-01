package com.maledictus.client;


import com.maledictus.GUI;
import com.maledictus.Game;
import com.maledictus.WelcomePage;
import com.maledictus.music.GameMusic;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.text.ParseException;

import static java.lang.Thread.sleep;

public class Main {

    public static void main(String[] args) throws IOException, ParseException, org.json.simple.parser.ParseException, UnsupportedAudioFileException, LineUnavailableException, InterruptedException {
        WelcomePage.InitImage();
        sleep(3000);
        GameMusic gameMusic = new GameMusic();
      // gameMusic.playMusic();

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Game game = null;
                try {
                    game = new Game();
                } catch (UnsupportedAudioFileException e) {
                    e.printStackTrace();
                } catch (LineUnavailableException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    game.initiateGame();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (org.json.simple.parser.ParseException e) {
                    e.printStackTrace();
                } catch (ParseException e) {
                    e.printStackTrace();
                } catch (UnsupportedAudioFileException e) {
                    e.printStackTrace();
                } catch (LineUnavailableException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
