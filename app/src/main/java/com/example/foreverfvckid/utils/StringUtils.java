package com.example.foreverfvckid.utils;

import org.mindrot.jbcrypt.BCrypt;

import java.text.NumberFormat;
import java.util.Locale;

public class StringUtils {

    public static String formatPriceRupiah(Double price) {
        NumberFormat currencyFormat =
                NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
        return currencyFormat.format(price);
    }

    public static String hashStringWithBcrypt(String str) {
        return BCrypt.hashpw(str, BCrypt.gensalt());
    }

    public static boolean verifyBcryptHash(String str, String hash) {
        return BCrypt.checkpw(str, hash);
    }

    public static String capitalizeEachWord(String input) {
        StringBuilder result = new StringBuilder();
        String[] words = input.split("\\s+");
        for (String word : words) {
            if (!word.isEmpty()) {
                String firstLetter = word.substring(0, 1);
                String remainingLetters = word.substring(1);
                result.append(firstLetter.toUpperCase()).append(remainingLetters).append(" ");
            }
        }
        return result.toString().trim();
    }
}
