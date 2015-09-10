package Esercizio6.es6_4;


import RegExp.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RangeTest {


	@Test
	public void testRange() throws Exception {
		assertEquals(new RegExpChoice(
										new RegExpChoice(
														new RegExpSymbol('1'),
														new RegExpSymbol('2')),
										new RegExpChoice(
														new RegExpSymbol('3'),
														new RegExpSymbol('4')
										)).compile().dfa().minimize().toDOT("range"),
		new RegExpRange('1','4').compile().dfa().minimize().toDOT("range"));


	}


}
