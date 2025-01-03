package LeetCode_DailyQuestions;

import java.util.Queue;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;

public class Reverse_Odd_Levels_of_Binary_Tree {

    // Definition for a binary tree node
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

    // Solution 1: BFS Approach (Less Efficient)
    // Time: O(n), Space: O(n)
    public TreeNode reverseOddLevelsBFS(TreeNode root) {
        if (root == null) {
            return root;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int level = 0;

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            List<TreeNode> levelNodes = new ArrayList<>();

            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                levelNodes.add(node);

                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }

            if (level % 2 == 1) {
                int left = 0;
                int right = levelNodes.size() - 1;

                while (left < right) {
                    TreeNode leftNode = levelNodes.get(left);
                    TreeNode rightNode = levelNodes.get(right);

                    int temp = leftNode.val;
                    leftNode.val = rightNode.val;
                    rightNode.val = temp;

                    left++;
                    right--;
                }
            }

            level++;
        }

        return root;
    }

    // Solution 2: DFS Approach (Optimal Solution)
    // Time: O(n), Space: O(log n)
    public TreeNode reverseOddLevelsDFS(TreeNode root) {
        if (root == null) return root;
        dfs(root.left, root.right, 1);
        return root;
    }

    private void dfs(TreeNode node1, TreeNode node2, int level) {
        if (node1 == null || node2 == null) return;

        if (level % 2 == 1) {
            int temp = node1.val;
            node1.val = node2.val;
            node2.val = temp;
        }

        dfs(node1.left, node2.right, level + 1);
        dfs(node1.right, node2.left, level + 1);
    }

    public static void main(String[] args) {
        Reverse_Odd_Levels_of_Binary_Tree solution = new Reverse_Odd_Levels_of_Binary_Tree();

        // test tree: [2,3,5,8,13,21,34]
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(3);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(8);
        root.left.right = new TreeNode(13);
        root.right.left = new TreeNode(21);
        root.right.right = new TreeNode(34);

        System.out.println("Testing BFS Solution:");
        TreeNode result1 = solution.reverseOddLevelsBFS(root);

        System.out.println("\nTesting DFS Solution:");
        TreeNode result2 = solution.reverseOddLevelsDFS(root);
    }
}