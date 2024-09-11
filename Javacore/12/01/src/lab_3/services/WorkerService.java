package lab_3.services;

import lab_3.data.data;
import lab_3.entities.Worker;
import lab_3.utils.Enum;

import java.util.ArrayList;
import java.util.Scanner;

public class WorkerService {
    Scanner scanner = new Scanner(System.in);

    // Add a new worker
    public void addWorker() {
        System.out.print("Enter Worker ID: ");
        String id = scanner.nextLine();
        Worker workerId = Worker.findWorkerById(id);

        if (workerId != null) {
            System.out.println("Worker with this ID already exists.");
            return;
        }

        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Age: ");
        int age = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter Salary: ");
        double salary = Double.parseDouble(scanner.nextLine());
        System.out.print("Enter Work Place: ");
        String workPlace = scanner.nextLine();

        Worker worker = new Worker(id, name, age, salary, workPlace);
        data.addWorker(worker);
        System.out.println("Worker added successfully!");
    }

    // Update salary and status (increase or decrease)
    public void updateSalary(boolean isIncrease) {
        System.out.print("Enter Worker ID: ");
        String id = scanner.nextLine();
        Worker worker = Worker.findWorkerById(id);

        if (worker != null) {
            System.out.print("Enter Amount to " + (isIncrease ? "Increase" : "Decrease") + " Salary: ");
            double amount = Double.parseDouble(scanner.nextLine());

            if (!isIncrease && worker.getSalary() - amount < 0) {
                System.out.println("Salary cannot be negative!");
                return;
            }

            if (!isIncrease) amount = -amount;
            double newSalary = worker.getSalary() + amount;

            worker.setSalary(newSalary);
            worker.setStatus(isIncrease ? Enum.Type.Up : Enum.Type.Down);
            worker.setDate(new java.util.Date());

            System.out.println("Salary updated successfully!");
        } else {
            System.out.println("Worker not found!");
        }
    }

    public void displayWorkers() {
        ArrayList<Worker> workers = data.getWorkers();
        if (workers.isEmpty()) {
            System.out.println("No workers to display!");
        } else {
            System.out.printf("%-5s %-10s %-3s %-7s %-6s %-12s\n", "Code", "Name", "Age", "Salary", "Status", "Date");
            System.out.println("--------------------------------------------------------------");

            for (Worker worker : workers) {
                System.out.println(worker);
            }
        }
    }
}
