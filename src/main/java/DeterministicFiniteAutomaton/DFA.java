package DeterministicFiniteAutomaton;


import java.util.*;

/**
 * Un oggetto della classe DFA rappresenta un automa a stati finiti
 * deterministico
 */
public class DFA {
	/**
	 * Numero degli stati dell'automa. Ogni stato e` rappresentato da
	 * un numero interno non negativo, lo stato con indice 0 e` lo
	 * stato iniziale.
	 */
	private int numberOfStates;

	/**
	 * Insieme degli stati finali dell'automa.
	 */
	private HashSet<Integer> finalStates;

	/**
	 * Funzione di transizione dell'automa, rappresentata come una
	 * mappa da mosse a stati di arrivo.
	 */
	private HashMap<Move, Integer> transitions;

	/**
	 * Crea un DFA con un dato numero di stati.
	 *
	 * @param n Il numero di stati dell'automa.
	 */
	public DFA(int n) {
		numberOfStates = n;
		finalStates = new HashSet<Integer>();
		transitions = new HashMap<Move, Integer>();
	}

	/**
	 * Aggiunge uno stato all'automa.
	 *
	 * @return L'indice del nuovo stato creato
	 */
	public int newState() {
		return numberOfStates++;
	}

	/**
	 * Aggiunge una transizione all'automa.
	 *
	 * @param p  Lo stato di partenza della transizione.
	 * @param ch Il simbolo che etichetta la transizione.
	 * @param q  Lo stato di arrivo della transizione.
	 * @return <code>true</code> se lo stato di partenza e lo stato di
	 * arrivo sono validi, <code>false</code> altrimenti.
	 */
	public boolean setMove(int p, char ch, int q) {
		if (!validState(p) || !validState(q))
			return false;

		transitions.put(new Move(p, ch), q);
		return true;
	}

	/**
	 * Aggiunge uno stato finale.
	 *
	 * @param p Lo stato che si vuole aggiungere a quelli finali.
	 * @return <code>true</code> se lo stato e` valido,
	 * <code>false</code> altrimenti.
	 */
	public boolean addFinalState(int p) {
		if (validState(p)) {
			finalStates.add(p);
			return true;
		} else
			return false;
	}

	/**
	 * Determina se uno stato e` valido oppure no.
	 *
	 * @param p Lo stato da controllare.
	 * @return <code>true</code> se lo stato e` valido,
	 * <code>false</code> altrimenti.
	 * @see #numberOfStates
	 */
	public boolean validState(int p) {
		return (p >= 0 && p < numberOfStates);
	}

	/**
	 * Determina se uno stato e` finale oppure no.
	 *
	 * @param p Lo stato da controllare.
	 * @return <code>true</code> se lo stato e` finale,
	 * <code>false</code> altrimenti.
	 * @see #finalStates
	 */
	public boolean finalState(int p) {
		return finalStates.contains(p);
	}

	/**
	 * Restituisce il numero di stati dell'automa.
	 *
	 * @return Numero di stati.
	 */
	public int numberOfStates() {
		return numberOfStates;
	}

	/**
	 * Restituisce l'alfabeto dell'automa, ovvero l'insieme di simboli
	 * che compaiono come etichette delle transizioni dell'automa.
	 *
	 * @return L'alfabeto dell'automa.
	 */
	public HashSet<Character> alphabet() {
		HashSet<Character> alphabet = new HashSet<Character>();
		for (Move m : transitions.keySet())
			alphabet.add(m.ch);
		return alphabet;
	}

	/**
	 * Esegue una mossa dell'automa.
	 *
	 * @param p  Stato di partenza prima della transizione.
	 * @param ch Simbolo da riconoscere.
	 * @return Stato di arrivo dopo la transizione, oppure
	 * <code>-1</code> se l'automa non ha una transizione
	 * etichettata con <code>ch</code> dallo stato
	 * <code>p</code>.
	 */
	public int move(int p, char ch) {
		Move move = new Move(p, ch);
		if (transitions.containsKey(move))
			return transitions.get(move);
		else
			return -1;
	}

