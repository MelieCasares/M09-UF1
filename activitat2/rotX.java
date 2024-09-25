import java.util.Scanner;

public class rotX {
    public static final char[] LETTERSMIN = "aáàäbcçdeéèëfghiíìïjklmnñoóòöpqrstuúùüvwxyz".toCharArray();
    public static final char[] LETTERSMAYUS = "AÁÀÄBCÇDEÉÈËFGHIÍÌÏJKLMNÑOÓÒÖPQRSTUÚÙÜVWXYZ".toCharArray();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("E (encrypth) or D (decode)");
        String option = scanner.nextLine().toLowerCase();
        System.out.println("Write the phrase");
        String phrase = scanner.nextLine();
        String output;
        scanner.close();
        switch (option) {
            case "d":
                output = desxifraRot13(phrase);
                break;
            case "e":
                output = xifraRot13(phrase);
                break;
            default:
                output = "Error";
        }
        
    }

    public static int arrayPos(char letter) {
        for (int i = 0; i < LETTERSMAYUS.length; i++) {
            if (letter == LETTERSMAYUS[i]) return i;
        }
        for (int i = 0; i < LETTERSMIN.length; i++) {
            if (letter == LETTERSMIN[i]) return i;
        }
        return -1;
    }

    public static String xifraRotX(String cadena, int desplaçament) {
        StringBuffer encryptString;
        for (int i = 0; i < cadena.length(); i++ ) {
            int letterPos = arrayPos(cadena.charAt(i));
            if (letterPos != -1) {
                char encrypthLetter = LETTERS[(letterPos + desplaçament) % LETTERS.length];
                encryptString.append(encrypthLetter);
            } else {
                encryptString.append(cadena.charAt(i));
            }
            
        }
    }
    
}
