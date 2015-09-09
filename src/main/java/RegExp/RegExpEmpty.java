package RegExp;

import NondeterministicFiniteAutomaton.NFA;

public class RegExpEmpty implements RegExp{


    public RegExpEmpty(){

    }

    public NFA compile() {
        NFA a = new NFA(0);
        return a;
    }

}
