package lab_3.services;

import lab_3.entities.Worker;

import java.util.ArrayList;
import java.util.Scanner;

public class WorkerService {
   Scanner scanner = new Scanner(System.in);

    // Add a new worker
    public void addWorker() {
        System.out.print("Enter Worker ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Age: ");
        int age = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter Salary: ");
        double salary = Double.parseDouble(scanner.nextLine());
        System.out.print("Enter Work Place: ");
        String workPlace = scanner.nextLine();

        Worker worker = new Worker(id, name, age, salary, workPlace);
        Worker.addWorker(worker);
        System.out.println("Worker added successfully!");
    }

    // Update salary (increase or decrease)
    public void updateSalary(boolean isIncrease) {
        System.out.print("Enter Worker ID: ");
        String id = scanner.nextLine();
        Worker worker = Worker.findWorkerById(id);
        if (worker != null) {
            System.out.print("Enter Amount to " + (isIncrease ? "Increase" : "Decrease") + " Salary: ");
            double amount = Double.parseDouble(scanner.nextLine());
            if (!isIncrease) amount = -amount;
            worker.setSalary(worker.getSalary() + amount);
            System.out.println("Salary updated successfully!");
        } else {
            System.out.println("Worker not found!");
        }
    }

    // Display worker information
    public void displayWorkers() {
        ArrayList<Worker> workers = Worker.getWorkers();
        if (workers.isEmpty()) {
            System.out.println("No workers to display!");
        } else {
            for (Worker worker : workers) {
                System.out.println(worker);
            }
        }
    }
}
