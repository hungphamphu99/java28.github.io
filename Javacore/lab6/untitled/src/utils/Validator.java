package utils;

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


}
