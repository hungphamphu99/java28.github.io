package lab_4.entities;

public class AdminStaff extends Employee {

    public AdminStaff(String id, String name, double salary) {
        super(id, name, salary);
    }

    @Override
    public double calculateIncome() {
        return salary;
    }
}
