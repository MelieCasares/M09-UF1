import java.util.*;

public class PolyAlfabetic {
    public static final char[] LETTERS = "aáàäbcçdeéèëfghiíìïjklmnñoóòöpqrstuúùüvwxyz".toCharArray();
    public static char[] permutatedLetters;
    public static final long randSeed = 54546461;
    public static final Random rand = new Random();
    public static final String clauSecreta = "keyCloak";

    public static void main(String[] args) {
        String msgs[] = {"Test 01 àrbritre, coixí, Perímetre",

            "Test 02 Taüll, DÍA, año",
            "Test 03 Peça, Òrrius, Bòvila"};
        String msgsXifrats [] = new String[msgs.length];
        System.out.println("Xifratge:\n--------");
        for (int i = 0; i < msgs.length; i++) {
            initRandom(clauSecreta );
            msgsXifrats [i] = xifraPoliAlfa(msgs[i]);
            System.out.printf("%-34s -> %s%n", msgs[i], msgsXifrats [i]);
        }
        System.out.println("Desxifratge: \n-----------" );
        for (int i = 0; i < msgs.length; i++) {
            initRandom(clauSecreta );
            String msg = desxifraPoliAlfa (msgsXifrats [i]);
            System.out.printf("%-34s -> %s%n", msgsXifrats [i], msg);
        }
        
    }

    public static int findLetter(char letter, char[] alfabet) {
        for (int i = 0; i < alfabet.length; i++) {
            if (alfabet[i] == letter) return i;
        }
        return -1;
    }

    public static char[] permutaAlfabet(char[] alfabet) {
        Character[] alfabetChar = new Character[alfabet.length];
        for (int i = 0; i < alfabet.length; i++) {
            alfabetChar[i] = alfabet[i];
        }
        List<Character> listAlfabet = Arrays.asList(alfabetChar);
        Collections.shuffle(listAlfabet, rand);
        char[] permutatedAlfabet = new char[alfabet.length];
        for (int i = 0; i < alfabet.length; i++) {
            permutatedAlfabet[i] = listAlfabet.get(i);
        }
        //Character [] permutatedAlfabet = listAlfabet.toArray(new Character[alfabet.length]);
        
        return permutatedAlfabet;
    }

    public static void initRandom(String clau) {
        if (clau.equals(clauSecreta)) rand.setSeed(randSeed);
    }

    public static String xifraPoliAlfa(String cadena) {
        StringBuilder xifrat = new StringBuilder();
        for (int i = 0; i < cadena.length(); i++) {
            char xifratLetter;
            char letter = cadena.charAt(i);
            int posicio = findLetter(Character.toLowerCase(letter), LETTERS);
            if (posicio == -1) {
                xifrat.append(letter);
                continue;
            } 
            permutatedLetters = permutaAlfabet(LETTERS);
            
            if (Character.isUpperCase(letter)) {
                xifratLetter = Character.toUpperCase(permutatedLetters[posicio]);
            } else {
                xifratLetter = permutatedLetters[posicio];
            }
            xifrat.append(xifratLetter);
        }
        return xifrat.toString();

    }

    public static String desxifraPoliAlfa(String cadena) {
        StringBuilder decode = new StringBuilder();
        for (int i = 0; i < cadena.length(); i++) {
            char decodeLetter;
            char letter = cadena.charAt(i);
            int isLetter = findLetter(Character.toLowerCase(letter), LETTERS);
            if (isLetter == -1) {
                decode.append(letter);
                continue;
            }
            permutatedLetters = permutaAlfabet(LETTERS);
            int posicio = findLetter(Character.toLowerCase(letter), permutatedLetters);
            if (Character.isUpperCase(letter)) {
                decodeLetter = Character.toUpperCase(LETTERS[posicio]);
            } else {
                decodeLetter = LETTERS[posicio];
            }
            decode.append(decodeLetter);
        }
        return decode.toString();
    }
}
