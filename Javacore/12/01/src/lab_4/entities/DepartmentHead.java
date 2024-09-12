package lab_4.entities;

public class DepartmentHead extends Employee {
    private double responsibilityAllowance;

    public DepartmentHead( String name, double salary, double responsibilityAllowance) {
        super( name, salary);
        this.responsibilityAllowance = responsibilityAllowance;
    }

    @Override
    public double calculateIncome() {
        return salary + responsibilityAllowance;
    }
}
