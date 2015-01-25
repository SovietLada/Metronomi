/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * Created by Leevi 2015.
 * All rights reserved.
 */
package GUI;

/**
 *
 * @author Leevi
 */
import Control.KeyboardListener;
import Logic.Metronome;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class UserInterface implements Runnable {

  private JFrame frame;
  private final JTextField displayTempo;
  private final Metronome metronome;
  private final KeyboardListener controller;

  public UserInterface(Metronome metronome) {
    this.metronome = metronome;
    this.displayTempo = new JTextField("Current tempo: " + metronome.getBpm() + " BPM");
    this.controller = new KeyboardListener(displayTempo, metronome);
  }

  @Override
  public void run() {
    frame = new JFrame("Digital Metronome");
    frame.setPreferredSize(new Dimension(300, 300));
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    addComponents(frame.getContentPane());

    frame.pack();
    frame.setVisible(true);
  }

  private void addComponents(Container container) {
    displayTempo.setPreferredSize(new Dimension(300, 50));
    displayTempo.setEnabled(false);
    container.add(displayTempo, BorderLayout.NORTH);

    JPanel buttons = new JPanel(new GridLayout(3, 1));

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

    container.add(buttons);
  }

  public JFrame getFrame() {
    return frame;
  }

  public Metronome getMetronome() {
    return metronome;
  }
}
