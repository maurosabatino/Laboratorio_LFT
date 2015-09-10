package RegExp;

import NondeterministicFiniteAutomaton.NFA;

/**
 * per rappresentare una foglia epsylon
 */
public class RegExpEpsylon implements RegExp {

public RegExpEpsylon(){

    }

    /**
     * Creazione di un NFA che ha una epsilon transizione
     * viene creato un NFA con 2 stati, lo stato iniziale (0)
     * ha una trasizione verso lo stato finale per mezzo di un epsilon input
     * @return NFA traduzione di una espressione regolare avente il simbolo epsilon
     */
    public NFA compile() {
        NFA a = new NFA(2);
        a.addMove(0,NFA.EPSILON, 1);
        a.addFinalState(1);
        return a;
    }

}
