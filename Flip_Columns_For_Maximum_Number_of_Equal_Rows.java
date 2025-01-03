package LeetCode_DailyQuestions;

import java.util.*;

public class Flip_Columns_For_Maximum_Number_of_Equal_Rows {
    public static void main(String[] args) {
        Sol12 solution = new Sol12();

        // Example input
        int[][] mat = {
                {0, 1, 0},
                {1, 0, 1},
                {0, 1, 0}
        };

        int result = Solution.maxEqualRowsAfterFlips(mat);
        System.out.println("Maximum Equal Rows After Flips: " + result);
    }
}

class Solution {
    public static int maxEqualRowsAfterFlips(int[][] matrix) {
        Map<String, Integer> freq = new HashMap<>();

        for(int []row : matrix){
            StringBuilder patt = new StringBuilder();
            if(row[0] == 0){
                for(int bit : row) patt.append(bit);
            }
            else{
                for(int bit : row) patt.append(bit ^ 1);
            }
            freq.merge(patt.toString(),1,Integer::sum);
        }
        return Collections.max(freq.values());
    }
}