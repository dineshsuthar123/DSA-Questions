package LeetCode_DailyQuestions;
import java.util.*;

class _2559 {
    public static void main(String[] args) {
        String[] words1 = {"aba","bcb","ece","aa","e"};
        int[][] queries1 = {{0,2},{1,4},{1,1}};

        String[] words2 = {"a","e","i"};
        int[][] queries2 = {{0,2},{0,1},{2,2}};

        BasicSolution basicSol = new BasicSolution();
        OptimizedSolution optSol = new OptimizedSolution();

        System.out.println("Test Case 1:");
        System.out.println("Basic Solution: " + Arrays.toString(basicSol.vowelStrings(words1, queries1)));
        System.out.println("Optimized Solution: " + Arrays.toString(optSol.vowelStrings(words1, queries1)));

        System.out.println("\nTest Case 2:");
        System.out.println("Basic Solution: " + Arrays.toString(basicSol.vowelStrings(words2, queries2)));
        System.out.println("Optimized Solution: " + Arrays.toString(optSol.vowelStrings(words2, queries2)));
    }
}

class BasicSolution {
    public int[] vowelStrings(String[] words, int[][] queries) {
        Set<Character> vowels = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u'));
        int[] prefixSum = new int[words.length + 1];

        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            boolean isVowelWord = false;

            if (!word.isEmpty()) {
                char firstChar = word.charAt(0);
                char lastChar = word.charAt(word.length() - 1);
                isVowelWord = vowels.contains(firstChar) && vowels.contains(lastChar);
            }

            prefixSum[i + 1] = prefixSum[i] + (isVowelWord ? 1 : 0);
        }

        int[] result = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int left = queries[i][0];
            int right = queries[i][1];
            result[i] = prefixSum[right + 1] - prefixSum[left];
        }

        return result;
    }
}

class OptimizedSolution {
    public int[] vowelStrings(String[] words, int[][] queries) {
        boolean[] isVowel = new boolean[128];
        isVowel['a'] = true;
        isVowel['e'] = true;
        isVowel['i'] = true;
        isVowel['o'] = true;
        isVowel['u'] = true;

        int[] prefixSum = new int[words.length + 1];
        int sum = 0;

        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            char first = word.charAt(0);
            char last = word.charAt(word.length() - 1);
            if (isVowel[first] && isVowel[last]) {
                sum++;
            }
            prefixSum[i + 1] = sum;
        }

        int[] result = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            result[i] = prefixSum[queries[i][1] + 1] - prefixSum[queries[i][0]];
        }

        return result;
    }
}