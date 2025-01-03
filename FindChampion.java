package LeetCode_DailyQuestions;


public class FindChampion {
    public static void main(String[] args) {
        ChampionFinder solution = new ChampionFinder();

        // Example input
        int n = 4;
        int[][] edges = {
                {0, 1},
                {1, 2},
                {2, 3}
        };

        int result = solution.findChampion(n, edges);

        System.out.println("Champion: " + result);
    }
}

class ChampionFinder {
    public int findChampion(int n, int[][] edges) {
        int[] inDegree = new int[n];


        for (int[] edge : edges) {
            inDegree[edge[1]]++;
        }

        int champ = -1;
        int count = 0;


        for (int i = 0; i < n; i++) {
            if (inDegree[i] == 0) {
                count++;
                champ = i;
            }
        }

        return count > 1 ? -1 : champ;
    }
}
