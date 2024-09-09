package lab_3.view;

import lab_3.services.WorkerService;

import java.util.Scanner;

public class Menu {
     Scanner scanner = new Scanner(System.in);
     WorkerService workerService = new WorkerService();
    public void displayMenu() {
        while (true) {
            System.out.println("======== Worker Management ========");
            System.out.println("1. Add Worker");
            System.out.println("2. Increase Salary");
            System.out.println("3. Decrease Salary");
            System.out.println("4. Display Worker Information");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    workerService.addWorker();
                    break;
                case 2:
                    workerService.updateSalary(true);
                    break;
                case 3:
                    workerService.updateSalary(false);
                    break;
                case 4:
                    workerService.displayWorkers();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option! Please try again.");
            }
        }

    }
}
