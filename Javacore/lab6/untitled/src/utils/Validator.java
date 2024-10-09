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
}
