package Metronomi.test.Logic;

import Metronomi.Logic.Metronome;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * RodTest tests Rod's content.
 * 
 * @author Leevi
 */
public class RodTest {

  Metronome metronome;

  public RodTest() {
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
  public void testRod1() {
    assertEquals("Click!", metronome.getRod().click());
  }

  @Test
  public void testRod2() {
    assertEquals("Accent!", metronome.getRod().accent());
  }
}
