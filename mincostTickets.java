package LeetCode_DailyQuestions;
import java.util.*;

public class mincostTickets {
    // Solution 1: Using 365 days DP array
    public int mincostTickets1(int[] days, int[] costs) {
        int[] dp = new int[days[days.length-1] + 1];
        Set<Integer> travelDays = new HashSet<>();
        for(int day : days) travelDays.add(day);

        for(int day = 1; day < dp.length; day++) {
            if(!travelDays.contains(day)) {
                dp[day] = dp[day-1];
                continue;
            }

            dp[day] = Math.min(
                    dp[Math.max(0, day-1)] + costs[0],
                    Math.min(
                            dp[Math.max(0, day-7)] + costs[1],
                            dp[Math.max(0, day-30)] + costs[2]
                    )
            );
        }

        return dp[dp.length-1];
    }

    // Solution 2: Using sliding window with optimal runtime
    public int mincostTickets2(int[] days, int[] costs) {
        int n = days.length;
        int[] dp = new int[n];
        int[] durations = {1, 7, 30};

        for(int i = 0; i < n; i++) {
            dp[i] = costs[0] + (i > 0 ? dp[i-1] : 0);

            for(int j = 1; j < 3; j++) {
                int k = i;
                while(k >= 0 && days[i] - days[k] < durations[j]) k--;
                dp[i] = Math.min(dp[i], costs[j] + (k >= 0 ? dp[k] : 0));
            }
        }
        return dp[n-1];
    }

    public static void main(String[] args) {
        mincostTickets solution = new mincostTickets();

        int[] days1 = {1,4,6,7,8,20};
        int[] costs1 = {2,7,15};
        System.out.println("Solution 1 Test Case 1: " + solution.mincostTickets1(days1, costs1));
        System.out.println("Solution 2 Test Case 1: " + solution.mincostTickets2(days1, costs1));

        int[] days2 = {1,2,3,4,5,6,7,8,9,10,30,31};
        int[] costs2 = {2,7,15};
        System.out.println("Solution 1 Test Case 2: " + solution.mincostTickets1(days2, costs2));
        System.out.println("Solution 2 Test Case 2: " + solution.mincostTickets2(days2, costs2));
    }
}