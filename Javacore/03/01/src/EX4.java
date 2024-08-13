import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


//Tạo một tài khoản gồm username: techmaster, password: hoclacoviec, balance: 1000000
//Khi start chương trình hiển thị yêu cầu người dùng login (nhập username, password)
//- Nếu login thất bại -> Thông báo lỗi
//- Nếu login thành công -> Hiển thị menu cho người dung lựa chọn các tính năng sau:
//        1. Xem thông tin tài khoản (hiển thị username, password, balance)
//2. Rút tiền (cho người dùng nhập vào số tiền muốn rút sau đó in ra biên lai ”Bạn đã rút … vnđ vào lúc …”)
//- Mỗi khi người dùng thực hiện xong 1 chức năng sẽ hiển thị câu thông báo “Do you want to continue? (Y/N)
//        + Nếu người dùng chọn “Y” -> Hiển thị lại danh sách tính năng cho người dùng chọn
//+ Nếu chọn “N” -> Thoát chương trình (System.exit() )



public class EX4 {
    private static String username = "techmaster";
    private static String password = "hoclacoviec";
    private static double balance = 1000000;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        if (login(scanner)) {
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
                    default:
                        System.out.println("Please try again.");
                        continue;
                }

                continueProgram = askContinue(scanner);
            }
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
            System.out.println("Login failed! Invalid username or password.");
            return false;
        }
    }

    private static void showMenu() {
        System.out.println("\nMenu:");
        System.out.println("1. View account information");
        System.out.println("2. Withdraw money");
        System.out.print("Choose an option: ");
    }

    private static void viewAccountInfo() {
        System.out.println("\nAccount Information:");
        System.out.println("Username: " + username);
        System.out.println("Password: " + password);
        System.out.println("Balance: " + balance + " VND");
    }

    private static void withdrawMoney(Scanner scanner) {
        System.out.print("Enter the amount to withdraw: ");
        double amount = Double.parseDouble(scanner.next());

        if (amount <= balance) {
            balance -= amount;
            String receipt = String.format("You have withdrawn %.2f VND at %s", amount, Time());
            System.out.println(receipt);
        } else {
            System.out.println("Insufficient balance!");
        }
    }

    private static String Time() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

    private static boolean askContinue(Scanner scanner) {
        while (true) {
            System.out.print("Do you want to continue? (Y/N): ");
            String choice = scanner.next().trim().toUpperCase();

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
