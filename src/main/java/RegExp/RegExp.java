package RegExp;

import NondeterministicFiniteAutomaton.NFA;

public interface RegExp {
    /**
     * @return
     */
    NFA compile();
}
