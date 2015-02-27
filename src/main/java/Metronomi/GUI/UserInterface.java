/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * Created by Leevi 2015.
 * All rights reserved.
 */
package Metronomi.GUI;

import Metronomi.Control.KeyboardListener;
import Metronomi.Logic.Metronome;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

/**
 * UserInterface creates the program's UI and links the buttons with control
 * component.
 * 
 * @author Leevi
 * 
 * @see Metronomi.Control.KeyboardListener
 */
public class UserInterface implements Runnable {

  private JFrame frame;
  private final JTextField displayTempo;
  private final Metronome metronome;
  private final KeyboardListener controller;

  /**
   *
   * @param metronome
   */
  public UserInterface(Metronome metronome) {
    this.metronome = metronome;
    this.displayTempo = new JTextField("Current tempo: " + metronome.getBpm() + " BPM");
    this.displayTempo.setDisabledTextColor(Color.black);
    this.controller = new KeyboardListener(displayTempo, metronome);
  }

  /**
   * run creates buttons & labels, and adds them to the UI frame.
   */
  @Override
  public void run() {
    frame = new JFrame("Digital Metronome");
    frame.setPreferredSize(new Dimension(340, 340));
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    addComponents(frame.getContentPane());

    frame.pack();
    frame.setVisible(true);
  }

  private void addComponents(Container container) {
    displayTempo.setPreferredSize(new Dimension(340, 60));
    displayTempo.setEnabled(false);
    container.add(displayTempo, BorderLayout.NORTH);

    JPanel buttons = new JPanel(new GridLayout(4, 1));

    JButton onoff = new JButton("on/off");
    onoff.addActionListener(controller);
    onoff.setActionCommand("onoff");
    buttons.add(onoff);

    JButton faster = new JButton("+");
    faster.addActionListener(controller);
    faster.setActionCommand("faster");
    buttons.add(faster);

    JButton slower = new JButton("-");
    slower.addActionListener(controller);
    slower.setActionCommand("slower");
    buttons.add(slower);
    
    JButton eightify = new JButton("1/4 -> 1/8");
    eightify.addActionListener(controller);
    eightify.setActionCommand("eightify");
    buttons.add(eightify);

    container.add(buttons);

    JPanel signatures = new JPanel(new GridLayout(4, 1));

    JButton threeQuarters = new JButton("3/4");
    threeQuarters.addActionListener(controller);
    threeQuarters.setActionCommand("3/4");
    signatures.add(threeQuarters);

    JButton fourQuarters = new JButton("4/4");
    fourQuarters.addActionListener(controller);
    fourQuarters.setActionCommand("4/4");
    signatures.add(fourQuarters);

    JButton fiveQuarters = new JButton("5/4");
    fiveQuarters.addActionListener(controller);
    fiveQuarters.setActionCommand("5/4");
    signatures.add(fiveQuarters);

    JButton sevenQuarters = new JButton("7/4");
    sevenQuarters.addActionListener(controller);
    sevenQuarters.setActionCommand("7/4");
    signatures.add(sevenQuarters);

    container.add(signatures, BorderLayout.EAST);
  }

  /**
   *
   * @return frame
   */
  public JFrame getFrame() {
    return frame;
  }

  /**
   *
   * @return metronome
   */
  public Metronome getMetronome() {
    return metronome;
  }
}
