package Esercizio1.es1_3;


public class VirgolaMobileSpazi {
    public static boolean scan(String s) {
        int state = 0;
        int i = 0;
        while (state >= 0 && i < s.length()) {
            final char ch = s.charAt(i++);
            switch (state) {
                case 0:
                    if (ch == '.') {
                        state = 2;
                    } else if (ch == '0' || ch == '1' || ch == '2' || ch == '3' || ch == '4' || ch == '5' || ch == '6' || ch == '7' || ch == '8' || ch == '9') {
                        state = 1;
                    } else if(ch == '+'|| ch == '-'){
                        state = 1;
                    } else if(ch==' '){
                       state = 0;
                    } else {
                        state = -1;
                    }
                    break;
                case 1:
                    if (ch == '0' || ch == '1' || ch == '2' || ch == '3' || ch == '4' || ch == '5' || ch == '6' || ch == '7' || ch == '8' || ch == '9') {
                        state = 1;
                    } else if (ch == '.') {
                        state = 2;
                    }else if(ch == 'e'){
                        state = 3;
                    }else if (ch==' '){
                        state = 5;
                    }else{
                        state = -1;
                    }
                    break;
                case 2:
                    if (ch == '0' || ch == '1' || ch == '2' || ch == '3' || ch == '4' || ch == '5' || ch == '6' || ch == '7' || ch == '8' || ch == '9') {
                        state = 2;
                    } else if( ch == 'e'){
                        state = 3;
                    } else if(ch==' '){
                        state = 5;
                    }else state = -1;
                    break;
                case 3:
                    if(ch == '+' || ch == '-'){
                        state = 4;
                    } else if(ch == '0' || ch == '1' || ch == '2' || ch == '3' || ch == '4' || ch == '5' || ch == '6' || ch == '7' || ch == '8' || ch == '9') {
                        state = 4;
                    } else state = -1;
                    break;
                case 4:
                    if(ch == '0' || ch == '1' || ch == '2' || ch == '3' || ch == '4' || ch == '5' || ch == '6' || ch == '7' || ch == '8' || ch == '9'){
                        state = 4;
                    }else if(ch == ' '){
                        state = 5;
                    }
                    else state = -1;
                    break;
                case 5:
                    if(ch==' ') state = 5;
                    else state =-1;
                    break;
            }
        }
        return state == 1 || state == 2 || state == 4 || state == 5;
    }
}
