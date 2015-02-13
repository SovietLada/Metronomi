package Control;

import Metronomi.Control.KeyboardListener;
import Metronomi.Logic.Metronome;
import javax.swing.JTextField;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * ControlTest tests Control package.
 * 
 * @author Leevi
 */
public class ControlTest {

  JTextField field;
  Metronome metronome;
  KeyboardListener listener;

  public ControlTest() {
    field = new JTextField("");
    metronome = new Metronome();
    listener = new KeyboardListener(field, metronome);
  }

  @BeforeClass
  public static void setUpClass() {
  }

  @AfterClass
  public static void tearDownClass() {
  }

  @Before
  public void setUp() {
  }

  @After
  public void tearDown() {
  }

  @Test
  public void testInitialState() {
    assertEquals(true, listener.getMetronome() != null);
    assertEquals(true, listener.getMetronome().getBpm() == 100);
  }
  
  @Test
  public void testRod() {
    assertEquals(true, listener.getMetronome().getRod() != null);
  }
  
  @Test
  public void testTempo1() {
    listener.incrementTempo();
    assertEquals(true, listener.getMetronome().getBpm() > 100);
  }
  
  @Test
  public void testTempo2() {
    listener.decrementTempo();
    listener.decrementTempo();
    assertEquals(true, listener.getMetronome().getBpm() < 100);
  }
  
  @Test
  public void testTempo3() {
    int a = listener.getMetronome().getBpm();
    for (int i = 0; i < 10; ++i) {
      listener.incrementTempo();
    }
    for (int i = 0; i < 10; ++i) {
      listener.decrementTempo();
    }
    assertEquals(true, listener.getMetronome().getBpm() == a);
  }
  
  @Test
  public void testStarting() {
    listener.startMetronome();
    try {
      Thread.sleep(3000);
      assertEquals(true, listener.getMetronome().isRunning());
    }
    catch (InterruptedException e) {
      System.err.println(e);
    }
  }
  
  @Test 
  public void testStopping() {
    listener.stopMetronome();
    try {
      Thread.sleep(3000);
      assertEquals(false, listener.getMetronome().isRunning());
    }
    catch (InterruptedException e) {
      System.err.println(e);
    }
  }
}
