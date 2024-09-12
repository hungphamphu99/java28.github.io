package lab_4.data;

import lab_4.entities.AdminStaff;
import lab_4.entities.DepartmentHead;
import lab_4.entities.Employee;
import lab_4.entities.MarketingStaff;

import java.util.ArrayList;
import java.util.List;

public class Database {
    public static List<Employee> employees = new ArrayList<>();

    static {
        employees.add(new AdminStaff( "A", 9000000));
        employees.add(new MarketingStaff( "B", 8000000, 50000000, 0.05));
        employees.add(new DepartmentHead( "C", 12000000, 3000000));
        employees.add(new AdminStaff( "D", 10000000));
        employees.add(new MarketingStaff( "E", 8500000, 70000000, 0.04));
    }


}
