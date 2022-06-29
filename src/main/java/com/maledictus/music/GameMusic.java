package com.maledictus.music;

import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class GameMusic {
    private static Clip audioClip;
    private FloatControl gainControl;

    public GameMusic() throws UnsupportedAudioFileException, IOException, LineUnavailableException {

        //InputStream in = getClass().getResourceAsStream("data/lavender-town-music.wav");

        InputStream audioFile = new BufferedInputStream(GameMusic.class.getClassLoader().getResourceAsStream("data/lavender-town-music.wav"));


        //File audioFile = new File("resources/data/lavender-town-music.wav");

        AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
        AudioFormat format = audioStream.getFormat();
        DataLine.Info info = new DataLine.Info(Clip.class, format);
        audioClip = (Clip) AudioSystem.getLine(info);
        audioClip.open(audioStream);
        gainControl = (FloatControl) audioClip.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(-8.0f);
    }

    public static void playMusic() {
        audioClip.start();
        audioClip.loop(audioClip.LOOP_CONTINUOUSLY);
    }


    public static void stopMusic() {
        audioClip.stop();
    }

    public void setMusicLow(){
        gainControl.setValue(-30.0f);
    }
    public void setMusicMidLow(){
        gainControl.setValue(-20.0f);
    }

    public void setMusicMidRange(){
        gainControl.setValue(-13.0f);
    }

    public void setMusicMidHigh(){
        gainControl.setValue(-2.0f);
    }

    public void setMusicHigh(){
        gainControl.setValue(5.0f);
    }


}