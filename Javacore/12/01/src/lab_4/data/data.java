package lab_4.data;

import lab_4.entities.AdminStaff;
import lab_4.entities.DepartmentHead;
import lab_4.entities.Employee;
import lab_4.entities.MarketingStaff;

import java.util.ArrayList;
import java.util.List;

public class data {
    public static List<Employee> employees = new ArrayList<>();

    static {
        employees.add(new AdminStaff("2001", "A", 9000000));
        employees.add(new MarketingStaff("2002", "B", 8000000, 50000000, 0.05));
        employees.add(new DepartmentHead("2003", "C", 12000000, 3000000));
        employees.add(new AdminStaff("2004", "D", 10000000));
        employees.add(new MarketingStaff("2005", "E", 8500000, 70000000, 0.04));
    }

    public static void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public static List<Employee> getEmployees() {
        return employees;
    }
}
