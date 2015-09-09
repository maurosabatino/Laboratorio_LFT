package Esercizio2.es2_7;

import DeterministicFiniteAutomaton.DFA;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by mauro on 16/08/2015.
 */
public class ToJavaTest {
	DFA tz ;

	@Before
	public void setUp() throws Exception {
		tz 	= new DFA(4);
		tz.setMove(0, '1', 0);
		tz.setMove(0, '0', 1);
		tz.setMove(1, '1', 0);
		tz.setMove(1, '0', 2);
		tz.setMove(2, '1', 0);
		tz.setMove(2, '0', 3);
		tz.setMove(3, '1', 3);
		tz.setMove(3, '0', 3);
		tz.addFinalState(3);

	}

	@Test
	public void testToJava() throws Exception {
		String expected="public class TreZeri{ \n" +
						"\n" +
						"        public static boolean Scan (String s) { \n" +
						"\n" +
						"            int state = 0; \n" +
						"            int i = 0; \n" +
						"\n" +
						"            while (state >=0 && i<s.length()){ \n" +
						"                final char ch = s.charAt(i++); \n" +
						"\n" +
						"                switch (state) { \n" +
						"                    case 0:\n" +
						"                    if (ch == 0)\n" +
						"                         state = 1;\n" +
						"                    else if (ch == 1)\n" +
						"                         state = 0;\n" +
						"\n" +
						"                    else state = -1;\n" +
						"                    break; \n" +
						"\n" +
						"                    case 1:\n" +
						"                    if (ch == 1)\n" +
						"                         state = 0;\n" +
						"                    else if (ch == 0)\n" +
						"                         state = 2;\n" +
						"\n" +
						"                    else state = -1;\n" +
						"                    break; \n" +
						"\n" +
						"                    case 2:\n" +
						"                    if (ch == 0)\n" +
						"                         state = 3;\n" +
						"                    else if (ch == 1)\n" +
						"                         state = 0;\n" +
						"\n" +
						"                    else state = -1;\n" +
						"                    break; \n" +
						"\n" +
						"                    case 3:\n" +
						"                    if (ch == 1)\n" +
						"                         state = 3;\n" +
						"                    else if (ch == 0)\n" +
						"                         state = 3;\n" +
						"\n" +
						"                    else state = -1;\n" +
						"                    break; \n" +
						"\n" +
						"                }\n" +
						"             }\n" +
						"        return state == 3; \n" +
						"    }\n" +
						"\n" +
						"     public static void main(String [] args){\n" +
						"         System.out.println(Scan(args[0]) ? \"OK\" : \"NOPE\");\n" +
						"     }\n" +
						"}";
		assertEquals(expected,tz.toJava("TreZeri"));

	}
}
