package ex_2.utils;

import java.util.Scanner;

public class InputUtils {

    public static int inputInt(String message) {
        Scanner scanner = new Scanner(System.in);
        int value;

        while (true) {
            System.out.print(message);
            try {
                value = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid value. Please enter an integer.");
            }
        }

        return value;
    }

    public static String inputString(String message) {
        Scanner scanner = new Scanner(System.in);
        String value;

        while (true) {
            System.out.print(message);
            value = scanner.nextLine().trim();
            if (!value.isEmpty()) {
                break;
            } else {
                System.out.println("Input cannot be empty. Please enter again.");
            }
        }

        return value;
    }

    public static int inputIntInRange(String message, int min, int max) {
        int value;
        while (true) {
            value = inputInt(message);
            if (value >= min && value <= max) {
                break;
            } else {
                System.out.println("Value must be between " + min + " and " + max + ". Please enter again.");
            }
        }

        return value;
    }
}
