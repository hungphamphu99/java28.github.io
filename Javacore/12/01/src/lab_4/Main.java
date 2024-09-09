package lab_4;

import lab_4.entities.AdminStaff;
import lab_4.entities.DepartmentHead;
import lab_4.entities.Employee;
import lab_4.entities.MarketingStaff;
import lab_4.view.Menu;

public class Main {
    public static void main(String[] args) {
        Employee.getEmployees().add(new AdminStaff("2001", "A", 9000000));
        Employee.getEmployees().add(new MarketingStaff("2002", "B", 8000000, 50000000, 0.05));
        Employee.getEmployees().add(new DepartmentHead("2003", "C", 12000000, 3000000));
        Employee.getEmployees().add(new AdminStaff("2004", "D", 10000000));
        Employee.getEmployees().add(new MarketingStaff("2005", "E", 8500000, 70000000, 0.04));

        Menu menu = new Menu();
        menu.displayMenu();
    }
}

