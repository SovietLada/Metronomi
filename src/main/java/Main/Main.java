/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * Created by Leevi 2015.
 * All rights reserved.
 */
package Main;

/**
 *
 * @author Leevi
 */
import GUI.UserInterface;
import Logic.Metronome;
import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {
        UserInterface a = new UserInterface(new Metronome());
        SwingUtilities.invokeLater(a);
    }
}
