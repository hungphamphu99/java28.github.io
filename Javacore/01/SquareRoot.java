public class SquareRoot {
    public static void main(String[] args) {
        double number = 4.0;

        double sqrtResult = Math.sqrt(number);
        double finalResult = Math.round(sqrtResult * 1000.0) / 1000.0;

        System.out.println("Result :" + finalResult);
    }
}
