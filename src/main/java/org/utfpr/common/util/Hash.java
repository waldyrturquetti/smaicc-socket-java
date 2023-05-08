package org.utfpr.common.util;

public class Hash {

    private static final Integer CIPHER_KEY = 2;

    public static String encrypt(String password) {
        StringBuilder cipherText = new StringBuilder();
        int sizePassword = password.length();

        for (int c = 0; c < sizePassword; c++) {
            int cipherLetterFromACSII = ((int) password.charAt(c)) + CIPHER_KEY;

            while (cipherLetterFromACSII > 126) {
                cipherLetterFromACSII -= 1;
            }

            cipherText.append((char) cipherLetterFromACSII);
        }

        return cipherText.toString();
    }

    public static String decryption(String password) {
        StringBuilder text = new StringBuilder();
        int sizePassword = password.length();

        for (int c = 0; c < sizePassword; c++) {
            int decipheredLetterFromACSII = ((int) password.charAt(c)) - CIPHER_KEY;

            while (decipheredLetterFromACSII < 2) {
                decipheredLetterFromACSII += 1;
            }

            text.append((char) decipheredLetterFromACSII);
        }

        return text.toString();
    }
}
