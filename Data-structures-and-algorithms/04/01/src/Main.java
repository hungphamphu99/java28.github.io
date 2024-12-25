import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        RunningSum runningSum = new RunningSum();
        int[] numbers = {1,2,3,4};
        System.out.println(Arrays.toString(runningSum.runningSum(numbers)));

              }
}