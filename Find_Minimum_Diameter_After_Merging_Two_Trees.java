package LeetCode_DailyQuestions;

import java.util.*;

public class Find_Minimum_Diameter_After_Merging_Two_Trees {

    public static void main(String[] args) {
        LongestDiameter solution = new LongestDiameter();

        // Example inputs
        int[][] edges1 = {{0, 1}, {1, 2}, {1, 3}};
        int[][] edges2 = {{0, 1}, {1, 2}, {2, 3}, {3, 4}};

        int result = solution.minimumDiameterAfterMerge(edges1, edges2);

        System.out.println("Minimum Diameter After Merging Two Trees: " + result);
    }
}

class LongestDiameter {
    public int minimumDiameterAfterMerge(int[][] edges1, int[][] edges2) {
        int d1 = diameter(edges1);
        int d2 = diameter(edges2);
        return Math.max(Math.max(d1 - 1, d2 - 1), d1 / 2 + 1 + d2 / 2);
    }

    private int diameter(int[][] edge) {
        List<List<Integer>> map = new ArrayList<>();
        for (int i = 0; i < edge.length + 1; i++) {
            map.add(new ArrayList<>());
        }

        for (int[] Edge : edge) {
            int i = Edge[0];
            int j = Edge[1];
            map.get(i).add(j);
            map.get(j).add(i);
        }

        int[] res = {0};
        dfs(0, -1, map, res);
        return res[0];
    }

    private int dfs(int node, int parent, List<List<Integer>> map, int[] res) {
        int maxDepth = 1;
        for (int neighbor : map.get(node)) {
            if (neighbor == parent) continue;
            int depth = dfs(neighbor, node, map, res);
            res[0] = Math.max(res[0], maxDepth + depth);
            maxDepth = Math.max(maxDepth, 1 + depth);
        }
        return maxDepth;
    }
}
