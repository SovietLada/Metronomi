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
public class Metronome {

    // TODO: signatures 3/4, 5/4, 7/4
    // TODO: quarters to eights selection, i.e delay = delay / 2
    private final Rod rod;
    private int bpm;
    private long delay;
    private boolean isRunning;
    private int quarters;
    public static int tempoStep = 4;

    public Metronome() {
        this.rod = new Rod();
        this.bpm = 80;
        this.quarters = 1;
        setDelay();

        this.isRunning = false;
    }

    public void start() {
        isRunning = true;
        while (isRunning) {
            swing();
        }
        quarters = 1;
    }

    public void stop() {
        isRunning = false;
    }

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
        if (quarters > 4) {
            quarters = 1;
        }
        try {
            Thread.sleep(delay);
        }
        catch (InterruptedException e) {
        }
    }

    public Rod getRod() {
        return rod;
    }

    public int getBpm() {
        return bpm;
    }

    public void setBpm(int bpm) {
        this.bpm = bpm;
        setDelay();
    }

    public long getDelay() {
        return delay;
    }

    private void setDelay() {
        double a = Math.round((double) 60 / bpm * 1000);
        long b = (long) a;
        delay = b;
    }

    public boolean isRunning() {
        return isRunning;
    }
}
