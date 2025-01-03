package LeetCode_DailyQuestions;

public class Max_Chunks_To_Make_Sorted {

    // Prefix-based Linear Solution
    public static int maxChunksToSortedCustom(int[] arr) {
        if (arr == null || arr.length == 0) return 0;

        int[] max = new int[arr.length];
        max[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            max[i] = Math.max(max[i - 1], arr[i]);
        }

        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (max[i] == i) {
                count++;
            }
        }

        return count;
    }

    // Clean greedy implementation
    public static int maxChunksToSortedGreedy(int[] arr) {
        int chunks = 0, max = 0;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
            if (max == i) chunks++;
        }
        return chunks;
    }

    public static void main(String[] args) {
        int[] arr = {4, 3, 2, 1, 0};

        // Test Prefix-based Linear Solution
        int resultCustom = maxChunksToSortedCustom(arr);
        System.out.println("Result using custom implementation: " + resultCustom);

        // Test greedy implementation
        int resultGreedy = maxChunksToSortedGreedy(arr);
        System.out.println("Result using greedy implementation: " + resultGreedy);
    }
}
