package com.maledictus.client;

import com.maledictus.GUI;
import com.maledictus.Game;
import com.maledictus.WelcomePage;
import com.maledictus.music.GameMusic;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.text.ParseException;

public class Main {

    public static void main(String[] args) throws IOException, ParseException, org.json.simple.parser.ParseException, UnsupportedAudioFileException, LineUnavailableException {
        Game game = new Game();
        WelcomePage.InitImage();
        game.initiateGame();

        GameMusic gameMusic = new GameMusic();
        gameMusic.playMusic();
        GUI giu = new GUI();

















    }
}
