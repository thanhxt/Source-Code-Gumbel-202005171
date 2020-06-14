package de.hsmannheim.inf.pr2.ads;

import java.util.Random;

public class Aufg7 {

    public static String wordGenerator(int numberofWords) {
        Random r = new Random();
        StringBuilder str = new StringBuilder();

        for(int i = 0; i < numberofWords ; i++){

            //anzahl an Buchstaben, 26
            int x = r.nextInt(26);
            //a-z -> 97 +1 = a
            char c = (char) (97 + x);
            str.append(c);
        }
        return str.toString();
    }

    public static void main(String[] args) {
        System.out.println(wordGenerator(10));
    }
}