	/**
	 * Esercizio 2.2
	 * Verifica se una stringa e` riconosciuta dall'automa.
	 *
	 * @param s Stringa da riconoscere.
	 * @return <code>true</code> se la stringa e` stata riconosciuta,
	 * <code>false</code> altrimenti.
	 */
	public boolean scan(String s) {
		int i = 0;
		int state = 0;
		while (i < s.length()) {
			final char ch = s.charAt(i++);
			state = move(state, ch);//effettuo una transizione e finisco in un nuovo stato
		}
		return finalState(state);//se ci si trova in uno stato finale true altrimenti false.
	}

	/**
	 * Esercizio 2.4
	 * controlla se un dfa è completo: in ogni stato è definita una transizione per ogni simbolo dell'alfabeto
	 *
	 * @return true se la funzione di transizione di un DFA `e definita per tutti gli stati dell’automa e i simboli del
	 * suo alfabeto di riferimento, false altrimenti.
	 */
	public boolean complete() {
		boolean complete = true;
		HashSet alph = alphabet();//ottengo l'alfabeto del dfa
		int i = 0;
		while (complete && i < numberOfStates) {//ciclo finchè non trovo uno stato in cui non sia presente un simbolo dell'alfabeto
			for (Object c : alph) {
				if (move(i, (Character) c) == -1) complete = false;
			}
			i++;
		}
		return complete;
	}

	/**
	 * Esercizio 2.5
	 * Stampa una rappresentazione testuale dell'automa da
	 * visualizzare con <a href="http://www.graphviz.org">GraphViz</a>.
	 *
	 * @param name Nome dell'automa.
	 */
	public String toDOT(String name) {
		String dot = "digraph " + name + "{\n";

		dot += "rankdir=LR;\n";
		dot += "node [shape = doublecircle];\n";

		for (Integer i : finalStates) { //prendo gli stati finali perchè ho bisogno di segnarli con un cerchio doppio
			dot += "q" + i + ";\n";
		}

		dot += "node [shape = circle];\n";

		for (Move m : transitions.keySet()) {
			dot += "q" + m.start + " -> q" + transitions.get(m) + " [ label = \"" + m.ch + "\" ];\n";
			//q[stato di inizio mossa] -> q[valore associato alla mossa] [ label = \carattere di transizione
		}

		dot += "}";
		return dot;
	}

	/**
	 * Esercizio - 2.6
	 * Fornisce la rappresentazione in formato dot di un DFA ottimizzando la versione normale.
	 * l'ottimizzazione consiste nell'associare ad una transizione p-->q tutti i simboli che compaiono come etichette di
	 * tale transizione.	 *
	 * @param name nome dell'automa
	 * @return String rappresentazione in formato dot del DFA
	 */
	public String toDOTOptimized(String name) {
		String dot = "digraph " + name + "{\nrankdir=LR;\nnode [shape = doublecircle];\n";
		/**
		 * identifico gli stati finali con un cerchio doppio
		 */
		for (Integer fin : this.finalStates) {
			dot += "q" + fin + ";\n";
		}
		/**
		 * I nodi avranno la forma di un cerchio
		 */
		dot += "node [shape = circle];\n";
		/**
		 * Associa ad una mossa uno stato
		 */
		HashMap<Move, Integer>  tmp = new HashMap<Move, Integer>();
		HashSet<Character> result;
		/**
		 * cicla su tutte le mosse
		 */
		for (Move move : transitions.keySet()) {
			result = new HashSet<Character>();
			/**
			 * se mossa gia' analizzata, salta iterazione
			 */
			if (tmp.containsKey(move)) {
				continue;
			}
			result.add(move.ch);
			/**
			 * ri-cicla su tutte le mosse
			 */
			for (Move mv : transitions.keySet()) {
				/**
				 * cicla e confronta se ci sono delle corrispondenze tra stato
				 * di partenza e di arrivo, ho trovato una transizione con medesimo stato iniziale e finale
				 */
				if (move.start == mv.start && transitions.get(move) == transitions.get(mv)) {
					tmp.put(mv, transitions.get(mv));
					result.add(mv.ch);//aggiungo il carattere al risultato
				}
			}
			/**
			 * aggiunge la nuova transizione al grafo
			 */
			dot += "q" + move.start + " -> q" + transitions.get(move) + " [ label = \"";
			int j = 0;
			/**
			 * aggiungo i caratteri che ho trovato e li faccio comparire nell'elenco dei caratteri responsabili della transizione
			 */
			for (Character c : result) {
				if (j != 0) {
					dot += "," + c;
				} else {
					dot += c;
					j++;
				}
			}
			dot += "\" ];\n";
			tmp.put(move, transitions.get(move));
		}
		dot += "}";
		return dot;
	}


