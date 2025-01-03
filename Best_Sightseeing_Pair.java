package LeetCode_DailyQuestions;

public class Best_Sightseeing_Pair {
    public static void main(String[] args) {
        DP_solution solution = new DP_solution();

        //  Given example
        int[] values1 = {8,1,5,2,6};
        System.out.println("Test Case 1:");
        System.out.println("Input: [8,1,5,2,6]");
        System.out.println("Expected Output: 11");
        System.out.println("Actual Output: " + solution.maxScoreSightseeingPair(values1));
        System.out.println();

        int[] values2 = {10,8,6,4,2};
        System.out.println("Test Case 2:");
        System.out.println("Input: [10,8,6,4,2]");
        System.out.println("Expected Output: 17");
        System.out.println("Actual Output: " + solution.maxScoreSightseeingPair(values2));
        System.out.println();

        int[] values3 = {1,2,3,4,5};
        System.out.println("Test Case 3:");
        System.out.println("Input: [1,2,3,4,5]");
        System.out.println("Expected Output: 8");
        System.out.println("Actual Output: " + solution.maxScoreSightseeingPair(values3));
        System.out.println();

        int[] values4 = {1,2};
        System.out.println("Test Case 4:");
        System.out.println("Input: [1,2]");
        System.out.println("Expected Output: 2");
        System.out.println("Actual Output: " + solution.maxScoreSightseeingPair(values4));
    }
}

class DP_solution {
    public int maxScoreSightseeingPair(int[] values) {
        int ans = 0;
        int val = values[0];
        for(int i = 1; i < values.length; i++) {
            ans = Math.max(ans, values[i] + val - 1);
            val = Math.max(values[i], val - 1);
        }
        return ans;
    }
}