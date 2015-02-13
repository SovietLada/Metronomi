/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * Created by Leevi 2015.
 * All rights reserved.
 */
package Metronomi.Logic;

/**
 * Metronome implements core logic, responds to UI calls and controls
 * the Rod object.
 * 
 * @author Leevi
 */
public class Metronome {

  // TODO: quarters to eights selection, i.e delay = delay / 2.
  private final Rod rod;
  private int bpm;
  private long delay;
  private boolean isRunning;

  /**
   * canStop removes a UI bug that confuses the beat timing.
   */
  public boolean canStop;

  /**
   * canStart removes a UI bug that confuses the beat timing.
   */
  public boolean canStart;
  
  private int quarters;
  private int bar;

  /**
   * tempoStep defines the amount of bpm change per appropriate command.
   */
  public static int tempoStep = 4;
  
  private SignaturesEnum signature;

  /**
   * Initializer sets every value to a static default.
   * 
   * @see Metronomi.Logic.Rod
   */
  public Metronome() {
    this.rod = new Rod();
    this.bpm = 100;
    this.canStop = true;
    this.canStart = true;
    this.quarters = 1;
    setDelay();

    this.isRunning = false;
    this.signature = SignaturesEnum.fourQuarters;
    setSignature();
  }

  /**
   * setSignature switches the signature enumeration and sets bar attribute
   * to match an appropriate value.
   * 
   * @see Metronomi.Logic.SignaturesEnum
   */
  public void setSignature() {
    switch (signature) {
      case threeQuarters:
        bar = 3;
        break;
      case fourQuarters:
        bar = 4;
        break;
      case fiveQuarters:
        bar = 5;
        break;
      case sevenQuarters:
        bar = 7;
        break;
    }
  }

  /**
   * changeSignature sets a new enumeration value to newSignature.
   * 
   * @param newSignature, SignaturesEnum that defines the bar attribute,
   * which itself defines the time signature.
   * 
   * @see Metronomi.Logic.SignaturesEnum
   */
  public void changeSignature(SignaturesEnum newSignature) {
    this.signature = newSignature;
    setSignature();
  }

  /**
   * start will fire a loop that runs the metronome clicks. Control component
   * creates a new thread for the start method to run on.
   * 
   * @see Metronomi.Control.KeyboardListener
   */
  public void start() {
    isRunning = true;
    denyStopping();
    while (isRunning) {
      swing();
    }
    quarters = 1;
  }

  /**
   * stop ends the thread that was created in start method.
   * 
   * @see Metronomi.Logic.Metronome#start() 
   */
  public void stop() {
    isRunning = false;
    canStop = true;
    denyStarting();
    try {
      Thread.sleep(delay);
      canStart = true;
    }
    catch (InterruptedException e) {
      System.err.println(e);
    }
  }

  /**
   * swing prints the details of the metronome per every beat, 
   * and calls rod to play a sound after a sleep delay has been executed.
   * 
   * @see Metronomi.Logic.Rod
   */
  public void swing() {
    if (quarters == 1) {
      System.out.print("Delay: " + delay + "ms, Tempo: " + bpm + " bpm - ");
      System.out.println(rod.accent());
      ++quarters;
    }
    else {
      System.out.print("Delay: " + delay + "ms, Tempo: " + bpm + " bpm - ");
      System.out.println(rod.click());
      ++quarters;
    }
    if (quarters > bar) {
      quarters = 1;
    }
    try {
      Thread.sleep(delay);
      canStop = true;
    }
    catch (InterruptedException e) {
      System.err.println(e);
    }
  }

  /**
   * @see Metronomi.Logic.Rod
   * 
   * @return rod, object which plays sound files
   */
  public Rod getRod() {
    return rod;
  }

  /**
   *
   * @return beats per minute, value that defines delay.
   */
  public int getBpm() {
    return bpm;
  }

  /**
   * Set beats per minute and convert it to milliseconds.
   * @param bpm
   */
  public void setBpm(int bpm) {
    this.bpm = bpm;
    setDelay();
  }

  /**
   *
   * @return delay between each beat.
   */
  public long getDelay() {
    return delay;
  }

  private void setDelay() {
    double a = Math.round((double) 60 / bpm * 1000);
    long b = (long) a;
    delay = b;
  }

  /**
   *
   * @return isRunning
   */
  public boolean isRunning() {
    return isRunning;
  }

  /**
   *
   * @return canStop
   */
  public boolean canStop() {
    return canStop;
  }

  /**
   * denyStopping sets canStop attribute to false.
   */
  public void denyStopping() {
    canStop = false;
  }

  /**
   *
   * @return canStart
   */
  public boolean canStart() {
    return canStart;
  }

  /**
   * denyStartring sets canStart attribute to false.
   */
  public void denyStarting() {
    canStart = false;
  }
}
