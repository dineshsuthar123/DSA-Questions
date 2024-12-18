package LeetCode_DailyQuestions;

import java.util.ArrayDeque;
import java.util.Deque;

public class Final_Prices_With_a_Special_Discount_in_a_Shop {

    // Brute Force Solution
    public int[] finalPricesBruteForce(int[] prices) {
        int n = prices.length;
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            if (i == n - 1) {
                result[i] = prices[i];
                break;
            }
            for (int j = i + 1; j < n; j++) {
                if (prices[j] <= prices[i]) {
                    result[i] = prices[i] - prices[j];
                    break;
                }
                result[i] = prices[i];
            }
        }
        return result;
    }

    // Monotonic Stack Solution
    public int[] finalPricesMonotonicStack(int[] prices) {
        int n = prices.length;
        int[] result = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && prices[stack.peek()] >= prices[i]) {
                int index = stack.pop();
                result[index] = prices[index] - prices[i];
            }
            stack.push(i);
        }

        while (!stack.isEmpty()) {
            int index = stack.pop();
            result[index] = prices[index];
        }

        return result;
    }

    // Optimized In-Place Solution
    public int[] finalPricesOptimized(int[] prices) {
        int n = prices.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (prices[j] <= prices[i]) {
                    prices[i] -= prices[j];
                    break;
                }
            }
        }
        return prices;
    }

    public static void main(String[] args) {
        Final_Prices_With_a_Special_Discount_in_a_Shop obj = new Final_Prices_With_a_Special_Discount_in_a_Shop();
        int[] prices = {8, 4, 6, 2, 3};

        // Test Brute Force Solution
        int[] result1 = obj.finalPricesBruteForce(prices.clone());
        System.out.println("Brute Force Result:");
        for (int price : result1) {
            System.out.print(price + " ");
        }
        System.out.println();

        // Test Monotonic Stack Solution
        int[] result2 = obj.finalPricesMonotonicStack(prices.clone());
        System.out.println("Monotonic Stack Result:");
        for (int price : result2) {
            System.out.print(price + " ");
        }
        System.out.println();

        // Test Optimized In-Place Solution
        int[] result3 = obj.finalPricesOptimized(prices.clone());
        System.out.println("Optimized In-Place Result:");
        for (int price : result3) {
            System.out.print(price + " ");
        }
        System.out.println();
    }
}
