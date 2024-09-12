package lab_4.view;

import lab_4.entities.Employee;
import lab_4.service.EmployeeService;

import java.util.List;
import java.util.Scanner;

public class Menu {
    EmployeeService employeeService = new EmployeeService();
    Scanner scanner = new Scanner(System.in);

    public void displayMenu() {

        while (true) {
            try {
                System.out.println("\nSelect an action:");
                System.out.println("1. Add new employee");
                System.out.println("2. Update employee information");
                System.out.println("3. Remove employee");
                System.out.println("4. Search employees by salary");
                System.out.println("5. Sort employees by name and income");
                System.out.println("6. Show top 5 highest-income employees");
                System.out.println("7. Show all employees");
                System.out.println("8. Exit");

                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        employeeService.inputEmployee();
                        break;
                    case 2:
                        System.out.println("Enter the ID of the employee to update: ");
                        int updateId = Integer.parseInt(scanner.nextLine());
                        employeeService.updateEmployee(updateId);
                        break;
                    case 3:
                        System.out.println("Enter the ID of the employee to remove: ");
                        int removeId = Integer.parseInt(scanner.nextLine());
                        employeeService.removeEmployee(removeId);
                        break;
                    case 4:
                        System.out.println("Enter the minimum income to search: ");
                        double minSalary = getValidDouble();
                        List<Employee> results = employeeService.searchBySalary(minSalary);
                        if (results.isEmpty()) {
                            System.out.println("No employees found with a salary greater than " + minSalary);
                        } else {
                            results.forEach(System.out::println);
                        }
                        break;
                    case 5:
                        employeeService.sortByNameAndIncome();
                        break;
                    case 6:
                        employeeService.getTop5HighestIncome();
                        break;
                    case 7:
                        employeeService.printAllEmployees();
                        break;
                    case 8:
                        System.out.println("Exiting the program.");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid choice, please try again.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input, please try again.");
            }
        }
    }

    private double getValidDouble() {
        double input;
        while (true) {
            try {
                input = Double.parseDouble(scanner.nextLine());
                return input;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input, please enter a valid number.");
            }
        }
    }
}
