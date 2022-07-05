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

    public static void main(String[] args) throws IOException, UnsupportedAudioFileException, LineUnavailableException, InterruptedException {
        WelcomePage.InitImage();
        sleep(2000);
        GameMusic gameMusic = new GameMusic();
      // gameMusic.playMusic();

        javax.swing.SwingUtilities.invokeLater(() -> {
            Game game = null;
            try {
                game = new Game();
            } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
                e.printStackTrace();
            }
            try {
                assert game != null;
                game.initiateGame();
            } catch (IOException | org.json.simple.parser.ParseException | ParseException | UnsupportedAudioFileException | LineUnavailableException e) {
                e.printStackTrace();
            }
        });
    }
}