	/**
	 * Esercizio 2.7
	 * Stampa una classe Java con un metodo <code>scan</code> che implementa
	 * l'automa.
	 *
	 * @param name Nome della classe da generare.
	 */
	public String toJava(String name) {
		boolean scanFirst = false;
		String java = "public class " + name + "{ \n\n";

		java += "        public static boolean Scan (String s) { \n\n" +
						"            int state = 0; \n" +
						"            int i = 0; \n\n";

		java += "            while (state >=0 && i<s.length()){ \n" +
						"                final char ch = s.charAt(i++); \n\n" +
						"                switch (state) { \n";

		for (int j = 0; j < numberOfStates; j++) {
			java += "                    case " + j + ":\n";
			for (Move m : transitions.keySet()) {
				if (m.start == j && scanFirst == false) {
					java += "                    if (ch == " + m.ch + ")\n " +
									"                        state = " + transitions.get(m) + ";\n";
					scanFirst = true;
				} else if (m.start == j && scanFirst == true) {
					java += "                    else if (ch == " + m.ch + ")\n " +
									"                        state = " + transitions.get(m) + ";\n";
				}
			}
			java += "\n                    else state = -1;\n" +
							"                    break; \n\n";
			scanFirst = false;
		}

		java += "                }\n             }\n";

		for (Integer i : finalStates) {
			java += "        return state == " + i + "; \n    }\n\n";
		}

		java += "     public static void main(String [] args){\n" +
						"         System.out.println(Scan(args[0]) ? \"OK\" : \"NOPE\""
						+ ");\n" +
						"     }\n" +
						"}";

		return java;
	}


	/**
	 * Esercizio 3.1
	 * effettuo una visita in ampiezza del grafo a partire dal nodo q,
	 * per ottenere l'insieme degli stati raggiungibili da q
	 * @param q stato dell'automa
	 * @return HashSet insieme degli stati raggiungibili da q
	 */
	public HashSet<Integer> reach(int q) {
		HashSet<Integer> s = new HashSet<Integer>();
		Queue<Integer> c = new LinkedList<Integer>();
		boolean[] r = new boolean[numberOfStates()];
		/**
		 * Inizializzare il vettore in modo tale che tutti gli elementi con indice
		 * diverso da q siano false e l’elemento con indice q sia true.
		 */
		for (int i = 0; i < r.length; i++) {
			r[i] = i == q;
		}

		c.add(q);
		s.add(q);
		while (!c.isEmpty()) {
			int i = c.poll();
			for (Character ch : alphabet()) {
				int move = move(i, ch);
				if (move != -1 && !r[move]) {
					s.add(move);
					r[move] = true;
					c.add(move);
				}
			}
		}
		return s;
	}

	/**
	 * Esercizio 3.1
	 * il metodo trova l'insieme degli stati pozzo dell'automa
	 * Stato pozzo: stato che non ha un insieme di stati raggiungibili e non è uno stato finale
	 * @return insieme degli stati pozzo dell'automa
	 */
	public HashSet<Integer> sink() {
		HashSet<Integer> s = new HashSet<Integer>();
		for (int i = 0; i < numberOfStates(); i++) {
			if (reach(i).size() == 1 && !finalState(i)) s.add(i);
		}
		return s;
	}

	/**
	 * Esercizio 3.1
	 * testa se l'automa pu accettare il linguaggio vuoto.
	 *
	 * @return <code>true</code> se l'automa accetta il linguaggio vuoto <code>false</code> altrimenti.
	 */
	public boolean empty() {
		int count = 0;
		for (int i = 0; i < numberOfStates(); i++) {
			count += reach(i).size();
		}
		return count == numberOfStates;
	}


