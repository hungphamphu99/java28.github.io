package menu;

import services.PetService;

import java.util.Scanner;

public class Menu {
    Scanner scanner = new Scanner(System.in);
    PetService petService = new PetService();

    public void displayMenu() {
        while (true) {
            System.out.println("======== Pet Breeding Service ========");
            System.out.println("1. Match Pets");
            System.out.println("2. Display All Pets");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:

                    petService.match();
                    break;
                case 2:
                    petService.displayAllPets();
                    break;
                case 3:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option! Please try again.");
            }
        }
    }
}
