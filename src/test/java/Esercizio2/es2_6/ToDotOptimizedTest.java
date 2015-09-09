package Esercizio2.es2_6;

import DeterministicFiniteAutomaton.DFA;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by mauro on 16/08/2015.
 */
public class ToDotOptimizedTest {
	DFA dfa;
	String expeted;
	@Before
	public void setUp() throws Exception {
		dfa = new DFA(4);

		//finali
		dfa.addFinalState(3);

		//0
		dfa.setMove(0, '0', 1);
		dfa.setMove(0, '1', 0);

		//1
		dfa.setMove(1, '0', 2);
		dfa.setMove(1, '1', 0);

		//2
		dfa.setMove(2, '0', 3);
		dfa.setMove(2, '1', 0);

		//3, questo è lo stato che risente dell'ottimizzazione
		dfa.setMove(3, '0', 3);
		dfa.setMove(3, '1', 3);
		expeted = "digraph automa{\n" +
						"rankdir=LR;\n" +
						"node [shape = doublecircle];\n" +
						"q3;\n" +
						"node [shape = circle];\n" +
						"q3 -> q3 [ label = \"1,0\" ];\n" +
						"q2 -> q0 [ label = \"1\" ];\n" +
						"q2 -> q3 [ label = \"0\" ];\n" +
						"q1 -> q2 [ label = \"0\" ];\n" +
						"q0 -> q0 [ label = \"1\" ];\n" +
						"q1 -> q0 [ label = \"1\" ];\n" +
						"q0 -> q1 [ label = \"0\" ];\n"+
						"}";
	}
	@Test
	public void testDotOptimized() throws Exception {
		assertEquals(expeted,dfa.toDOTOptimized("automa"));

	}
}
