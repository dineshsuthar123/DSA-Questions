package LeetCode_DailyQuestions;

import java.util.PriorityQueue;
import java.util.Comparator;

class Maximum_Average_Pass_Ratio {
    public static void main(String[] args) {
        // Example input
        int[][] classes = {
                {1, 2},  // Class 1: 1 pass, 2 total
                {3, 5},  // Class 2: 3 pass, 5 total
                {2, 2}   // Class 3: 2 pass, 2 total
        };
        int extraStudents = 2;

        Sol12 solution = new Sol12();

        double result = solution.maxAverageRatio(classes, extraStudents);
        System.out.println("Maximum Average Pass Ratio: " + result);
    }
}

class Sol12 {
    public double maxAverageRatio(int[][] classes, int extraStudents) {
        PriorityQueue<double[]> maxHeap = new PriorityQueue<>(Comparator.comparingDouble(o -> -o[0])); // Max heap by profit
        for (int[] c : classes) {
            double a = c[0], b = c[1];
            maxHeap.offer(new double[]{profit(a, b), a, b});
        }
        while (extraStudents-- > 0) {
            double[] top = maxHeap.poll();
            assert top != null;
            double a = top[1], b = top[2];
            maxHeap.offer(new double[]{profit(a + 1, b + 1), a + 1, b + 1});
        }
        double ans = 0.0;
        while (!maxHeap.isEmpty()) {
            double[] top = maxHeap.poll();
            double a = top[1], b = top[2];
            ans += a / b;
        }
        return ans / classes.length;
    }

    private double profit(double a, double b) {
        return (a + 1) / (b + 1) - a / b;
    }
}
