package LeetCode_DailyQuestions;

public class MaxSumOfThreeSubarrays {

    public static int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        int n = nums.length;
        int[] windowSum = new int[n - k + 1];
        int sum = 0;

        for (int i = 0; i < n; i++) {
            sum += nums[i];
            if (i >= k - 1) {
                windowSum[i - k + 1] = sum;
                sum -= nums[i - k + 1];
            }
        }

        int[] leftMax = new int[windowSum.length];
        int[] rightMax = new int[windowSum.length];
        int best = 0;

        for (int i = 0; i < windowSum.length; i++) {
            if (windowSum[i] > windowSum[best]) {
                best = i;
            }
            leftMax[i] = best;
        }

        best = windowSum.length - 1;
        for (int i = windowSum.length - 1; i >= 0; i--) {
            if (windowSum[i] >= windowSum[best]) {
                best = i;
            }
            rightMax[i] = best;
        }

        int[] result = new int[3];
        int maxSum = 0;

        for (int mid = k; mid < windowSum.length - k; mid++) {
            int left = leftMax[mid - k];
            int right = rightMax[mid + k];
            int total = windowSum[left] + windowSum[mid] + windowSum[right];

            if (total > maxSum) {
                maxSum = total;
                result[0] = left;
                result[1] = mid;
                result[2] = right;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        // Test case 1
        int[] nums1 = {1, 2, 1, 2, 6, 7, 5, 1};
        int k1 = 2;
        int[] result1 = maxSumOfThreeSubarrays(nums1, k1);
        System.out.println("Test Case 1: " + java.util.Arrays.toString(result1)); // Output: [0, 3, 5]

        // Test case 2
        int[] nums2 = {1, 2, 1, 2, 1, 2, 1, 2, 1};
        int k2 = 2;
        int[] result2 = maxSumOfThreeSubarrays(nums2, k2);
        System.out.println("Test Case 2: " + java.util.Arrays.toString(result2)); // Output: [0, 2, 4]
    }
}
