package lab_4.service;

import lab_4.entities.MarketingStaff;
import java.util.Scanner;

public class MarketingStaffService {

    public MarketingStaff inputMarketingStaff() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter Marketing Staff name: ");
        String name = scanner.nextLine();

        double salary;
        while (true) {
            try {
                System.out.println("Enter Marketing Staff base salary: ");
                salary = Double.parseDouble(scanner.nextLine());
                if (salary > 0) {
                    break;
                } else {
                    System.out.println("Salary must be a positive number.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number for salary.");
            }
        }

        double sales;
        while (true) {
            try {
                System.out.println("Enter sales: ");
                sales = Double.parseDouble(scanner.nextLine());
                if (sales >= 0) {
                    break;
                } else {
                    System.out.println("Sales must be non-negative.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number for sales.");
            }
        }

        double commissionRate;
        while (true) {
            try {
                System.out.println("Enter commission rate: ");
                commissionRate = Double.parseDouble(scanner.nextLine());
                if (commissionRate >= 0 && commissionRate <= 1) {
                    break;
                } else {
                    System.out.println("Commission rate must be between 0 and 1.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number for commission rate.");
            }
        }

        return new MarketingStaff(name, salary, sales, commissionRate);
    }
}
