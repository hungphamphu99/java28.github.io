import java.util.Scanner;

//Làm demo một chiếc máy tính bỏ túi
//Người dùng sẽ nhập vào 2 số a, b và phép toán họ muốn thực hiện (+=x/%)
//->  Thực hiện tính phép tính trên


public class EX1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double a = ValidNumber(scanner, "Enter number (a): ");

        double b = ValidNumber(scanner, "Enter number (b): ");

        char operation = ValidOperation(scanner);


        Operation calculatorOperation = new Operation();
        double result = calculatorOperation.calculate(a, b, operation);

        if (calculatorOperation.isValidOperation()) {
            System.out.println("Result: " + result);
        }

        scanner.close();
    }

    private static double ValidNumber(Scanner scanner, String message) {
        double number;
        while (true) {
            System.out.print(message);
            try  {
                number = Double.parseDouble(scanner.next());
                break;
            } catch(NumberFormatException e) {
                System.out.println("Error");

            }
        }
        return number;
    }
    private static char ValidOperation(Scanner scanner) {
        char operation;
        while (true) {
            System.out.print("Choose math operation (+, %, *, /): ");
            operation = scanner.next().charAt(0);
            if (operation == '+' || operation == '%' || operation == '*' || operation == '/') {
                break;
            } else {
                System.out.println(" Please,try again");
            }
        }
        return operation;
    }


}

class Operation {
    private boolean validOperation;

    public double calculate(double a, double b, char operation) {
        double result = 0;
        validOperation = true;

        switch (operation) {
            case '+':
                result = a + b;
                break;
            case '%':
                if (b != 0) {
                    result = a % b;
                } else {
                    System.out.println("Erorr");
                    validOperation = false;
                }
                break;
            case '*':
                result = a * b;
                break;
            case '/':
                if (b != 0) {
                    result = a / b;
                } else {
                    System.out.println("Erorr");
                    validOperation = false;
                }
                break;
            default:
                System.out.println("Erorr");
                validOperation = false;
                break;
        }

        return result;
    }

    public boolean isValidOperation() {
        return validOperation;
    }
}



