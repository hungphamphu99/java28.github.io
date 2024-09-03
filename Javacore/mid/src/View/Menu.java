package view;

import service.UserService;

import java.util.Scanner;

public class Menu {
    private UserService userService;
     Scanner scanner;

    public Menu(UserService userService) {
        this.userService = userService;
        this.scanner = new Scanner(System.in);
    }

    public void displayMainMenu() {
        int choice = -1;
        while (choice != 0) {
            try {
                System.out.println("1 - Login");
                System.out.println("2 - Register");
                System.out.println("0 - Exit program");
                choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        login();
                        break;
                    case 2:
                        register();
                        break;
                    case 0:
                        System.out.println("Exiting program.");
                        break;
                    default:
                        System.out.println("Invalid choice. Please select again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    private void login() {
        while (true) {
            try {
                System.out.print("Enter username: ");
                String username = scanner.nextLine();
                System.out.print("Enter password: ");
                String password = scanner.nextLine();

                if (userService.loginUser(username, password)) {
                    displayUserMenu();
                    break;
                } else {
                    System.out.println("Username or password is incorrect.");
                    int loginChoice = -1;
                    while (loginChoice != 1 && loginChoice != 2 && loginChoice != 3) {
                        try {
                            System.out.println("1 - Try to login again");
                            System.out.println("2 - Forgot password");
                            System.out.println("3 - Back");
                            loginChoice = Integer.parseInt(scanner.nextLine());

                            switch (loginChoice) {
                                case 1:
                                    forgotPassword();
                                    break;
                                case 2:
                                    forgotPassword();
                                    return;
                                case 3:
                                    displayMainMenu();
                                    return;
                                default:
                                    System.out.println("Invalid choice. Please select again.");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input. Please enter a number.");
                        }
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }


    private void displayUserMenu() {
        int choice = -1;
        while (choice != 0 && userService.isLoggedIn()) {
            try {
                System.out.println("1 - Change username");
                System.out.println("2 - Change email");
                System.out.println("3 - Change password");
                System.out.println("4 - Logout");
                System.out.println("0 - Exit program");

                choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        System.out.print("Enter new username: ");
                        String newUsername = scanner.nextLine();
                        userService.changeUsername(newUsername);
                        break;
                    case 2:
                        System.out.print("Enter new email: ");
                        String newEmail = scanner.nextLine();
                        userService.changeEmail(newEmail);
                        break;
                    case 3:
                        System.out.print("Enter new password: ");
                        String newPassword = scanner.nextLine();
                        userService.changePassword(newPassword);
                        break;
                    case 4:
                        userService.logout();
                        break;
                    case 0:
                        System.out.println("Exiting program.");
                        break;
                    default:
                        System.out.println("Invalid choice. Please select again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    private void register() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        userService.registerUser(username, email, password);
    }

    private void forgotPassword() {
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();
        System.out.print("Enter your email: ");
        String email = scanner.nextLine();

        if (!userService.forgotPassword(username, email)) {
            System.out.println("Password reset failed. Please try again.");
        }
    }

}
