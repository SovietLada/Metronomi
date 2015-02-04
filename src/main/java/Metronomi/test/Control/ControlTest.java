package Metronomi.test.Control;

import Metronomi.Control.KeyboardListener;
import Metronomi.Logic.Metronome;
import java.awt.event.ActionEvent;
import static junit.runner.Version.id;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

// @author Leevi
public class ControlTest {

  Metronome metronome;
  KeyboardListener listener;

  public ControlTest() {
    metronome = new Metronome();
    listener = new KeyboardListener(null, metronome);
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
      assertEquals(listener.getMetronome() != null, true);
      assertEquals(listener.getMetronome().getBpm() == 80, true);
  }
}
