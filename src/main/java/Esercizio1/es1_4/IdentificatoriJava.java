package Esercizio1.es1_4;


public class IdentificatoriJava {
    public static boolean scan (String s ){
        int state = 0;
        int i = 0;
        while(state>=0 && i <s.length()){
            final char ch = s.charAt(i++);
            switch (state){
                case 0:
                    if (ch =='_') state = 1;
                    else state = -1;
                    break;
                case 1:
                    if(ch=='_') state = 2;
                    else if(Character.isDigit(ch) || Character.isAlphabetic(ch))
                        state = 2;
                    else state = -1;
                    break;
                case 2 :
                    if(ch=='_') state = 2;
                    else if(Character.isDigit(ch) || Character.isAlphabetic(ch))
                        state = 2;
                    else state = -1;
                    break;
            }
        }

        return state==2;

    }
}
