package lab_4.entities;

public class AdminStaff extends Employee {

    public AdminStaff( String name, double salary) {
        super( name, salary);
    }

    @Override
    public double calculateIncome() {
        return salary;
    }
}