	/**
	 * Esercizio 3.2
	 * samples ritorna un insieme di stringhe campione
	 * accettate dall'automa, una per ogni stato finale dell'automa.
	 * @param input stato di partenza
	 * @return <code>HashSet<Integer> result </code> insieme degli stati raggiunti da input
	 */
	public HashSet<StateWithExample> samples(int input){
		HashSet<StateWithExample> result = new HashSet<StateWithExample>();
		if(numberOfStates==0) return result;

		// creo un array di booleani indicanti la raggiungibilità
		boolean[] r = new boolean[numberOfStates];

		//stringa di supporto con i caratteri
		String[] s = new String[numberOfStates];

		for(int i=0; i<numberOfStates; i++){    //tutto a false
			r[i] = false;
			s[i] = "";
		}
		r[input] = true;                        //lo stato di partenza è true

		//inizializzo le variabili del ciclo di analisi
		int i = 0;                      //stato analizzato
		boolean modificato = false;     //indica se sono state apportate modifiche

        /* Bisonga ciclare finchè si modifica qualcosa */
		do{
			if(r[i]){                                               //se r[i] è raggiungibile
				for(Move m:transitions.keySet()){                   //cicla tutte le mosse possibili
					if(m.start == i && !r[transitions.get(m)]){     //prendi quelle che partono dallo stato i e non le ho ancora visitate
						r[transitions.get(m)] = true;               //lo stato è raggiungibile
						s[transitions.get(m)] = s[i] + " " + m.ch;  //aggiungo il carattere alla stringa dello stato
						modificato = true;                          //ho effettuato una modifica
					}
				}
			}
			i++;
			//posso uscire solo se non ci sono più modifiche possibili, ovverro finchè non ho raggiunto ogni stato ragiungibile
			if(i==numberOfStates&&modificato==true){            //se sono alla fine e ho fatto modifiche
				modificato = false;                             //continua a ciclare
				i = 0;                                          //resettando l'indice
			}
		}
		while(i<numberOfStates);


		for(int j=0; j<r.length; j++){
			// se è finale ed è uno stato raggiungibile lo inserisco nel risultato con la stringa calcolata
			if(r[j] && finalStates.contains(j)){
				//creo un insieme di int(stato) e string associata
				result.add(new StateWithExample(j, s[j]));
			}
		}

		return result;
	}

	/**
	 * Classe di supporto a Samples
	 * formata da
	 * int -> stato
	 * example->Stringa che consente di raggiungere lo stato dallo stato iniziale
	 */
	private static class StateWithExample {

		private int state;
		private String example;

		public StateWithExample(int state, String example) {
			this.state = state;
			this.example = example;
		}

		@Override
		public String toString(){
			return "[state: " + state + "; example: \"" + example + "\"]";
		}
	}

