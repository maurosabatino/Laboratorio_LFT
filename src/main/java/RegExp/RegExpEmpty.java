package RegExp;

import NondeterministicFiniteAutomaton.NFA;

/**
 * per rappresentare una foglia vuota
 */
public class RegExpEmpty implements RegExp{


    public RegExpEmpty(){

    }

    public NFA compile() {
        NFA a = new NFA(0);
        return a;
    }

}
