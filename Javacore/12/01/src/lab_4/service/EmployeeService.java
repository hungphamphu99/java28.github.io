package lab_4.service;

import lab_4.entities.AdminStaff;
import lab_4.entities.DepartmentHead;
import lab_4.entities.Employee;
import lab_4.entities.MarketingStaff;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class EmployeeService {

    public Employee inputEmployee() {
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

        String id;
        while (true) {
            System.out.println("Enter ID: ");
            id = scanner.nextLine();
            if (Employee.findEmployeeById(id) == null) {
                break;
            } else {
                System.out.println("This ID already exists. Please enter a unique ID.");
            }
        }

        String name;
        while (true) {
            System.out.println("Enter name: ");
            name = scanner.nextLine();
            if (name.matches("[a-zA-Z\\s]+")) {
                break;
            } else {
                System.out.println("Invalid name. Please enter a valid name containing only letters.");
            }
        }

        double salary;
        while (true) {
            try {
                System.out.println("Enter base salary: ");
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

        switch (type) {
            case 1:
                return new AdminStaff(id, name, salary);
            case 2:
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

                return new MarketingStaff(id, name, salary, sales, commissionRate);
            case 3:
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

                return new DepartmentHead(id, name, salary, responsibilityAllowance);
            default:
                System.out.println("Invalid employee type.");
                return null;
        }
    }



    public void removeEmployee(String id) {
        Employee.removeEmployee(id);
    }

    public void updateEmployee(String id) {
        Employee oldEmployee = Employee.findEmployeeById(id);
        if (oldEmployee != null) {
            removeEmployee(id);
            System.out.println("Enter new information:");
            Employee newEmployee = inputEmployee();
            Employee.getEmployees().add(newEmployee);
        } else {
            System.out.println("Employee with ID: " + id + " not found.");
        }
    }

    public List<Employee> searchBySalary(double minSalary) {
        return Employee.getEmployees().stream()
                .filter(employee -> employee.calculateIncome() >= minSalary)
                .collect(Collectors.toList());
    }

    public void sortByNameAndIncome() {
        Employee.getEmployees().sort(Comparator.comparing(Employee::getName)
                .thenComparing(Employee::calculateIncome));
    }

    public List<Employee> getTop5HighestIncome() {
        return Employee.getEmployees().stream()
                .sorted(Comparator.comparing(Employee::calculateIncome).reversed())
                .limit(5)
                .collect(Collectors.toList());
    }

    public void printAllEmployees() {
        for (Employee e : Employee.getEmployees()) {
            System.out.println(e);
        }
    }
}
