package lab_4.entities;

import java.util.List;
import static lab_4.data.Database.employees;

public abstract class Employee {

    private int id;
    private static int nextId = 0;
    private String name;
    protected double salary;

    public Employee(String name, double salary) {
        this.id = ++nextId;
        this.name = name;
        this.salary = salary;
    }

    public int getId() {
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
        return String.format("ID: %d, Name: %s, Salary: %.2f, Income: %.2f, Tax: %.2f",
                getId(), getName(), getSalary(), calculateIncome(), calculateTax());
    }
}
