import java.util.Scanner;
import java.lang.Math;

public class rotX {
    public static final char[] LETTERSMIN = "aáàäbcçdeéèëfghiíìïjklmnñoóòöpqrstuúùüvwxyz".toCharArray();
    public static final char[] LETTERSMAYUS = "AÁÀÄBCÇDEÉÈËFGHIÍÌÏJKLMNÑOÓÒÖPQRSTUÚÙÜVWXYZ".toCharArray();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("E (encrypth), D (decode), F (force)");
        String option = scanner.nextLine().toLowerCase();
        System.out.println("Write the phrase");
        String phrase = scanner.nextLine();
        System.out.println("Write the number of positions you want to move");
        int movements = scanner.nextInt();
        String output;
        scanner.close();
        switch (option) {
            case "d":
                output = desxifraRotX(phrase, movements);
                System.out.println(output);
                break;
            case "e":
                output = xifraRotX(phrase, movements);
                System.out.println(output);
                break;
            case "f":
                forcaBrutaRotX(phrase);
                break;
            default:
                output = "Error";
                System.out.println(output);
        }
        
    }

    public static char encrypthLetter(char letter, int desplaçament, boolean decode) {
        if (decode == false) {
            for (int i = 0; i < LETTERSMAYUS.length; i++) {
                if (letter == LETTERSMAYUS[i]) return LETTERSMAYUS[(i + desplaçament) % LETTERSMAYUS.length];
            }
            for (int i = 0; i < LETTERSMIN.length; i++) {
                if (letter == LETTERSMIN[i]) return LETTERSMIN[(i + desplaçament) % LETTERSMAYUS.length];
            }
            return letter;
        } else {
            for (int i = 0; i < LETTERSMAYUS.length; i++) {
                if (letter == LETTERSMAYUS[i]) return LETTERSMAYUS[(i - desplaçament) % LETTERSMAYUS.length < 0 ? LETTERSMAYUS.length - Math.abs(desplaçament - i) : (i - desplaçament) % LETTERSMAYUS.length];
            }
            for (int i = 0; i < LETTERSMIN.length; i++) {
                if (letter == LETTERSMIN[i]) return LETTERSMIN[(i - desplaçament) % LETTERSMIN.length < 0 ? LETTERSMIN.length - Math.abs(desplaçament - i) : (i - desplaçament) % LETTERSMIN.length];
            }
            return letter;
        }
    }

    public static String xifraRotX(String cadena, int desplaçament) {
        StringBuffer encryptString = new StringBuffer();
        for (int i = 0; i < cadena.length(); i++ ) {
            char letterEncrypt = encrypthLetter(cadena.charAt(i), desplaçament, false);
            encryptString.append(letterEncrypt);
        }

        return encryptString.toString();
        
    }

    public static String desxifraRotX(String cadena, int desplaçament) {
        StringBuffer decodeString = new StringBuffer();
        for (int i = 0; i < cadena.length(); i++ ) {
            char letterEncrypt = encrypthLetter(cadena.charAt(i), desplaçament, true);
            decodeString.append(letterEncrypt);
        }

        return decodeString.toString();
    }

    public static void forcaBrutaRotX(String cadenaXifrada) {
        for (int i = 0; i < LETTERSMAYUS.length; i++) {
            System.out.println(desxifraRotX(cadenaXifrada, i));
        }
    }
    
}
