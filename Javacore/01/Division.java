public class Division {
    public static void main(String[] args) {
        int numerator = 5;
        int denominator = 6;

        double quotient = (double) numerator / denominator;
        double roundedQuotient = Math.round(quotient * 1000.0) / 1000.0;

        System.out.println("Result " + roundedQuotient);
    }
}
