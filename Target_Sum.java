package LeetCode_DailyQuestions;

public class Target_Sum {
    // Recursive solution
    static class RecursiveSolution {
        private int count = 0;

        public int findTargetSum(int[] nums, int target) {
            backtrack(nums, 0, 0, target);
            return count;
        }

        private void backtrack(int[] nums, int index, int sum, int target) {
            if (index == nums.length) {
                if (sum == target) {
                    count++;
                }
                return;
            }

            backtrack(nums, index + 1, sum + nums[index], target);
            backtrack(nums, index + 1, sum - nums[index], target);
        }
    }

    // 1D DP solution
    static class DPSolution {
        public int findTargetSum(int[] nums, int target) {
            int total = 0;
            for (int num : nums) {
                total += num;
            }

            if (Math.abs(target) > total) {
                return 0;
            }

            int[] dp = new int[2 * total + 1];
            dp[nums[0] + total] = 1;
            dp[-nums[0] + total] += 1;

            for (int i = 1; i < nums.length; i++) {
                int[] next = new int[2 * total + 1];
                for (int sum = -total; sum <= total; sum++) {
                    if (dp[sum + total] > 0) {
                        next[sum + nums[i] + total] += dp[sum + total];
                        next[sum - nums[i] + total] += dp[sum + total];
                    }
                }
                dp = next;
            }

            return target > total ? 0 : dp[target + total];
        }
    }

    public static void main(String[] args) {
        // Test cases
        int[][] testNums = {
                {1, 1, 1, 1, 1},
                {1},
                {1, 2, 3},
                {0, 0, 0, 0, 0}
        };
        int[] testTargets = {3, 1, 2, 0};

        RecursiveSolution recursiveSolution = new RecursiveSolution();
        DPSolution dpSolution = new DPSolution();

        System.out.println("Testing both solutions:");
        System.out.println("----------------------");

        for (int i = 0; i < testNums.length; i++) {
            System.out.println("\nTest Case " + (i + 1) + ":");
            System.out.print("Numbers: [");
            for (int j = 0; j < testNums[i].length; j++) {
                System.out.print(testNums[i][j]);
                if (j < testNums[i].length - 1) System.out.print(", ");
            }
            System.out.println("]");
            System.out.println("Target: " + testTargets[i]);

            int recursiveResult = recursiveSolution.findTargetSum(testNums[i], testTargets[i]);
            int dpResult = dpSolution.findTargetSum(testNums[i], testTargets[i]);

            System.out.println("Recursive Solution Result: " + recursiveResult);
            System.out.println("DP Solution Result: " + dpResult);

            // Verify both solutions give same result
            if (recursiveResult == dpResult) {
                System.out.println("✓ Both solutions match!");
            } else {
                System.out.println("✗ Solutions don't match!");
            }
        }
    }
}