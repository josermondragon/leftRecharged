package com.maledictus;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class PlayMusic {

    public void playMusic() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        File audioFile = new File("C;/RPG-Proj/Maledictus/data/lavender-town-music.wav");

        AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);

        AudioFormat format = audioStream.getFormat();
        DataLine.Info info = new DataLine.Info(Clip.class, format);

        Clip audioClip = (Clip) AudioSystem.getLine(info);

        audioClip.open(audioStream);
        audioClip.start();
    }

}