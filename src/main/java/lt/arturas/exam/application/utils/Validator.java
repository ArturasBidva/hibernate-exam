package lt.arturas.exam.application.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
    public boolean hasDigits(String input) {
        char[] letters = input.toCharArray();
        for (char c : letters) {
            if (Character.isDigit(c)) {
                return true;
            }
        }
        return false;
    }

    public boolean containsIllegals(String toExamine) {
        Pattern pattern = Pattern.compile("[~#@*+!%{}<>\\[\\]|\"\\_^]");
        Matcher matcher = pattern.matcher(toExamine);
        return matcher.find();
    }
}
