package Esercizio3.es3_1;

import DeterministicFiniteAutomaton.DFA;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class RaggiungibilitTest {
  DFA r;
  DFA empty;
	HashSet<Integer> raggiungibili;
	HashSet<Integer> pozzo;
	@Before
  public void setUpDFA() throws Exception {
    r = new DFA(5);
    r.setMove(0,'0',1);
    r.setMove(0,'1',2);
    r.setMove(1,'0',1);
    r.setMove(1,'1',3);
    r.setMove(2,'0',2);
    r.setMove(2,'1',2);
    r.setMove(3, '0', 3);
    r.setMove(3,'1', 3);
    r.setMove(4,'0', 3);
    r.setMove(4, '1', 2);
    r.addFinalState(3);
  }

	@Before
	public void setUpRaggiunibili() throws Exception {
		raggiungibili = new HashSet<Integer>();
		raggiungibili.add(2);
		raggiungibili.add(3);
		raggiungibili.add(4);
	}

	@Before
	public void setUpPozzo() throws Exception {
		pozzo = new HashSet<Integer>();
		pozzo.add(2);
	}

	@Before
	public void setUpEmpty() throws Exception {
		empty = new DFA(2);
		empty.setMove(0,'0',0);
		empty.setMove(0, '1', 1);
		empty.setMove(1,'1',1);

	}

	@Test
	public void testReach() throws Exception {
		assertEquals(r.reach(4), raggiungibili);

	}

	@Test
	public void testSink() throws Exception {
		assertEquals(r.sink(),pozzo);
	}

	@Test
	public void testEmpty() throws Exception {
		 assertFalse(empty.empty());
	}

}
