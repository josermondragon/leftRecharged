package com.maledictus;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Music {
    private Clip audioClip;
    public Music() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        File audioFile = new File("resources/data/lavender-town-music.wav");

        AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);

        AudioFormat format = audioStream.getFormat();
        DataLine.Info info = new DataLine.Info(Clip.class, format);
        audioClip = (Clip) AudioSystem.getLine(info);
        audioClip.open(audioStream);



    }

    public void playMusic() {
        audioClip.start();
    }

    public void stopMusic() {
        audioClip.stop();
    }
}