package utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

public class Validator {


    public static int inputInteger(Scanner scanner){
        while(true){
            try {
                return Integer.parseInt(scanner.nextLine());
            }catch (Exception e){
                System.out.println("Invalid input");
            }
        }
    }
    public static int inputIntegerPositive(Scanner scanner) {
        while (true) {
            try {
                int input = Integer.parseInt(scanner.nextLine());
                if (input > 0) {
                    return input;
                } else {
                    System.out.println("Please enter a positive number.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid positive integer.");
            }
        }
    }


    public static double inputPositiveInteger(Scanner scanner) {
        while (true) {
            try {

                double input = Double.parseDouble(scanner.nextLine());

                if (input > 0.0 && input <= 10.0) {
                    return input; // Return the input if it is positive and less than 10
                } else {
                    System.out.println("The number must be greater than zero and less than 10. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid positive number less than 10.");
            }
        }
    }


    public static BigDecimal inputPositiveBigDecimal(Scanner scanner) {
        while (true) {
            try {
                String input = scanner.nextLine();
                BigDecimal amount = new BigDecimal(input).setScale(2, RoundingMode.HALF_UP);
                if (amount.compareTo(BigDecimal.ZERO) > 0) {
                    return amount;
                } else {
                    System.out.println("The amount must be greater than zero. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }

    public static int inputPositiveInteger_v2(Scanner scanner ,String message, int currentValue) {
        System.out.print(message + " (Press Enter to keep the current value: " + currentValue + "): ");
        String input = scanner.nextLine();

        if (input.isEmpty()) {
            System.out.println("Keeping the current value: " + currentValue);
            return currentValue;
        }

        try {
            int number = Integer.parseInt(input);
            if (number > 0) {
                System.out.println("Value updated successfully: " + number);
                return number; // Trả về giá trị mới nếu hợp lệ.
            } else {
                System.out.println("The number must be greater than 0. Please try again.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid positive number.");
        }

        return inputPositiveInteger_v2(scanner,message, currentValue);
    }

    public static double inputPositiveDouble_v2(Scanner scanner, String message, double currentValue) {
        System.out.print(message + " (Press Enter to keep the current value: " + currentValue + "): ");
        String input = scanner.nextLine();

        if (input.isEmpty()) {
            System.out.println("Keeping the current value: " + currentValue);
            return currentValue;
        }

        try {
            double number = Double.parseDouble(input);
            if (number > 0) {
                System.out.println("Value updated successfully: " + number);
                return number;
            } else {
                System.out.println("The number must be greater than 0. Please try again.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid positive number.");
        }

        return inputPositiveDouble_v2(scanner, message, currentValue);
    }


}
