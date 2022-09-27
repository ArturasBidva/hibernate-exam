package lt.arturas.exam.application.utils;

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

}