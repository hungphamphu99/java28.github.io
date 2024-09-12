package lab_4.service;

import lab_4.data.Database;
import lab_4.entities.Employee;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class EmployeeService {
    AdminStaffService adminStaffService = new AdminStaffService();
    MarketingStaffService marketingStaffService = new MarketingStaffService();
    DepartmentHeadService departmentHeadService = new DepartmentHeadService();

    public void inputEmployee() {
        Scanner scanner = new Scanner(System.in);

        int type;
        while (true) {
            try {
                System.out.println("Select employee type (1: Admin staff, 2: Marketing staff, 3: Department head): ");
                type = Integer.parseInt(scanner.nextLine());
                if (type >= 1 && type <= 3) {
                    break;
                } else {
                    System.out.println("Invalid employee type. Please choose between 1 and 3.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }

        Employee employee = null;
        switch (type) {
            case 1:
                employee = adminStaffService.inputAdminStaff();
                break;
            case 2:
                employee = marketingStaffService.inputMarketingStaff();
                break;
            case 3:
                employee = departmentHeadService.inputDepartmentHead();
                break;
            default:
                System.out.println("Invalid employee type.");
        }

        if (employee != null) {
            Database.employees.add(employee);
            System.out.println("Employee added successfully!");
        }
    }

    public void printAllEmployees() {
        if (Database.employees.isEmpty()) {
            System.out.println("No employees to display.");
        } else {
            for (Employee employee : Database.employees) {
                System.out.println(employee);
            }
        }
    }

    public void removeEmployee(int id) {
        Employee employeeToRemove = findEmployeeById(id);
        if (employeeToRemove != null) {
            Database.employees.remove(employeeToRemove);
            System.out.println("Employee removed successfully.");
        } else {
            System.out.println("Employee not found.");
        }
    }

    public void updateEmployee(int id) {
        Employee employeeToUpdate = findEmployeeById(id);
        if (employeeToUpdate != null) {
            removeEmployee(id);
            System.out.println("Enter new information:");
            inputEmployee();
        } else {
            System.out.println("Employee not found.");
        }
    }

    public List<Employee> searchBySalary(double minSalary) {
        return Database.employees.stream()
                .filter(employee -> employee.calculateIncome() >= minSalary)
                .collect(Collectors.toList());
    }

    public void sortByNameAndIncome() {
        Database.employees.sort(Comparator.comparing(Employee::getName)
                .thenComparing(Employee::calculateIncome));
        System.out.println("Employees sorted by name and income:");
        printAllEmployees();
    }

    public void getTop5HighestIncome() {
        List<Employee> top5 = Database.employees.stream()
                .sorted(Comparator.comparing(Employee::calculateIncome).reversed())
                .limit(5)
                .collect(Collectors.toList());

        System.out.println("Top 5 employees with the highest income:");
        for (Employee employee : top5) {
            System.out.println(employee);
        }
    }

    private Employee findEmployeeById(int id) {
        return Database.employees.stream().filter(employee -> employee.getId() == id).findFirst().orElse(null);
    }
}
