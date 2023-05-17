package utils;

import java.security.SecureRandom;
import java.util.Random;

public class RandomUtil {
    private static final Random random = new SecureRandom();

    public static String getRandomString(int size, String alphabet) {
        char[] buf = new char[size];
        char[] symbols = alphabet.toCharArray();

        for (int i = 0; i < size; i++) {
            buf[i] = symbols[random.nextInt(symbols.length)];
        }

        return new String(buf);
    }
}
