package LeetCode_DailyQuestions;

public class TargetStringFormation {
    public static void main(String[] args) {
        // Test cases
        String[] words1 = {"acca", "bbbb", "caca"};
        String target1 = "aba";

        String[] words2 = {"abba", "baab"};
        String target2 = "bab";

        String[] words3 = {"dcdcbaddbc", "abcabcdddb", "cdbbccdadc", "bbcddccbcd", "addbcddabc"};
        String target3 = "dcbdbdcc";

        TargetStringCounter solver = new TargetStringCounter();

        System.out.println("Test Case 1: " + solver.numWays(words1, target1));
        System.out.println("Test Case 2: " + solver.numWays(words2, target2));
        System.out.println("Test Case 3: " + solver.numWays(words3, target3));
    }
}
class TargetStringCounter {
    public int numWays(String[] words, String target) {
        final int MOD = 1_000_000_007;
        int m = words[0].length();
        int n = target.length();

        if (n > m) return 0;

        char[] targetArray = target.toCharArray();
        int[][] freq = new int[m][26];

        for (String word : words) {
            for (int j = 0; j < m; j++) {
                freq[j][word.charAt(j) - 'a']++;
            }
        }

        long[] dp = new long[n + 1];
        dp[0] = 1;

        for (int j = 0; j < m; j++) {
            int[] currentFreq = freq[j];
            for (int i = n - 1; i >= 0; i--) {
                int c = targetArray[i] - 'a';
                if (currentFreq[c] > 0) {
                    dp[i + 1] = (dp[i + 1] + dp[i] * currentFreq[c]) % MOD;
                }
            }
        }

        return (int) dp[n];
    }
}
