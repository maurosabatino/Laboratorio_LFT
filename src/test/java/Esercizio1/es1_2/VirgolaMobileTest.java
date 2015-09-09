package Esercizio1.es1_2;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class VirgolaMobileTest {

	VirgolaMobile vm;

	@Before
	public void setUp() throws Exception {
		vm = new VirgolaMobile();

	}

	@Test
	public void testScanAccepted() throws Exception {
		assertTrue(VirgolaMobile.scan("123"));
		assertTrue(VirgolaMobile.scan("125.5"));
		assertTrue(VirgolaMobile.scan("67e10"));
	}

	@Test
	public void testPoint() throws Exception {
		assertTrue(VirgolaMobile.scan(".567"));
	}

	@Test
	public void testMinus() throws Exception {
		assertTrue(VirgolaMobile.scan("-.7"));
		assertTrue(VirgolaMobile.scan("1e-2"));
		assertTrue(VirgolaMobile.scan("-.7e2"));
	}

	@Test
	public void testPlus() throws Exception {
		assertTrue(VirgolaMobile.scan("+7.5"));
	}
}