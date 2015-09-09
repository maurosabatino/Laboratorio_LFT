package Esercizio5.es5_2;

import NondeterministicFiniteAutomaton.NFA;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * TODO Esercizio 5.2 javadoc
 */
public class nthTest {

	@Test
	public void testNth() throws Exception {
		for (int i = 0; i < 10; i++) {
			NFA test = NFA.nth(i);
			System.out.println("NFA: " + test.numberOfStates() + "; DFA: " + test.dfa().numberOfStates() + "; MIN: " + test.dfa().minimize().numberOfStates());
		}
	}

	@Test
	public void testNFAtoDFA() throws Exception {
		for (int i = 0; i < 10; i++) {
			NFA test = NFA.nth(i);
			assertEquals((int) Math.pow(2, (i)), test.dfa().numberOfStates());
		}

	}
}
