package LeetCode_DailyQuestions;

import java.util.*;

public class Sliding_Puzzle {
    public static void main(String[] args) {
        Solution4 solution = new Solution4();

        // Example input
        int[][] board = {
                {1, 2, 3},
                {4, 0, 5}
        };

        int result = solution.slidingPuzzle(board);

        System.out.println("Minimum moves to solve the Sliding Puzzle: " + result);
    }
}

class Solution4 {

    public int slidingPuzzle(int[][] board) {
        int[][] directions = new int[][]{{1, 3}, {0, 2, 4}, {1, 5}, {0, 4}, {1, 3, 5}, {2, 4}};
        String finalString = "123450";

        StringBuilder startState = new StringBuilder();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                startState.append(board[i][j]);
            }
        }

        Set<String> visited = new HashSet<>();
        Queue<String> q = new LinkedList<>();
        q.add(startState.toString());
        visited.add(startState.toString());

        int moves = 0;

        while (!q.isEmpty()) {
            int size = q.size();

            for (int i = 0; i < size; i++) {
                String currState = q.poll();

                if (currState.equals(finalString)) {
                    return moves;
                }

                int zeroIdx = currState.indexOf('0');

                for (int newIdx : directions[zeroIdx]) {
                    String nextState = swap(currState, zeroIdx, newIdx);
                    if (visited.contains(nextState)) {
                        continue;
                    }
                    q.add(nextState);
                    visited.add(nextState);
                }
            }

            moves++;
        }

        return -1;
    }

    private String swap(String state, int i, int j) {
        char[] chars = state.toCharArray();
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
        return new String(chars);
    }
}
