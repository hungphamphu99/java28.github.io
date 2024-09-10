package lab_3.entities;

import lab_3.data.data;

import java.util.ArrayList;

public class Worker {
    private String id;
    private String name;
    private int age;
    private double salary;
    private String workPlace;


    public Worker(String id, String name, int age, double salary, String workPlace) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.workPlace = workPlace;
        data.addWorker(this);
    }



    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getWorkPlace() {
        return workPlace;
    }

    @Override
    public String toString() {
        return String.format("Code: %s, Name: %s, Age: %d, Salary: %.2f, Workplace: %s", id, name, age, salary, workPlace);
    }

    // Static method to find a worker by ID
    public static Worker findWorkerById(String id) {
        for (Worker worker : data.getWorkers()) {
            if (worker.getId().equals(id)) {
                return worker;
            }
        }
        return null;
    }
}
