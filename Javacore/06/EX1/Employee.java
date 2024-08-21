package EX1;

public class Employee extends Person {
    private int salary;

    public Employee( int id,String name, int age, String adress) {
        super(id, name, age, adress);
    }

    public int getSalary() {
        return salary;
    }
    public void setSalary(int salary) {
        this.salary = salary;

    }

}

