package Esercizio2.es2_3;

import DeterministicFiniteAutomaton.DFA;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;


public class VirgolaMobileDFATest {
	DFA vm;
	@Before
	public void setUp() throws Exception {
		vm = new DFA(8);
		vm.addFinalState(2);
		vm.addFinalState(4);
		vm.addFinalState(7);

		//0
		for(char i='0'; i<='9'; i++)
			vm.setMove(0,i, 2);
		vm.setMove(0, '+', 1);
		vm.setMove(0, '-', 1);
		vm.setMove(0, '.', 3);

		//1
		for(char i='0'; i<='9'; i++)
			vm.setMove(1,i, 2);
		vm.setMove(1, '.', 3);

		//2
		for(char i='0'; i<='9'; i++)
			vm.setMove(2,i, 2);
		vm.setMove(2, '.', 3);
		vm.setMove(2, 'e', 5);

		//3
		for(char i='0'; i<='9'; i++)
			vm.setMove(3,i, 4);

		//4
		for(char i='0'; i<='9'; i++)
			vm.setMove(4,i, 4);
		vm.setMove(4, 'e', 5);

		//5
		for(char i='0'; i<='9'; i++)
			vm.setMove(5,i, 7);
		vm.setMove(5, '+', 6);
		vm.setMove(5, '-', 6);

		//6
		for(char i='0'; i<='9'; i++)
			vm.setMove(6,i, 7);

		//7
		for(char i='0'; i<='9'; i++)
			vm.setMove(7,i, 7);
	}

	@Test
	public void testScanAccepted() throws Exception {
		assertTrue(vm.scan("123"));
		assertTrue(vm.scan("125.5"));
		assertTrue(vm.scan("67e10"));
	}

	@Test
	public void testPoint() throws Exception {
		assertTrue(vm.scan(".567"));
	}

	@Test
	public void testMinus() throws Exception {
		assertTrue(vm.scan("-.7"));
		assertTrue(vm.scan("1e-2"));
		assertTrue(vm.scan("-.7e2"));
	}

	@Test
	public void testPlus() throws Exception {
		assertTrue(vm.scan("+7.5"));
	}
}
