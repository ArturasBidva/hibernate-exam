package lt.arturas.exam.application;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserInputReceiver {
    Scanner scanner = new Scanner(System.in);

    String getUserTextInput() {
        String ivestis = scanner.nextLine();
        while (ivestis.equals("")) {
            ivestis = scanner.nextLine();
        }
        return ivestis;
    }

    Long getUserLongInput() {
        Long ivestis = -1L;
        while (ivestis.equals(-1L)) {
            try {
                ivestis = scanner.nextLong();
            } catch (InputMismatchException e) {
                System.out.println("Bloga ivestis");
                jumpLineScanner();

            }
        }
        return ivestis;
    }

    void jumpLineScanner() {
        scanner.nextLine();
    }

}

