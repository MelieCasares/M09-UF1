package iticbcn.xifratge;
import java.util.*;

public class XifradorMonoalfabetic implements Xifrador {
    public final static char[] LETTERS = "aáàäbcçdeéèëfghiíìïjklmnñoóòöpqrstuúùüvwxyz".toCharArray();
    public final static char[] PERMUTATEDLETTERS = permutaAlfabet(LETTERS);

        
    

    public int findLetter(char letter, char[] alfabet) {
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
        Collections.shuffle(listAlfabet);
        char[] permutatedAlfabet = new char[alfabet.length];
        for (int i = 0; i < alfabet.length; i++) {
            permutatedAlfabet[i] = listAlfabet.get(i);
        }
        //Character [] permutatedAlfabet = listAlfabet.toArray(new Character[alfabet.length]);
        
        return permutatedAlfabet;
    }

    public String xifraMonoAlfa(String cadena) {
        StringBuilder xifrat = new StringBuilder();
        for (int i = 0; i < cadena.length(); i++) {
            char xifratLetter;
            char letter = cadena.charAt(i);
            int posicio = findLetter(Character.toLowerCase(letter), LETTERS);
            if (posicio == -1) continue;
            if (Character.isUpperCase(letter)) {
                xifratLetter = Character.toUpperCase(PERMUTATEDLETTERS[posicio]);
            } else {
                xifratLetter = PERMUTATEDLETTERS[posicio];
            }
            xifrat.append(xifratLetter);
        }
        return xifrat.toString();

    }

    public String desxifraMonoAlfa(String cadena) {
        StringBuilder decode = new StringBuilder();
        for (int i = 0; i < cadena.length(); i++) {
            char decodeLetter;
            char letter = cadena.charAt(i);
            int posicio = findLetter(Character.toLowerCase(letter), PERMUTATEDLETTERS);
            if (posicio == -1) continue;
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
