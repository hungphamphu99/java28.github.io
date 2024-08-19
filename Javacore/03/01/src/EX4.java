import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EX4 {
    private static String username = "techmaster";
    private static String password = "hoclacoviec";
    private static double balance = 1000000;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (!login(scanner)) {
            System.out.println("Login failed! Invalid username or password. Please try again.");
        }

        boolean continueProgram = true;
        while (continueProgram) {
            showMenu();
            int choice = Integer.parseInt(scanner.next());
            switch (choice) {
                case 1:
                    viewAccountInfo();
                    break;
                case 2:
                    withdrawMoney(scanner);
                    break;
                case 3:
                    depositMoney(scanner);
                    break;
                default:
                    System.out.println("Please try again.");
                    continue;
            }

            continueProgram = askContinue(scanner);
        }
        scanner.close();
    }

    private static boolean login(Scanner scanner) {
        System.out.print("Enter username: ");
        String inputUsername = scanner.next();
        System.out.print("Enter password: ");
        String inputPassword = scanner.next();

        if (inputUsername.equals(username) && inputPassword.equals(password)) {
            System.out.println("Login successful!");
            return true;
        } else {
            return false;
        }
    }

    private static void showMenu() {
        System.out.println("\nMenu:");
        System.out.println("1. View account information");
        System.out.println("2. Withdraw money");
        System.out.println("3. Deposit money");
        System.out.print("Choose an option: ");
    }

    private static void viewAccountInfo() {
        System.out.println("\nAccount Information:");
        System.out.println("Username: " + username);
        System.out.println("Password: " + password);
        System.out.println("Balance: " + String.format("%,.2f", balance) + " VND");
    }
    
    private static void withdrawMoney(Scanner scanner) {
        double amount = 0;
        while (true) {
            System.out.print("Enter the amount to withdraw: ");
            String input = scanner.nextLine().trim();
            try {
                amount = Double.parseDouble(input);
                if (amount > 0) {
                    break;
                } else {
                    System.out.println("The amount must be greater than zero.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid number.");
            }
        }
    
        if (amount <= balance) {
            balance -= amount;
            String receipt = String.format("You have withdrawn %,.2f VND at %s", amount, Time());
            System.out.println(receipt);
            System.out.println("New balance: " + String.format("%,.2f", balance) + " VND");
        } else {
            System.out.println("Insufficient balance!");
        }
    }
    
    private static void depositMoney(Scanner scanner) {
        double amount = 0;
        while (true) {
            System.out.print("Enter the amount to deposit: ");
            String input = scanner.nextLine().trim();
            try {
                amount = Double.parseDouble(input);
                if (amount > 0) {
                    break;
                } else {
                    System.out.println("The amount must be greater than zero.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid number.");
            }
        }
    
        balance += amount;
        String receipt = String.format("You have deposited %,.2f VND at %s", amount, Time());
        System.out.println(receipt);
        System.out.println("New balance: " + String.format("%,.2f", balance) + " VND");
    }
    
    

    private static String Time() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

    private static boolean askContinue(Scanner scanner) {
        while (true) {
            System.out.print("Do you want to continue? (Y/N): ");
            String choice = scanner.next().toUpperCase();

            if (choice.equals("Y")) {
                return true;
            } else if (choice.equals("N")) {
                System.out.println("Exiting!");
                System.exit(0);
            } else {
                System.out.println("Invalid input, Please enter Y or N.");
            }
        }
    }
}
