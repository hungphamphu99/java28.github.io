package lab_4.service;

import lab_4.entities.DepartmentHead;
import java.util.Scanner;

public class DepartmentHeadService {

    public DepartmentHead inputDepartmentHead() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter Department Head name: ");
        String name = scanner.nextLine();

        double salary;
        while (true) {
            try {
                System.out.println("Enter Department Head base salary: ");
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

        double responsibilityAllowance;
        while (true) {
            try {
                System.out.println("Enter responsibility allowance: ");
                responsibilityAllowance = Double.parseDouble(scanner.nextLine());
                if (responsibilityAllowance >= 0) {
                    break;
                } else {
                    System.out.println("Responsibility allowance must be non-negative.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number for responsibility allowance.");
            }
        }

        return new DepartmentHead(name, salary, responsibilityAllowance);
    }
}
