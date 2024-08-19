import java.util.Scanner;

public class Login {
    private Account account;
    public Login(Account account) {
        this.account = account;
    }



    public boolean authenticate(String username, String password) {
        return account.getUsername().equals(username) && account.getPassword().equals(password);
    }
    public void login() {

        while (true){
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter your username: ");
            String usernameInput = scanner.nextLine();
            System.out.println("Enter your password: ");
            String passwordInput = scanner.nextLine();
            if (authenticate(usernameInput, passwordInput)){
                System.out.println("You have successfully logged in!");
                break;
            }else {
                System.out.println("You have unsuccessfully logged in!");
                while (true) {
                    System.out.print("Do you want to continue? (Y/N): ");
                    String choice = scanner.next().toUpperCase();
                    if (choice.equals("Y")) {
                       break;
                    } else if (choice.equals("N")) {
                        System.out.println("Exiting!");
                        System.exit(0);
                    } else {
                        System.out.println("Invalid input, Please enter Y or N.");
                    }
                }

            }

        }
    }
}
