package Polarion_Testdata.Utils;

import java.security.SecureRandom;

public class RandomString {

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final int length = 5;

    static SecureRandom secureRandom;
    static StringBuilder stringBuilder;

    public static String stringGenerator() {
        secureRandom = new SecureRandom();
        stringBuilder = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = secureRandom.nextInt(CHARACTERS.length());
            stringBuilder.append(CHARACTERS.charAt(index));
        }
        return stringBuilder.toString();
    }

    public static String sapID() {
        stringGenerator();
        int a = (int) (Math.random() * 100);
        stringBuilder.append("-");
        stringBuilder.append(a);

        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        System.out.println(sapID());
    }
}
