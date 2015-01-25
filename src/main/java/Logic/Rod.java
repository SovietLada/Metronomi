/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * Created by Leevi 2015.
 * All rights reserved.
 */
package Logic;

/**
 *
 * @author Leevi
 */
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;

public class Rod {

    public String click() {
        playSound("/Audio/click.wav");
        return "Click!";
    }

    public String accent() {
        playSound("/Audio/accent.wav");
        return "Accent!";
    }

    public static synchronized void playSound(final String url) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    javax.sound.sampled.Clip clip = AudioSystem.getClip();
                    AudioInputStream inputStream;
                    inputStream = AudioSystem.getAudioInputStream(getClass().getResource(url));
                    clip.open(inputStream);
                    clip.start();
                }
                catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            }
        }).start();
    }
}
