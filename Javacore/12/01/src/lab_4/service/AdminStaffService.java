package lab_4.service;

import lab_4.entities.AdminStaff;
import java.util.Scanner;

public class AdminStaffService {

    public AdminStaff inputAdminStaff() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter Admin Staff name: ");
        String name = scanner.nextLine();

        double salary;
        while (true) {
            try {
                System.out.println("Enter Admin Staff base salary: ");
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

        return new AdminStaff(name, salary);
    }
}
