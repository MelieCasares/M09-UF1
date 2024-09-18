package M09_UF01_AC01_Melie_Casares;
import java.lang.Math;
import java.util.Scanner;

public class R013 {
    public static final char[] letterMin = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
    public static final char[] letterMax = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("E (encrypth) or D (decode)");
        String option = scanner.nextLine().toLowerCase();
        System.out.println("Write the phrase");
        String phrase = scanner.nextLine();
        String output;
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
        
        System.out.println(output);
    }

    public static String xifraRot13( String cadena ) {
        String encryptString = "";
        for (int i = 0; i < cadena.length(); i++) {
            char letter = cadena.charAt(i);
            int asciiLetter = letter;
            if (asciiLetter >= 65 && asciiLetter <= 90) {
                int asciiEncrypt = (asciiLetter - 65 + 13) % letterMin.length;
                letter = letterMax[asciiEncrypt];
            } else if (asciiLetter >= 97 && asciiLetter <= 122) {
                int asciiEncrypt = (asciiLetter - 97 + 13) % letterMin.length;
                letter = letterMin[asciiEncrypt];
            }
            encryptString = encryptString + letter;
        }
        return encryptString;
    }

    public static String desxifraRot13( String cadena ) {
        String decrypthString = "";
        for (int i = 0; i < cadena.length(); i++) {
            char letter = cadena.charAt(i);
            int asciiLetter = letter;
            if (asciiLetter >= 65 && asciiLetter <= 90) {
                int asciiEncrypt = (asciiLetter - 65 - 13) % letterMax.length;
                if (asciiEncrypt < 0) {
                    asciiEncrypt = letterMax.length - Math.abs(asciiEncrypt);
                }
                letter = letterMax[asciiEncrypt];
            } else if (asciiLetter >= 97 && asciiLetter <= 122) {
                int asciiEncrypt = (asciiLetter - 97 - 13) % letterMax.length;
                if (asciiEncrypt < 0) {
                    asciiEncrypt = letterMin.length - Math.abs(asciiEncrypt);
                }
                letter = letterMin[asciiEncrypt];
            }
            decrypthString = decrypthString + letter;
        }

        return decrypthString;

    }
}
