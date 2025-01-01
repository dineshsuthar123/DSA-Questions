package LeetCode_DailyQuestions;

public class _1422 {
    public static void main(String[] args) {
        _1422 solution = new _1422();
        String input = "011101"; // Example input
        int maxScore = solution.maxScore(input);
        System.out.println("Maximum Score: " + maxScore);
    }

    public int maxScore(String s) {
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
}
