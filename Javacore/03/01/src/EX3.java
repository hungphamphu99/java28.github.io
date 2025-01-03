import java.util.Scanner;


//Cho người dùng nhập vào một số nguyên
//Mỗi khi người dùng nhập xong, hỏi người dùng có muốn tiếp tục nhập hay k (Do you want to continue? (Y/N) )
//Nếu người dùng chọn Y -> tiếp tục cho nhập
//Nếu k thì in ra tổng các số người dung vừa nhập vào

public class EX3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int sum = 0;
        boolean continueInput = true;

        while (continueInput) {
            System.out.print("Please enter an integer: ");
            String input = scanner.nextLine();

            try {
                int number = Integer.parseInt(input);
                sum += number;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter an integer.");
                continue;
            }

            System.out.print("Do you want to continue? (Y/N): ");
            String response = scanner.nextLine().trim().toUpperCase();

            while (!response.equals("Y") && !response.equals("N")) {
                System.out.print("Invalid input. Do you want to continue? (Y/N): ");
                response = scanner.nextLine().trim().toUpperCase();
            }

            if (response.equals("N")) {
                continueInput = false;
            }
        }

        System.out.println("The total sum is: " + sum);
        scanner.close();
    }
}
