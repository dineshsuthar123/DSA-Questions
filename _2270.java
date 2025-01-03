package LeetCode_DailyQuestions;

class _2270 {

    public int waysToSplitArray(int[] nums) {
        int count = 0;
        long prefixSum = 0;
        long totalSum = 0;

        for(int num : nums) {
            totalSum += num;
        }

        for(int i = 0; i < nums.length - 1; i++) {
            prefixSum += nums[i];
            long rightSum = totalSum - prefixSum;

            if(prefixSum >= rightSum) {
                count++;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        _2270 solution = new _2270();

        // Test cases
        int[] test1 = {10,4,-8,7};
        int[] test2 = {2,3,1,0};

        System.out.println("Test Case 1: " + solution.waysToSplitArray(test1)); // Expected: 2
        System.out.println("Test Case 2: " + solution.waysToSplitArray(test2)); // Expected: 2

        int[] test3 = {-65494,50355,-59116,-77153,-6748,-50098};
        System.out.println("Test Case 3: " + solution.waysToSplitArray(test3));
    }
}