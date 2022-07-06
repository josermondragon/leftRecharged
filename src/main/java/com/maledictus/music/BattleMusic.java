    package com.maledictus.music;

import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

    public class BattleMusic {
    private static Clip audioClip;
    private FloatControl gainControl;

    public BattleMusic() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        //File audioFile = new File("resources/data/battle-music.wav");
        InputStream audioFile = new BufferedInputStream(BattleMusic.class.getClassLoader().getResourceAsStream("data/battle-music.wav"));


        AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);

        AudioFormat format = audioStream.getFormat();
        DataLine.Info info = new DataLine.Info(Clip.class, format);
        audioClip = (Clip) AudioSystem.getLine(info);
        audioClip.open(audioStream);
        gainControl = (FloatControl) audioClip.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(-30.0f);
    }

    public void playMusic() {
        audioClip.start();
        audioClip.loop(audioClip.LOOP_CONTINUOUSLY);
    }

    public static void stopMusic() {
         audioClip.stop();

    }
}