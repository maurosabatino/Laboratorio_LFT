package Esercizio3.es3_2;

import DeterministicFiniteAutomaton.DFA;
import org.junit.Before;
import org.junit.Test;


public class SampleTest {
	DFA dfa;
	DFA vm;
	@Before
	public void setUpDFAtreZeri() throws Exception {
		dfa = new DFA(5);

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
		dfa.setMove(2, '1', 4);

		//3
		dfa.setMove(3, '0', 3);
		dfa.setMove(3, '1', 3);

	}

	@Before
	public void setUpDFAvirgolaMobile() throws Exception {
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
	public void testSampleTreZeri() throws Exception {
		System.out.println(dfa.samples(0));
	}

	@Test
	public void testSampleVirgolaMobile() throws Exception {
		System.out.println(vm.samples(0));
	}
}
