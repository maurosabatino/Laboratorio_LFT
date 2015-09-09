package Esercizio2.es2_4;
/**
 * Aggiungere alla classe DFA un metodo complete che ritorna true
 * se la funzione di transizione di un DFA `e definita per tutti gli stati dell’automa e i simboli del
 * suo alfabeto di riferimento, false altrimenti.
 */

import DeterministicFiniteAutomaton.DFA;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CompleteDfaTest {

	DFA complete;
	DFA incomplete;
	@Before
	public  void setUpComplete() throws Exception {
		complete = new DFA(2);

		complete.addFinalState(1);

		complete.setMove(0, '1', 0);
		complete.setMove(0, '0', 1);
		complete.setMove(1, '1', 1);
		complete.setMove(1, '0', 1);
	}

	@Before
	public void setUpIncomplete() throws Exception {
		incomplete = new DFA(2);

		incomplete.addFinalState(1);

		incomplete.setMove(0, '0', 1);
		incomplete.setMove(1, '1', 1);
		incomplete.setMove(1, '0', 1);
	}

	@Test
	public void testComplete() throws Exception {
		assertTrue(complete.complete());
	}

	@Test
	public void testNotComplete() throws Exception {
		assertFalse(incomplete.complete());
	}
}