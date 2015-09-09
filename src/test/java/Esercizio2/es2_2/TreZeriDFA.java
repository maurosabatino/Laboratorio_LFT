package Esercizio2.es2_2;

import DeterministicFiniteAutomaton.DFA;
import junit.framework.TestCase;

/**
 * Aggiungere alla classe DFA un metodo scan che accetta una stringa s e ritorna
 * true se s `e riconosciuta dall’automa, false altrimenti. Scrivere un semplice programma di
 * prova che crea una istanza della classe DFA, costruisce la rappresentazione del DFA mostrato in
 * Figura 1, e verifica se una stringa data in input (passata dalla linea di comando o letta da tastiera)
 * è o meno riconosciuta dall’automa.
 */
public class TreZeriDFA  extends TestCase{
	DFA treZeri;
	@Override
	public void setUp() throws Exception {
		super.setUp();
		treZeri = new DFA(4);
		treZeri.setMove(0,'1',0);
		treZeri.setMove(0,'0',1);
		treZeri.setMove(1,'1',0);
		treZeri.setMove(1,'0',2);
		treZeri.setMove(2,'1',0);
		treZeri.setMove(2,'0',3);
		treZeri.setMove(3, '1', 3);
		treZeri.setMove(3, '0', 3);
		treZeri.addFinalState(3);
	}

	public void testScanAccepted() throws Exception {
		assertTrue(treZeri.scan("000"));
		assertTrue(treZeri.scan("000111"));
	}

	public void testScanNotAccepted() throws Exception {
		assertFalse(treZeri.scan("0011110"));
		assertFalse(treZeri.scan("010101"));
		assertFalse(treZeri.scan("10214"));
	}

}
