package lab_3.entities;

import lab_3.data.Data;
import lab_3.utils.Enum;
import java.util.Date;

public class Worker {
    private String id;
    private String name;
    private int age;
    private double salary;
    private String workPlace;
    private Enum.Type status;
    private Date date;

    public Worker(String id, String name, int age, double salary, String workPlace) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.workPlace = workPlace;
        this.status = Enum.Type.None;
        this.date = new Date();
        Data.addWorker(this);
    }

    // Getters and setters...

    public String getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public double getSalary() { return salary; }
    public void setSalary(double salary) { this.salary = salary; }
    public String getWorkPlace() { return workPlace; }
    public Enum.Type getStatus() { return status; }
    public void setStatus(Enum.Type status) { this.status = status; }
    public Date getDate() { return date; }

    public void setDate(Date date) { this.date = date; }
    @Override
    public String toString() {
        return String.format("%-5s %-10s %-3d %-7.2f %-6s %-12s",
                id, name, age, salary, status.name(), date.toString());
    }

    public static Worker findWorkerById(String id) {
        for (Worker worker : Data.getWorkers()) {
            if (worker.getId().equals(id)) {
                return worker;
            }
        }
        return null;
    }
}
