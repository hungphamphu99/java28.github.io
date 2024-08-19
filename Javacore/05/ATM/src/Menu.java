import java.util.Scanner;

public class Menu {
   private final Account account;
   Scanner scanner = new Scanner(System.in);
   public Menu(Account account) {
       this.account = account;
   }
   public void showMenu() {
       boolean continueProgram = true;
       while (continueProgram) {
           displayMenu();
           String input = scanner.nextLine();
           switch (input) {
               case "1":
                   showAccount();
                   break;
               case "2":
                   withdraw();
                   break;
               case "3":
                    deposit();
                    break;
               case "4":
                   System.exit(0);
               default:
                   System.out.println("Please try again.");
                   continue;

           }
           continueProgram = askContinue(scanner);


       }
   }
   private void displayMenu() {
       System.out.println("Welcome to " + account.getUsername());
       System.out.println("Please select an option:");
       System.out.println("1. Show account");
       System.out.println("2. Withdraw ");
       System.out.println("3. Deposit");
       System.out.println("3. Exit");
   }

   private void showAccount() {
       System.out.println("Welcome to " + account.getUsername());
       System.out.println("Balance: " + account.getBalance());

   }

    private void withdraw() {
        int amount = 0;
        while (true) {
            try {
                System.out.println("Enter the amount you want to withdraw:");
                amount = Integer.parseInt(scanner.next());

                if (amount < 0) {
                    System.out.println("Please enter a positive number.");
                } else if (amount > account.getBalance()) {
                    System.out.println("Insufficient balance.");
                } else {
                    account.setBalance(account.getBalance() - amount);
                    System.out.println("Withdrawal successful! Your balance is " + account.getBalance());
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a positive number.");
            }
        }
    }

    private void deposit() {
        System.out.println("Enter the amount you want to deposit");

        int amount = 0;



        while (true) {
            String input = scanner.next();
            try {
                amount = Integer.parseInt(input);

                if (amount < 0) {
                    System.out.println("Please enter a positive number:");
                } else {
                    account.setBalance(account.getBalance() + amount);
                    System.out.printf("Your balance is %.2f%n", account.getBalance());
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number:");
            }
        }
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

