package lab_4.data;

import lab_4.entities.Employee;

import java.util.ArrayList;
import java.util.List;

public class data {
    public static List<Employee> employees = new ArrayList<>();
    public static void addEmployee(Employee employee) {
        employees.add(employee);

    }
    public static List<Employee> getEmployees(Employee employee) {
        return employees;

    }
}
