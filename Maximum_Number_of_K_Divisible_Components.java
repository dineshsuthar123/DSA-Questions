package LeetCode_DailyQuestions;

import java.util.*;

public class Maximum_Number_of_K_Divisible_Components {

    public static void main(String[] args) {
        int n = 7;
        int[][] edges = {{0, 1}, {0, 2}, {1, 3}, {1, 4}, {2, 5}, {2, 6}};
        int[] values = {2, 2, 1, 3, 4, 1, 1};
        int k = 3;

        // Test Solution 1
        Solutionfirst sol1 = new Solutionfirst();
        int result1 = sol1.maxKDivisibleComponents(n, edges, values, k);
        System.out.println("Solution 1 Result: " + result1);

        // Test Solution 2
        Solution2 sol2 = new Solution2();
        int result2 = sol2.maxKDivisibleComponents(n, edges, values, k);
        System.out.println("Solution 2 Result: " + result2);
    }
}

class Solutionfirst {
    private List<Integer>[] graph;
    private int[] values;
    private int k;
    private int result;

    @SuppressWarnings("unchecked")
    public int maxKDivisibleComponents(int n, int[][] edges, int[] values, int k) {
        this.values = values;
        this.k = k;
        this.result = 1;

        graph = new List[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }

        dfs(0, -1);
        return result;
    }

    private long dfs(int node, int parent) {
        long sum = values[node];

        for (int child : graph[node]) {
            if (child != parent) {
                long childSum = dfs(child, node);
                if (childSum % k == 0) {
                    result++;
                } else {
                    sum += childSum;
                }
            }
        }

        return sum;
    }
}

class Solution2 {
    private List<List<Integer>> graph;
    private int[] values;
    private int k;

    public int maxKDivisibleComponents(int n, int[][] edges, int[] values, int k) {
        this.values = values;
        this.k = k;

        buildGraph(n, edges);

        Result result = dfs(0, -1);
        return result.components + 1;
    }

    private void buildGraph(int n, int[][] edges) {
        graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            graph.get(u).add(v);
            graph.get(v).add(u);
        }
    }

    private class Result {
        long sum;
        int components;

        Result(long sum, int components) {
            this.sum = sum;
            this.components = components;
        }
    }

    private Result dfs(int node, int parent) {
        long componentSum = values[node];
        int componentsCount = 0;

        for (int neighbor : graph.get(node)) {
            if (neighbor != parent) {
                Result childResult = dfs(neighbor, node);

                if (childResult.sum % k == 0) {
                    componentsCount += childResult.components + 1;
                } else {
                    componentsCount += childResult.components;
                    componentSum += childResult.sum;
                }
            }
        }

        return new Result(componentSum, componentsCount);
    }
}
