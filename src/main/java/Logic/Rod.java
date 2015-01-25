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
import java.io.*;
import javax.sound.sampled.*;

public class Rod {

  public String click() {
    playSound("src/main/java/audio/click.wav");
    return "Click!";
  }

  public String accent() {
    playSound("src/main/java/audio/accent.wav");
    return "Accent!";
  }

  public static synchronized void playSound(final String url) {
    new Thread(new Runnable() {
      @Override
      public void run() {
        try {
          File file = new File(url);
          AudioInputStream stream;
          AudioFormat format;
          DataLine.Info info;
          Clip clip;

          stream = AudioSystem.getAudioInputStream(file);
          format = stream.getFormat();
          info = new DataLine.Info(Clip.class, format);
          clip = (Clip) AudioSystem.getLine(info);
          clip.open(stream);
          clip.start();
        } catch (Exception e) {
        }
      }
    }).start();
  }
}
