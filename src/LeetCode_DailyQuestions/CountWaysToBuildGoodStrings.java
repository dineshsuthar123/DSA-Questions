package LeetCode_DailyQuestions;

public class CountWaysToBuildGoodStrings {
    public static void main(String[] args) {
        // Test cases
        int low = 3, high = 3, zero = 1, one = 1;
        StringConstructorDP solution1 = new StringConstructorDP();
        StringConstructorOptimized solution2 = new StringConstructorOptimized();

        System.out.println("Solution 1 output: " + solution1.countGoodStrings(low, high, zero, one));
        System.out.println("Solution 2 output: " + solution2.countGoodStrings(low, high, zero, one));
    }
}

class StringConstructorDP {
    public int countGoodStrings(int low, int high, int zero, int one) {
        int MOD = 1_000_000_007;
        int[] dp = new int[high + 1];
        dp[0] = 1;

        for (int i = 1; i <= high; i++) {
            if (i >= zero) {
                dp[i] = dp[i - zero];
            }
            if (i >= one) {
                dp[i] = (dp[i] + dp[i - one]) % MOD;
            }
        }

        int result = 0;
        for (int i = low; i <= high; i++) {
            result = (result + dp[i]) % MOD;
        }

        return result;
    }
}

class StringConstructorOptimized {

    public int countGoodStrings(int low, int high, int zero, int one) {
        if (zero > one) return countGoodStrings(low, high, one, zero);
        int dp[] = new int[high + 1], res = 0;
        dp[0] = 1;
        for (int i = 1; i <= high; i++) {
            int mod = (int) 1e9 + 7;
            if (i >= zero) dp[i] = (dp[i] + dp[i - zero]) % mod;
            if (i >= one) dp[i] = (dp[i] + dp[i - one]) % mod;
            if (i >= low) res = (res + dp[i]) % mod;
        }
        return res;
    }
}