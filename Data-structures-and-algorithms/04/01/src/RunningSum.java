public class RunningSum {
    public int[] runningSum(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }
        int[] runningSum = new int[nums.length];
        runningSum[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {

            runningSum[i] = runningSum[i - 1] + nums[i];


        }

        return runningSum;
    }
}
