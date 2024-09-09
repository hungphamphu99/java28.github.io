package lab_3.entities;

import java.util.ArrayList;

public class Worker {
    private String id;
    private String name;
    private int age;
    private double salary;
    private String workPlace;

    // Static list to hold all workers
    private static ArrayList<Worker> workers = new ArrayList<>();

    public Worker(String id, String name, int age, double salary, String workPlace) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.workPlace = workPlace;
    }

    // Static method to add a worker to the list
    public static void addWorker(Worker worker) {
        workers.add(worker);
    }

    // Static method to get all workers
    public static ArrayList<Worker> getWorkers() {
        return workers;
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
        for (Worker worker : workers) {
            if (worker.getId().equals(id)) {
                return worker;
            }
        }
        return null;
    }
}
