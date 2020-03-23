import java.util.*;
import java.lang.Math;
import java.lang.Enum;

import static java.lang.StrictMath.sqrt;
import static java.lang.String.valueOf;

public class CodeWars1 {
    public static void main(String[] args) {

//        System.out.println(CodeWars1.expanSion("RqaEzty"));
//        System.out.println(CodeWars1.numberVowels("adlkjllfeerclkiau"));
//        System.out.println(CodeWars1.extremIntOfaSeq("10 2 -13 4 -5"));
//        System.out.println(CodeWars1.shortestWord("nous sommes allés vers Soultzeren"));
//        System.out.println(CodeWars1.isItaSquare(3));
//        System.out.println(CodeWars1.isItAnIsogram("mOseRadvi"));
        System.out.println(CodeWars1.distinctLetters("yaabbbccccdefww", "azbklmopq"));

    }

    
    public static String expanSion (String s) {
        char[] tabIn = s.toCharArray();
        String texteNew = new String();

        for (int i = 0; i < tabIn.length; i++) {
            for (int j = 0; j < i+1; j++) {
                if (j==0) texteNew = texteNew + valueOf(tabIn[i]).toUpperCase();
                else texteNew = texteNew + valueOf(tabIn[i]).toLowerCase();
            }
            if (i < tabIn.length -1) { texteNew = texteNew + '-'; }
        }
        return texteNew;
    }

    public static int numberOfVowels(String s) {
        char[] tabIn = s.toCharArray();
        int cpt = 0;
        for (int i = 0; i < tabIn.length; i++) {

            if ( (s.charAt(i) == 'a') || (s.charAt(i) == 'e') ||(s.charAt(i) == 'u')
                || (s.charAt(i) == 'i') || (s.charAt(i) == 'o') ) cpt++;
        }
        return cpt;
    }

    public static String extremIntOfaSeq (String s) {
        int result = 0;
        int previous = 0;
        ArrayList<String> seq = new ArrayList<>();
        String t;

        result = s.indexOf(' ', previous);

        // Chargement dans une Array
        while (result != -1) {
            seq.add(s.substring(previous, result));
            previous = result + 1;
            result = s.indexOf(' ', previous);
        }
        seq.add(s.substring(previous,s.length()));

        // extraction des min et max
        Collections.sort(seq);
        t =  seq.get(seq.size()-1) + " " + seq.get(0);

        return t;
    }

    public static String shortestWord (String s) {
        int result;
        int previous = 0;
        HashMap<String, Integer> dict = new HashMap<String, Integer>();
        String t;
        String mot;

        result = s.indexOf(' ', previous); //first
        // Chargement dans une Hashmap
        while (result != -1) {
            mot = s.substring(previous,result);
            if (mot.length() != 1 || mot != " ") dict.put(mot, mot.length());
            previous = result+1;
            result = s.indexOf(' ', previous);
        }

        mot = s.substring(previous,s.length()); //last
        if (mot.length() != 1 || mot != " ") dict.put(mot, mot.length());


        // parcours et extraction du mot le plus court
        Integer mini = s.length();
        String ind = new String();
        for (Map.Entry mapentry : dict.entrySet()) {
            if (mini > (Integer) mapentry.getValue()) {
                mini = (Integer) mapentry.getValue();
                ind = (String) mapentry.getKey();
            }
        }

         return "Mot le plus cours : <<" + ind + ">> , longueur : " + mini.toString();
    }

    public static boolean isItaSquare (Integer i) {
        Double d;
        Double s;

        d = i.doubleValue();
        s = sqrt(d);
        if ((s == d) || (s-s.intValue() != 0)) return false;
        else return true;
    }

    public static boolean isItAnIsogram (String s) {
        HashMap<Character, Integer> mapOfLetter = new HashMap<Character, Integer>();
        char[] lettersReceived = s.toLowerCase().toCharArray();
        char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        for (char letter : alphabet) {
            mapOfLetter.put(letter,0);
        }

        for (int i = 0; i < lettersReceived.length; i++) {
            if (mapOfLetter.get(lettersReceived[i]) == 0) mapOfLetter.put(lettersReceived[i],1);
            else if (mapOfLetter.get(lettersReceived[i]) == 1) return false;
        }

        return true;
    }

    public static String distinctLetters (String s1, String s2) {
        TreeSet<Character> mapOfLetter = new TreeSet<>();
        char[] lettersReceived = (s1+s2).toLowerCase().toCharArray();
        String result = "";

        for (int i = 0; i < lettersReceived.length; i++) {
            mapOfLetter.add(lettersReceived[i]);
        }

        for (Character character : mapOfLetter) result += character;
        return result;
    }


}
