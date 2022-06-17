package com.maledictus;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException, ParseException, org.json.simple.parser.ParseException, UnsupportedAudioFileException, LineUnavailableException {

        Game game = new Game();
        game.initiateGame();

    }
}
