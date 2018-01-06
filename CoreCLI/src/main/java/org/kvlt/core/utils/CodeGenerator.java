package org.kvlt.core.utils;

import java.util.Random;

public class CodeGenerator {

    private static char[] VOWELS = { 'a', 'e', 'u', 'o'};
    private static char[] CONSONANTS = { 'b', 'c', 'd', 'f', 'g', 'h', 'k', 'm',
            'n', 'p', 'r', 's', 't', 'w', 'z' };

    private static final int CODE_LENGTH = 5;
    private static final Random random;

    static {
        random = new Random();
    }

    public static String genNiceCode() {
        char[] code = new char[CODE_LENGTH];
        for (int i = 0; i < CODE_LENGTH; i++) {
            if (i > 0) {
                if (isVowel(code[i - 1])) {
                    code[i] = CONSONANTS[random.nextInt(CONSONANTS.length)];
                } else {
                    code[i] = VOWELS[random.nextInt(VOWELS.length)];
                }
            } else {
                char[] rndArray = random.nextBoolean() ? VOWELS : CONSONANTS;
                code[i] = rndArray[random.nextInt(rndArray.length)];
                //code[i] = VOWELS[random.nextInt(VOWELS.length)];
            }
        }
        return new String(code);
    }

    private static boolean isVowel(char c) {
        for (char vowelChar : VOWELS) {
            if (vowelChar == c) return true;
        }
        return false;
    }

}
