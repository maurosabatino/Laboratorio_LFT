package RegExp;

import NondeterministicFiniteAutomaton.NFA;

/**
 * NOTA: Il metodo comile delega alle altre classi la realizzazione della scelta
 * e non richiama eslicitamente il 'new NFA()' in quanto in tutti i casi l'NFA
 * restituito dal metodo invcato è già correttamente formato: in questo modo si
 * evita di appesantire l'NFA finale riducendone gli stati e le epsilon-transizioni.
 *
 */
public class RegExpRange implements RegExp{
    //DA COMPLETARE 6.4
    private char s1;
    private char s2;

    public RegExpRange(char from, char to) {
        this.s1 = from;
        this.s2 = to;
    }

    public NFA compile() {
        if(s1>s2){                                          //nel caso l'ordine sia invertito scambio i char
            char t = s1;
            s1 = s2;
            s2 = t;
        }
        if((s1-s2) == 0){                                   //se from e to sono lo stesso simbolo
            return new RegExpSymbol(s2).compile();    //genero l'NFA contenente solo quel simbolo

        } else {                                            //altriemnti risolvo ricorsivamente
            char x = (char)(s1+1);                          //calcolo il carattere successivo
            return new RegExpChoice(                        //ritorno una nuova scelata formata
                    new RegExpSymbol(s1),             //dal primo simbolo e
                    new RegExpRange(x, s2)            //dal range che va da x a s2
            ).compile();
        }

    }

}
