package LeetCode_DailyQuestions;

public class Maximum_Matrix_Sum {
    public static void main(String[] args) {
        Solution solution = new Solution();

        int[][] matrix1 = {
                {1, 2, 3},
                {-1, -2, -3},
                {1, 2, 3}
        };
        System.out.println("Max Matrix Sum: " + Solution3.maxMatrixSum(matrix1)); // Example Output: 45

        int[][] matrix2 = {
                {-1, -2},
                {-3, -4}
        };
        System.out.println("Max Matrix Sum: " + Solution3.maxMatrixSum(matrix2)); // Example Output: 9

        int[][] matrix3 = {
                {0, -2, 3},
                {-1, 4, -5},
                {6, 7, -8}
        };
        System.out.println("Max Matrix Sum: " + Solution3.maxMatrixSum(matrix3)); // Example Output: 36
    }
}

class Solution3 {
    public static long maxMatrixSum(int[][] matrix) {
        int min = Integer.MAX_VALUE;
        long sum = 0;
        int negCount = 0;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] < 0)
                    negCount++;
                int absValue = Math.abs(matrix[i][j]);
                min = Math.min(min, absValue);
                sum += absValue;
            }
        }

        if (negCount % 2 == 0)
            return sum;

        return sum - 2L * min;
    }
}
