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

	@Test
	public void testCostatntiNumeriche() throws Exception {
		System.out.println(new RegExpSequence(
						new RegExpChoice(
										new RegExpChoice(
														new RegExpSymbol('+'),
														new RegExpSymbol('-')
										),
										new RegExpEpsylon()
						),
						new RegExpSequence(
										new RegExpChoice(
														new RegExpEpsylon(),
														new RegExpSequence(
																		new RegExpSequence(
																						new RegExpRange('0','9'),
																						new RegExpStar(
																										new RegExpRange('0','9')
																						)
																		),
																		new RegExpChoice(
																						new RegExpSequence(
																										new RegExpSymbol('e'),
																										new RegExpSequence(
																														new RegExpChoice(
																																		new RegExpChoice(
																																						new RegExpSymbol('+'),
																																						new RegExpSymbol('-')
																																		),
																																		new RegExpEpsylon()
																														),
																														new RegExpSequence(
																																		new RegExpRange('0','9'),
																																		new RegExpStar(
																																						new RegExpRange('0','9')
																																		)
																														)
																										)
																						),
																						new RegExpEpsylon()
																		)
														)
										),
										new RegExpSequence(
														new RegExpSymbol('.'),
														new RegExpSequence(
																		new RegExpRange('0','9'),
																		new RegExpChoice(
																						new RegExpEpsylon(),
																						new RegExpSequence(
																										new RegExpStar(
																														new RegExpRange('0','9')
																										),
																										new RegExpSequence(
																														new RegExpSymbol('e'),
																														new RegExpSequence(
																																		new RegExpChoice(
																																						new RegExpChoice(
																																										new RegExpSymbol('+'),
																																										new RegExpSymbol('-')
																																						),
																																						new RegExpEpsylon()
																																		),
																																		new RegExpSequence(
																																						new RegExpRange('0','9'),
																																						new RegExpStar(
																																										new RegExpRange('0','9')
																																						)
																																		)
																														)
																										)
																						)
																		)
														)
										)
						)
		).compile().dfa().minimize().toDOT("Number"));

	}
}
