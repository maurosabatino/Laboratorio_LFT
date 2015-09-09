package Esercizio6.es6_2;

import DeterministicFiniteAutomaton.DFA;
import NondeterministicFiniteAutomaton.NFA;
import RegExp.RegExpChoice;
import RegExp.RegExpSequence;
import RegExp.RegExpStar;
import RegExp.RegExpSymbol;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 *
 */
public class RegExp6_2 {

	NFA regex;
	/**
	 *      alfabeto = {/,*,c}
	 *
	 *  //    /.*.(({*}*)U({c}+.{/}*))*.*./
 	 */
	@Before
	public void setUpRegexp() {
		// /*
		RegExpSequence seq1=new RegExpSequence(new RegExpSymbol('/'),new RegExpSymbol('*'));
		/// /////////////////////////////
		// {c}+
		RegExpSequence seqt1=new RegExpSequence(new RegExpSymbol('c'),new RegExpStar(new RegExpSymbol('c')));
		// {/}*
		RegExpStar star1=new RegExpStar(new RegExpSymbol('/'));
		// {c}+.{/}*
		RegExpSequence seqt2=new RegExpSequence(seqt1,star1);
		// {*}*
		RegExpStar star2=new RegExpStar(new RegExpSymbol('*'));
		// {*}*U({c}+.{/}*)*
		RegExpStar choice=new RegExpStar(new RegExpChoice(star2,seqt2));
		/// /////////////////////////////
		// /*.{*}*U({c}+{/}*)
		RegExpSequence seq2=new RegExpSequence(seq1,choice);
		// */
		RegExpSequence seq3=new RegExpSequence(new RegExpSymbol('*'),new RegExpSymbol('/'));
		// /.*.(({*}*)U({c}+.{/}*))*.*./
		RegExpSequence re=new RegExpSequence(seq2,seq3);

		//regexp to nfa
		regex = re.compile();
	}

	@Test
	public void testAccept() throws Exception {
		DFA minimized = regex.dfa().minimize();
		assertTrue(minimized.scan("/****/"));
		assertTrue(minimized.scan("/*c*c*/"));
		assertFalse(minimized.scan("/*/"));
		assertFalse(minimized.scan("/**/**/"));
	}

	@Test
	public void testNFA() throws Exception {


	}

	@Test
	public void testDFA() throws Exception {


	}

	@Test
	public void testDFAMin() throws Exception {


	}
}
