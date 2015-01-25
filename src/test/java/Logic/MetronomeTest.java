package Logic;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

// @author Leevi
public class MetronomeTest {

  Metronome metronome;

  public MetronomeTest() {
    metronome = new Metronome();
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
  public void testMetronome1() {
    assertEquals(false, metronome.isRunning());
  }

  @Test
  public void testMetronome2() {
    metronome.stop();
    assertEquals(false, metronome.isRunning());
  }

  @Test
  public void testMetronome3() {
    if (metronome.getRod() != null) {
      assertEquals(true, true);
    }
  }

  @Test
  public void testInitialDelay() {
    long a = 750;
    assertEquals(a, metronome.getDelay());
  }

  @Test
  public void testDelay1() {
    long a = 800;
    metronome.setBpm(75);
    assertEquals(a, metronome.getDelay());
  }

  @Test
  public void testDelay2() {
    long b = 667;
    metronome.setBpm(90);
    assertEquals(b, metronome.getDelay());
  }

  @Test
  public void testDelay3() {
    long c = 2609;
    metronome.setBpm(23);
    assertEquals(c, metronome.getDelay());
  }
}
