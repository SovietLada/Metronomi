/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * Created by Leevi 2015.
 * All rights reserved.
 */
package Metronomi.Metronomi;

/**
 * Project's main class.
 * 
 * @author Leevi
 */
import Metronomi.GUI.UserInterface;
import Metronomi.Logic.Metronome;
import javax.swing.SwingUtilities;

public class Main {

  public static void main(String[] args) {
    UserInterface a = new UserInterface(new Metronome());
    SwingUtilities.invokeLater(a);
  }
}
