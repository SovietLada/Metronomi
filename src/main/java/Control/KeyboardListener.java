/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * Created by Leevi 2015.
 * All rights reserved.
 */
package Control;

/**
 *
 * @author Leevi
 */
import Logic.Metronome;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JTextField;

public class KeyboardListener implements KeyListener, ActionListener {

    private final Metronome metronome;
    private final JTextField textField;

    public KeyboardListener(JTextField textField, Metronome metronome) {
        this.metronome = metronome;
        this.textField = textField;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void keyPressed(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void keyReleased(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("onoff")) {
            if (metronome.isRunning()) {
                metronome.stop();
            }
            else {
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
        }
        else if (e.getActionCommand().equals("faster")) {
            if (metronome.getBpm() < 180) {
                metronome.setBpm(metronome.getBpm() + Metronome.tempoStep);
                textField.setText("Current tempo: " + metronome.getBpm() + " BPM");
            }
        }
        else if (e.getActionCommand().equals("slower")) {
            if (metronome.getBpm() > 20) {
                metronome.setBpm(metronome.getBpm() - Metronome.tempoStep);
                textField.setText("Current tempo: " + metronome.getBpm() + " BPM");
            }
        }
    }
}
