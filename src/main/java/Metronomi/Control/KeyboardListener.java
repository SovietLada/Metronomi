/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * Created by Leevi 2015.
 * All rights reserved.
 */
package Metronomi.Control;

import Metronomi.Logic.Metronome;
import Metronomi.Logic.SignaturesEnum;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JTextField;

/**
 * KeyboardListener receives action commands from UI buttons, and mutates the
 * Metronome object according to user's configurations.
 *
 * @author Leevi
 */
public class KeyboardListener implements KeyListener, ActionListener {

  private final Metronome metronome;
  private final JTextField textField;

  /**
   * Initializer initializes Metronome & JTextField objects.
   *
   * @param textField
   * @param metronome
   */
  public KeyboardListener(JTextField textField, Metronome metronome) {
    this.metronome = metronome;
    this.textField = textField;
  }

  /**
   *
   * @return metronome
   */
  public Metronome getMetronome() {
    return metronome;
  }

  @Override
  public void keyTyped(KeyEvent e) {
    throw new UnsupportedOperationException("No implementation.");
  }

  @Override
  public void keyPressed(KeyEvent e) {
    throw new UnsupportedOperationException("No implementation.");
  }

  @Override
  public void keyReleased(KeyEvent e) {
    throw new UnsupportedOperationException("No implementation.");
  }

  /**
   * actionPerformed calls proper methods based on the input it receives.
   * 
   * @param ActionEvent
   * 
   * @see Metronomi.GUI.UserInterface
   */
  @Override
  @SuppressWarnings("ConvertToStringSwitch")
  public void actionPerformed(ActionEvent e) {
    if (e.getActionCommand().equals("onoff")) {
      if (metronome.isRunning() && metronome.canStop()) {
        stopMetronome();
      }
      else if (!metronome.isRunning() && metronome.canStart()) {
        startMetronome();
      }
    }
    else if (e.getActionCommand().equals("faster")) {
      if (metronome.getBpm() < 180) {
        incrementTempo();
      }
    }
    else if (e.getActionCommand().equals("slower")) {
      if (metronome.getBpm() > 20) {
        decrementTempo();
      }
    }
    else if (e.getActionCommand().equals("3/4")) {
      metronome.changeSignature(SignaturesEnum.threeQuarters);
    }
    else if (e.getActionCommand().equals("4/4")) {
      metronome.changeSignature(SignaturesEnum.fourQuarters);
    }
    else if (e.getActionCommand().equals("5/4")) {
      metronome.changeSignature(SignaturesEnum.fiveQuarters);
    }
    else if (e.getActionCommand().equals("7/4")) {
      metronome.changeSignature(SignaturesEnum.sevenQuarters);
    }
  }

  /**
   * stopMetronome stops the execution loop of clicks & accents.
   * 
   * @see Metronomi.Logic.Metronome#stop() 
   */
  public void stopMetronome() {
    metronome.stop();
  }

  /**
   * startMetronome fires a new thread that loops the click & accent sound 
   * clips.
   * @see Metronomi.Logic.Metronome#start() 
   */
  public void startMetronome() {
    new Thread(new Runnable() {
      @Override
      public void run() {
        try {
          metronome.start();
        }
        catch (Exception e) {
          System.err.println(e.getMessage());
        }
      }
    }).start();
  }

  /**
   * incrementTempo reduces the delay between beats.
   * 
   * @see Metronomi.Logic.Metronome#swing() 
   */
  public void incrementTempo() {
    metronome.setBpm(metronome.getBpm() + Metronome.tempoStep);
    textField.setText("Current tempo: " + metronome.getBpm() + " BPM");
  }

  /**
   * decrementTempo increases the delay between beats.
   * 
   * @see Metronomi.Logic.Metronome#swing()
   */
  public void decrementTempo() {
    metronome.setBpm(metronome.getBpm() - Metronome.tempoStep);
    textField.setText("Current tempo: " + metronome.getBpm() + " BPM");
  }
}