	/**
	 * Esercizio 4.1
	 * dato un automa deterministico ne crea uno che accetta lo stesso linguaggio,
	 * ma con un numero di stati minimo.
	 * per fare ciò utilizza l'algoritmo denominato "riempi tabella",
	 * ovvero trova gli stati distignuibili
	 *
	 * @return DFA il dfa con un numero di stati minimo
	 */
	public DFA minimize() {
		boolean bool = true;// variabile booleana per indicare se ci sono ancora delle transizioni da analizzare
		HashSet<Character> alfabeto = this.alphabet();
		Iterator<Character> itAlfabeto;


		/**
		 * 1 Allocare una matrice eq di elementi di tipo boolean e dimensioni
		 * n x n, dove n e il numero di stati dell'automa
		 */
		boolean eq[][] = new boolean[numberOfStates][numberOfStates];


		/**
		 * 2 Inizializzare la matrice in modo tale che l’elemento eq[i][j] sia true
		 * se i e j sono entrambi finali o entrambi non finali, false altrimenti.
		 */
		for (int i = 0; i < numberOfStates; i++) {
			for (int j = 0; j < numberOfStates; j++) {
				eq[i][j] = finalState(i)==finalState(j);//parto con la selezione degli stati finali, che di sicuro sono indistinguibili
			}
		}


		/**
		 * 3 Per ogni coppia di stati i e j ed ogni carattere ch tali che eq[i][j] è
		 * true ed eq[move(i,ch)][move(j,ch)] è false, si pone l'elemento eq[i][j] a false.
		 */
		while (bool) {
			bool = false;
			for (int i = 0; i < numberOfStates; i++) {
				for (int j = 0; j < numberOfStates; j++) {
					if (eq[i][j] == true){//abbiamo trovato uno stato inditinguibile
						itAlfabeto = alfabeto.iterator();
						while (itAlfabeto.hasNext()) {//ciclo finchè non trovo un indistinguibile
							char ch = itAlfabeto.next();
							if (eq[move(i, ch)][move(j, ch)] == false) {
								eq[i][j] = false;
								bool = true;
							}
						}
					}
				}
			}
		}


		/**
		 * 4 Ripetere il passo precedente fintantoche vengono scoperte nuove ´
		 * coppie di stati distinguibili:
		 * A tal fine è stato aggiunto il while esterno
		 */



		/**
		 * 5 Allocare un vettore m di elementi di tipo int e dimensione n e inizializzarlo
		 * in modo tale che l'elemento i-esimo sia lo stato indistinguibile da
		 * i con indice piu piccolo.
		 * vado a creare le classi di equivalenza
		 */
		int[] m = new int[numberOfStates];
		int k = -1;
		for (int i = 0; i < numberOfStates; i++) {
			for (int j = 0; j < numberOfStates; j++) {
				// prende la prima j sulla colonna che, incrociato con i, ha valore true(indistinguibile)
				if (eq[i][j]) {
					m[i] = j; //nell'array di costruzione mettiamo j nella posizione i
					if (j > k) {
						k = j; //num. degli stati che mi servono per costruire l'automa minimo
					}
					break;
				}
			}
		}

		/**
		 * 6 Sia k l'elemento piu grande del vettore m. Allocare e inizializzare
		 * un DFA B con k + 1 stati e tale che per ogni transizione da i a j
		 * etichettata ch in A esiste una transizione da m[i] a m[j] etichettata chin B.
		 * Fare in modo che, se i è finale in A, allora m[i] sia finale in B.
		 * Ora siamo giunti alla fine e siamo pronti per costruire l'automa minimo
		 */
		DFA b = new DFA(k + 1);
		Move move;
		for (int i = 0; i < numberOfStates; i++) {                  //per ogni transazione da i
			itAlfabeto = alfabeto.iterator();
			while (itAlfabeto.hasNext()) {
				char ch = itAlfabeto.next();                        //etichettata ch
				move = new Move(i, ch);
				if (transitions.get(move) != null) {                //(se esiste)
					b.setMove(m[i], ch, m[transitions.get(move)]);  //aggiungo una transazione da m[i] a m[j] etichetata ch
					if (this.finalState(i)) {                       //se è finale
						b.addFinalState(m[i]);                      //lo imposto tale
					}
				}
			}
		}

		return b;
	}

	/**
	 * Esercizio 4.2
	 * dopo aver effettuato la minimizzazione dei dfa, se gli stati iniziali sono indistinguibili,
	 * allora i due automi sono equivalenti
	 * @param target automa sul quale si vuole effettuare il test di equivalenza
	 * @return <code>True</code> se l'automa passato come parametro è eqivalente all'automa utilizzato <code>false</code> altrimenti
	 *
	 */
	public boolean equivalentTo(DFA target) {
		DFA a = this.minimize(); //l'automa minimp è equivalente all'automa di partenza
		DFA b = target.minimize();
		return     a.numberOfStates==b.numberOfStates
						&& a.finalStates.equals(b.finalStates)
						&& a.transitions.equals(b.transitions);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		DFA dfa = (DFA) o;
		return Objects.equals(numberOfStates, dfa.numberOfStates) &&
						Objects.equals(finalStates, dfa.finalStates) &&
						Objects.equals(transitions, dfa.transitions);
	}

	@Override
	public int hashCode() {
		return Objects.hash(numberOfStates, finalStates, transitions);
	}
}

