package LeetCode_DailyQuestions;

import java.util.Arrays;

public class Rotating_the_Box {
    public static void main(String[] args) {
        Solutionfirst solution = new Solutionfirst();

        // Example input
        char[][] box = {
                {'#', '#', '*', '.', '.'},
                {'#', '#', '#', '*', '.'},
                {'#', '#', '.', '#', '*'}
        };

        char[][] result = Solution1.rotateTheBox(box);

        System.out.println("Rotated Box:");
        for (char[] row : result) {
            System.out.println(Arrays.toString(row));
        }
    }
}

class Solution1 {
    public static char[][] rotateTheBox(char[][] box) {
        int ROWS = box.length;
        int COLS = box[0].length;

        char[][] res = new char[COLS][ROWS];

        for (char[] Fill : res) {
            Arrays.fill(Fill, '.');
        }

        for (int r = 0; r < ROWS; r++) {
            int i = COLS - 1;
            for (int c = COLS - 1; c >= 0; c--) {
                if (box[r][c] == '#') {
                    res[i][ROWS - r - 1] = '#';
                    i--;
                } else if (box[r][c] == '*') {
                    res[c][ROWS - r - 1] = '*';
                    i = c - 1;
                }
            }
        }
        return res;
    }
}
