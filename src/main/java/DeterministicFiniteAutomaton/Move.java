package DeterministicFiniteAutomaton;

public class Move {
    final int start;
    final char ch;

    public Move(int start, char ch) {
        this.start = start;
        this.ch = ch;
    }

    public boolean equals(Object o){
        if(o instanceof Move){
            Move m = (Move)o;
            return (start == m.start && ch == m.ch);
        }else return false;
    }
    public int hashCode(){
        return start ^ (int)ch;
    }

}

