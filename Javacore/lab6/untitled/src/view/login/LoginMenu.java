package view.login;

import view.salesmanagement.CustomerMenu;
import view.salesmanagement.StaffMenu;
import view.universitymanagement.AdminMenu;
import view.universitymanagement.StudentMenu;
import view.universitymanagement.TeacherMenu;
import service.login.UserService;
import entities.login.User;

import java.util.Scanner;

public class LoginMenu {
   Scanner scanner = new Scanner(System.in);
   UserService userService = new UserService();


    public void mainMenu(){
        int choice = -1;
        while (choice != 0) {
            System.out.println("\n----- Main Menu -----");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            try {
                choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        login();
                        break;
                    case 2:
                        register();
                        break;
                    case 3:
                        System.out.println("Exiting the system. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please select 1, 2, or 3.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    private void login() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        if (userService.loginUser(username, password)) {
            User loggedInUser = userService.getLoggedInUser();

            displayMenuForRole(loggedInUser);
        } else {
            System.out.println("Login failed. Invalid username or password.");
        }
    }

    private void register() {
        boolean isRegistered = userService.registerUser();
        if (isRegistered) {
            System.out.println("Registration successful!");
        } else {
            System.out.println("Registration failed. Please try again.");
        }
    }

    private void displayMenuForRole(User user) {
        if (user.isAdmin()) {
            new AdminMenu().display();
        } else if (user.isTeacher()) {
            new TeacherMenu().display(user);
        } else if (user.isStudent()) {
            new StudentMenu().display(user);
        } else if (user.isCustomer()) {
            new CustomerMenu().display(user);
        } else if (user.isStaff()) {
            new StaffMenu().display(user);
        } else {
            System.out.println("Unknown role. No menu available.");
        }
    }
}
