package lab_4.entities;

public class MarketingStaff extends Employee {
    private double sales;
    private double commissionRate;

    public MarketingStaff( String name, double salary, double sales, double commissionRate) {
        super(name, salary);
        this.sales = sales;
        this.commissionRate = commissionRate;
    }

    @Override
    public double calculateIncome() {
        return salary + sales * commissionRate;
    }
}
