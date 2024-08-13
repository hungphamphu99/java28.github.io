import java.util.Scanner;

//Dùng for tính tổng tất cả các số nguyên từ phần tử thứ 0 -> n
//Tính tích từ phần tử thứ 0 -> n
//Viết chương trình cho phép nhập vào một số nguyên dương n, tính tổng tất cả số chẵn trong khoảng từ 0 - n.
//Sử dụng cả 3 cách

public class EX2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = -1;

        while (n < 0) {
            System.out.print("Enter a positive integer (n): ");
            try {
                n = Integer.parseInt(scanner.next());
                if (n < 0) {
                    System.out.println("Please, enter a positive integer (n): ");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please, enter a positive integer (n):");
            }
        }


        //case 1
        int sum = 1;
        for (int i = 2; i <= n; i++) {
            sum += i;
        }
        System.out.println("The sum from 0 to " + n + " is: " + sum);

        // case 2
        int product = 1;
        for (int i = 2; i <= n; i++) {
            product *= i;
        }
        System.out.println("The product from 1 to " + n + " is: " + product);

        // case 3
        int sumEven = 0;
        for (int i = 0; i <= n; i += 2) {
            sumEven += i;
        }
        System.out.println("The sum of all even numbers from 0 to " + n + " is: " + sumEven);
    }
}
