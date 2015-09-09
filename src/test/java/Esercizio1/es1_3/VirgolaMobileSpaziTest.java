package Esercizio1.es1_3;

/**
 * Modificare l’automa dell’esercizio precedente in modo che riconosca
 * costanti numeriche precedute e/o seguite da sequenze eventualmente vuote di spazi.
 * Modificare l’implementazione Java dell’automa conseguentemente.
 */

import static org.junit.Assert.*;
public class VirgolaMobileSpaziTest {
    @org.junit.Test
    public void testScan() throws Exception {
        VirgolaMobileSpazi vm = new VirgolaMobileSpazi();

        assertTrue(VirgolaMobileSpazi.scan("        123      "));
        assertTrue(VirgolaMobileSpazi.scan("         125.5      "));
        assertTrue(VirgolaMobileSpazi.scan("     .567            "));
        assertTrue(VirgolaMobileSpazi.scan("      +7.5      "));
        assertTrue(VirgolaMobileSpazi.scan("         -.7        "));
        assertTrue(VirgolaMobileSpazi.scan("      67e10         "));
        assertTrue(VirgolaMobileSpazi.scan("       1e-2      "));
        assertTrue(VirgolaMobileSpazi.scan("        -.7e2              "));
    }
}
