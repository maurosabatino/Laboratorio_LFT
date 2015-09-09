package Esercizio4.es4_1;

import DeterministicFiniteAutomaton.DFA;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * TODO Esercizio 4.1 migliorare la suite di test e javadoc
 * Aggiungere un metodo minimize alla classe DFA che implementa l’algoritmo di
 * minimizzazione (Tabella 2). Verificare il corretto funzionamento del metodo minimize su alcuni
 * DFA appositamente costruiti. Nota: l’input dell’algoritmo in Tabella 2 `e l’istanza di DFA su cui
 * minimize viene invocato. In altri termini, minimize `e un metodo di DFA senza argomenti.
 */
public class MinimizeTest {

	DFA unoFinale;
	DFA unoFinaleMinimized;

	@Before
	public void setUpUnoFinale() throws Exception {
		unoFinale = new DFA(4);

		unoFinale.addFinalState(1);
		unoFinale.addFinalState(3);

		unoFinale.setMove(0, '0', 0);
		unoFinale.setMove(0, '1', 1);
		unoFinale.setMove(1, '1', 1);
		unoFinale.setMove(1, '0', 2);
		unoFinale.setMove(2, '1', 3);
		unoFinale.setMove(2, '0', 2);
		unoFinale.setMove(3, '1', 3);
		unoFinale.setMove(3, '0', 2);
	}

	@Before
	public void setUpUnoFinaleMinimized() throws Exception {
		unoFinaleMinimized = new DFA(2);
		unoFinaleMinimized.addFinalState(1);
		unoFinaleMinimized.setMove(0, '0', 0);
		unoFinaleMinimized.setMove(0, '1', 1);
		unoFinaleMinimized.setMove(1, '0', 0);
		unoFinaleMinimized.setMove(1,'1',1);

	}

	@Test
	public void testMinimizedUnoFinale() throws Exception {
		assertEquals(unoFinale.minimize(),unoFinaleMinimized);

	}

	public static void main(String[]args) {
		  DFA in2 = new DFA(5);
	    in2.addFinalState(0);
	    in2.addFinalState(4);

	    in2.setMove(3, 'a', 3);
	    in2.setMove(3, 'b', 3);
	    in2.setMove(0, 'a', 2);
	    in2.setMove(0, 'b', 1);
	    in2.setMove(1, 'a', 3);
	    in2.setMove(1, 'b', 0);
	    in2.setMove(2, 'a', 4);
	    in2.setMove(2, 'b', 3);
	    in2.setMove(4, 'a', 2);
	    in2.setMove(4, 'b', 1);

	    in2.toDOT("unoFinale");

	    DFA out2 = in2.minimize();
	    out2.toDOT("out2");


	    DFA A = new DFA(4);
        A.setMove(0,'0',0);
        A.setMove(0,'1',1);
        A.setMove(1,'1',1);
        A.setMove(1,'0',2);
        A.setMove(2,'0',2);
        A.setMove(2,'1',3);
        A.setMove(3,'0',2);
        A.setMove(3,'1',3);
        A.addFinalState(1);
        A.addFinalState(3);
        
        DFA min = A.minimize();
        System.out.println(min.numberOfStates());
    }
}
