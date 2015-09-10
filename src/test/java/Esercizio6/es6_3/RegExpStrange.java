package Esercizio6.es6_3;

import NondeterministicFiniteAutomaton.NFA;
import RegExp.RegExpChoice;
import RegExp.RegExpStar;
import RegExp.RegExpSymbol;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Legge tutte e sole le stringhe formate da 'a' o da 'b' in un qualsiasi ordine
 * e numero.
 */
public class RegExpStrange {
	@Test
	public void testStrange() throws Exception {
		NFA choiche =new RegExpStar(new RegExpChoice(new RegExpSymbol('a'), new RegExpSymbol('b'))).compile();
		assertTrue(choiche.dfa().minimize().scan("aaaaaaaa"));
		assertTrue(choiche.dfa().minimize().scan("bbbbbbbbb"));
		assertTrue(choiche.dfa().minimize().scan("ba"));
		assertTrue(choiche.dfa().minimize().scan("babbbaaaba"));
	}


}
