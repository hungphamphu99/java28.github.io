package EX1;

import java.util.Scanner;


public class Input {

    Scanner scanner = new Scanner(System.in);
    private int getValidNumber(String tag) {
        int number = -1;
        while (number <= 0) {
            System.out.println("Enter the number of "+ tag);
            try {
                number = Integer.parseInt(scanner.nextLine());
                if (number <= 0) {
                    System.out.println("Please enter an integer greater than 0.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter an integer greater than 0.");
            }
        }
        return number;
    }

    public void input(Scanner scanner) {
        int numberOfEmployees = getValidNumber("Enter the number of employees: ");

        Employee[] employees = new Employee[numberOfEmployees];
        for (int i = 0; i < numberOfEmployees; i++) {
            int employeeId = -1;
            while (employeeId <= 0) {
                System.out.println("Enter the employee id-" + (i + 1) + ":");

                try {

                    employeeId = Integer.parseInt(this.scanner.nextLine());
                    if (employeeId <= 0) {
                        System.out.println("Please enter a integer greater than 0");
                    }

                } catch (Exception e) {
                    System.out.println("Please enter a integer greater than 0");
                }

            }

            System.out.println("Enter the employee  name-" + (i + 1) + ":");
            String employeeName = this.scanner.next();
            System.out.println("Enter the employee age-" + (i + 1));
            int employeeAge = getValidNumber("Enter the employee age-" + (i + 1));
            System.out.println("Enter the employee address-" + (i + 1) + ":");
            String employeeAddress = this.scanner.next();
            employees[i] = new Employee(employeeId, employeeName, employeeAge, employeeAddress);
        }

    }
}
