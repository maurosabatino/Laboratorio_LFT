package Esercizio6.es6_1;

import NondeterministicFiniteAutomaton.NFA;
import RegExp.*;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Implementare le classi RegExpEpsilon, RegExpEmpty, RegExpChoice, RegExpStar
 * seguendo la traccia data qui sopra. Verificare il corretto funzionamento del metodo compile producendo
 * l’"-NFA corrispondente a un insieme ragionevole di espressioni regolari di complessit`a
 * crescente.
 */
public class RegExpTest {
	NFA epsilon;
	NFA empty;
	NFA choiche;
	NFA star;


	@Test
	public void testEpsilon() throws Exception {
		epsilon = new RegExpEpsylon().compile();
		assertTrue(epsilon.dfa().minimize().scan(""+NFA.EPSILON));
		epsilon = new RegExpSequence(new RegExpSymbol('a'),new RegExpEpsylon()).compile();
		System.out.print("epsiolon: \0");
		assertTrue(epsilon.dfa().minimize().scan(""));

	}

	@Test
	public void testEmpty() throws Exception {
	empty = new RegExpEmpty().compile();
		assertEquals(0,empty.numberOfStates());
	}

	@Test
	public void testChoice() throws Exception {
		/**
		 * (a+b)
		 */
		choiche = new RegExpChoice(
						new RegExpSymbol('a'), new RegExpSymbol('b')
		).compile();

		assertTrue(choiche.dfa().minimize().scan("a"));
		assertTrue(choiche.dfa().minimize().scan("b"));
		assertFalse(choiche.dfa().minimize().scan("ba"));

		/**
		 * espressione regolare : (a+b)*
		 *
		 */
		choiche = new RegExpStar(
						new RegExpChoice(
										new RegExpSymbol('a'), new RegExpSymbol('b')
						)
		).compile();

		assertTrue(choiche.dfa().minimize().scan("aaaaaaaa"));
		assertTrue(choiche.dfa().minimize().scan("bbbbbbbbb"));
		assertTrue(choiche.dfa().minimize().scan("ba"));
		assertTrue(choiche.dfa().minimize().scan("babbbaaaba"));

		choiche = new RegExpChoice(
						new RegExpSymbol('a'), new RegExpSymbol('b')
		).compile();


	}

	@Test
	public void testStar() throws Exception {
	/**
	 * espressione regolare : (a+b)*
	 *
	 */
		star = new RegExpStar(
							new RegExpChoice(
											new RegExpSymbol('a'), new RegExpSymbol('b')
							)
		).compile();

		assertTrue(star.dfa().minimize().scan("aaaaaaaa"));
		assertTrue(star.dfa().minimize().scan("bbbbbbbbb"));
		assertTrue(star.dfa().minimize().scan("ba"));
		assertTrue(star.dfa().minimize().scan("babbbaaaba"));
		assertTrue(star.dfa().minimize().scan(""));
		/**
		 * espressione regolare (a+b)*.b*
		 */
		star = new RegExpSequence(
						new RegExpStar(new RegExpChoice(new RegExpSymbol('a'), new RegExpSymbol('b'))),//(a+b)*
						new RegExpStar(new RegExpSymbol('c'))//c*

		).compile();
		assertTrue(star.dfa().minimize().scan("babbbaaabac"));
		assertFalse(star.dfa().minimize().scan("bcabbcbaaabac"));
		assertTrue(star.dfa().minimize().scan("ccccc"));
	}
}
