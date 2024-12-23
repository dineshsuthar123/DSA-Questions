package LeetCode_DailyQuestions;

import java.util.*;

public class MinimumOperations {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    static class CycleDetectionSolution {
        public int minimumOperations(TreeNode root) {
            if (root == null) return 0;

            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            int totalSwaps = 0;

            while (!queue.isEmpty()) {
                int size = queue.size();
                int[] values = new int[size];
                int[] sorted = new int[size];

                for (int i = 0; i < size; i++) {
                    TreeNode node = queue.poll();
                    values[i] = node.val;
                    sorted[i] = node.val;

                    if (node.left != null) queue.offer(node.left);
                    if (node.right != null) queue.offer(node.right);
                }

                Arrays.sort(sorted);
                totalSwaps += countMinSwaps(values, sorted);
            }

            return totalSwaps;
        }

        private int countMinSwaps(int[] arr, int[] sorted) {
            int n = arr.length;
            Map<Integer, Integer> positions = new HashMap<>();

            for (int i = 0; i < n; i++) {
                positions.put(arr[i], i);
            }

            boolean[] visited = new boolean[n];
            int swaps = 0;

            for (int i = 0; i < n; i++) {
                if (visited[i] || arr[i] == sorted[i]) continue;

                int cycleSize = 0;
                int j = i;

                while (!visited[j]) {
                    visited[j] = true;
                    j = positions.get(sorted[j]);
                    cycleSize++;
                }

                swaps += cycleSize - 1;
            }

            return swaps;
        }
    }

    static class DirectSwapSolution {
        public int minimumOperations(TreeNode root) {
            Queue<TreeNode> queue = new ArrayDeque<>();
            queue.add(root);
            int count = 0;
            while (!queue.isEmpty()) {
                int len = queue.size();
                int[] data = new int[len];
                int[] dataOrdered = new int[len];
                Map<Integer, Integer> map = new HashMap<>();
                int idx = 0;
                for (int i = 0; i < len; i++) {
                    TreeNode node = queue.poll();
                    if (node.left != null) queue.add(node.left);
                    if (node.right != null) queue.add(node.right);
                    dataOrdered[idx] = node.val;
                    data[idx] = node.val;
                    map.put(node.val, idx);
                    idx++;
                }
                Arrays.sort(dataOrdered);
                for (int i = 0; i < len; i++) {
                    if (data[i] == dataOrdered[i]) continue;
                    int j = map.get(dataOrdered[i]);
                    int tmp = data[i];
                    data[i] = data[j];
                    data[j] = tmp;
                    map.put(tmp, j);
                    count++;
                }
            }
            return count;
        }
    }

    public static void main(String[] args) {
        // Test case 1: [1,4,3,7,6,8,5,null,null,null,null,9,null,10]
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(4);
        root1.right = new TreeNode(3);
        root1.left.left = new TreeNode(7);
        root1.left.right = new TreeNode(6);
        root1.right.left = new TreeNode(8);
        root1.right.right = new TreeNode(5);
        root1.right.left.left = new TreeNode(9);
        root1.right.right.right = new TreeNode(10);

        CycleDetectionSolution solution1 = new CycleDetectionSolution();
        DirectSwapSolution solution2 = new DirectSwapSolution();

        System.out.println("Test Case 1:");
        System.out.println("Cycle Detection Solution: " + solution1.minimumOperations(root1));
        System.out.println("Direct Swap Solution: " + solution2.minimumOperations(root1));
    }
}