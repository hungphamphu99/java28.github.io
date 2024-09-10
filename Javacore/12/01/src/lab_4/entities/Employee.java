package lab_4.entities;

import lab_4.data.data;

import java.util.ArrayList;
import java.util.List;

import static lab_4.data.data.employees;

public abstract class Employee {

    private String id;
    private String name;
    protected double salary;

    public Employee(String id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        data.getEmployees(this);

    }


    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    public static List<Employee> getEmployees() {
        return employees;
    }

    public static void removeEmployee(String id) {
        employees.removeIf(employee -> employee.getId().equals(id));
    }

    public static Employee findEmployeeById(String id) {
        return employees.stream().filter(e -> e.getId().equals(id)).findFirst().orElse(null);  // Tìm nhân viên theo ID
    }

    public abstract double calculateIncome();

    public double calculateTax() {
        double income = calculateIncome();
        if (income <= 11000000) {
            return 0;
        }
        return (income - 11000000) * 0.1;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Salary: " + salary + ", Income: " + calculateIncome() + ", Tax: " + calculateTax();
    }
}
