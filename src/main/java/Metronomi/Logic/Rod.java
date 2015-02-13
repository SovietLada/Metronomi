/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * Created by Leevi 2015.
 * All rights reserved.
 */
package Metronomi.Logic;

import java.io.*;
import javax.sound.sampled.*;

/**
 * Rod fetches and plays various sound clips requested by the metronome object.
 * 
 * @author Leevi
 */
public class Rod {

  /**
   * click plays a clicking sound every non-accented beat.
   * 
   * @see Metronomi.Logic.Metronome#swing() 
   * 
   * @return static String, that indicates the type of the sound.
   */
  public String click() {
    playSound("src/main/java/Metronomi/audio/click.wav");
    return "Click!";
  }

  /**
   * accent plays an accent sound on the first beat of the bar.
   * 
   * @see Metronomi.Logic.Metronome#swing() 
   * 
   * @return static String, that indicates the type of the sound.
   */
  public String accent() {
    playSound("src/main/java/Metronomi/audio/accent.wav");
    return "Accent!";
  }

  private static synchronized void playSound(final String url) {
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
        }
        catch (IOException | LineUnavailableException | UnsupportedAudioFileException e) {
          System.err.println(e);
        }
      }
    }).start();
  }
}
