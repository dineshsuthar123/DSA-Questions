package LeetCode_DailyQuestions;

public class _1422 {
    public static void main(String[] args) {
        _1422 solution = new _1422();
        String input = "011101"; // Example input
        int maxScore1 = solution.maxScore1(input);
        int maxScore2 = solution.maxScore2(input);
        System.out.println("Maximum Score For The First Solution: " + maxScore1);
        System.out.println("Maximum Score For The Second Solution: " + maxScore2);
    }

    public int maxScore1(String s) {
        int totalones = 0;
        int zeros = 0;
        int ones = 0;
        int result = Integer.MIN_VALUE;

        for (char ch : s.toCharArray()) {
            if (ch == '1') {
                totalones++;
            }
        }

        for (int i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i) == '0') {
                zeros++;
            } else {
                ones++;
            }
            int best = zeros + (totalones - ones);
            result = Math.max(result, best);
        }
        return result;
    }
    public int maxScore2(String s) {
        int[] prefixSum = new int[s.length()];
        int totalScore = 0;

        // Calculate prefix sum for '1's
        for (int i = 0; i < s.length(); i++) {
            prefixSum[i] = (i > 0 ? prefixSum[i - 1] : 0) + (s.charAt(i) == '1' ? 1 : 0);
        }

        int maxScore = Integer.MIN_VALUE;

        // Iterate through the string and calculate scores
        for (int i = 0; i < s.length() - 1; i++) {
            int leftZeros = (i + 1) - prefixSum[i]; // Total '0's in the left part
            int rightOnes = prefixSum[s.length() - 1] - prefixSum[i]; // Total '1's in the right part
            int currentScore = leftZeros + rightOnes;
            maxScore = Math.max(maxScore, currentScore);
        }

        return maxScore;
    }
}
