package Esercizio5.es5_1;

import NondeterministicFiniteAutomaton.NFA;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;

import static org.junit.Assert.assertEquals;

/**
 * TODO esercizio 5.1 altri test e javadoc
 * Implementare tutti i metodi incompleti della classe NFA mostrata nel Listato
 * 3. Per l'implementazione del metodo epsilonClosure fare riferimento
 * all'algoritmo in Tabella 3. Verificare il funzionamento del metodo dfa su
 * alcune istanze di NFA rappresentanti automi non deterministici, alcuni con
 * ed altri senza epsilon-transizioni.
 */

public class EpsilonClosureTest {

	NFA automata;
	HashSet<Integer> eClosed;
    @Before
    public void setUpNFA() throws Exception {
	    automata = new NFA(7);
	    automata.addMove(0, '\0', 1);
	    automata.addMove(1, '\0', 2);
	    automata.addMove(2, '\0', 5);
	    automata.addMove(0, '\0', 3);
	    automata.addMove(0, '0', 4);
	    automata.addMove(4, '1', 5);
	    automata.addMove(4, '\0', 6);
	    automata.addFinalState(6);

	    eClosed = new HashSet<Integer>();
	    eClosed.add(0);
	    eClosed.add(1);
	    eClosed.add(2);
	    eClosed.add(3);
	    eClosed.add(5);
    }

	@Test
	public void testEspilonClosure() throws Exception {
		assertEquals(automata.epsilonClosure(0),eClosed);

	}
}
