package LeetCode_DailyQuestions;

import java.util.PriorityQueue;

public class Construct_String_With_Repeat_Limit {

    // PriorityQueue-based solution
    static class PriorityQueueSolution {
        public String repeatLimitedString(String s, int repeatLimit) {
            PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[0] - a[0]);
            int[] freq = new int[26];
            for (char ch : s.toCharArray()) freq[ch - 'a']++;
            for (int i = 0; i < 26; i++) {
                if (freq[i] > 0) pq.offer(new int[]{i, freq[i]});
            }

            StringBuilder res = new StringBuilder();
            while (!pq.isEmpty()) {
                int[] top = pq.poll();
                int charIndex = top[0];
                int count = Math.min(top[1], repeatLimit);

                for (int i = 0; i < count; i++) {
                    res.append((char) (charIndex + 'a'));
                }

                if (top[1] > count) {
                    if (pq.isEmpty()) break;
                    int[] next = pq.poll();
                    res.append((char) (next[0] + 'a'));
                    next[1]--;
                    if (next[1] > 0) pq.offer(next);
                    pq.offer(new int[]{charIndex, top[1] - count});
                }
            }
            return res.toString();
        }
    }

    // Optimized frequency array solution
    static class OptimizedFrequencySolution {
        public String repeatLimitedString(String s, int repeatLimit) {
            int[] freq = new int[26];
            for (char ch : s.toCharArray()) freq[ch - 'a']++;
            StringBuilder res = new StringBuilder();
            int prevChar = -1, prevCount = 0;

            for (int i = 25; i >= 0; i--) {
                while (freq[i] > 0) {
                    if (prevChar == i && prevCount == repeatLimit) {
                        int next = -1;
                        for (int j = i - 1; j >= 0; j--) {
                            if (freq[j] > 0) {
                                next = j;
                                break;
                            }
                        }
                        if (next == -1) return res.toString();
                        res.append((char) (next + 'a'));
                        freq[next]--;
                        prevChar = next;
                        prevCount = 1;
                    } else {
                        res.append((char) (i + 'a'));
                        freq[i]--;
                        if (prevChar == i) prevCount++;
                        else {
                            prevChar = i;
                            prevCount = 1;
                        }
                    }
                }
            }
            return res.toString();
        }
    }

    public static void main(String[] args) {
        String s = "cczazcc";
        int repeatLimit = 3;

        // Using PriorityQueue solution
        PriorityQueueSolution pqSolution = new PriorityQueueSolution();
        String result1 = pqSolution.repeatLimitedString(s, repeatLimit);
        System.out.println("PriorityQueue Solution: " + result1);

        // Using Optimized Frequency solution
        OptimizedFrequencySolution optSolution = new OptimizedFrequencySolution();
        String result2 = optSolution.repeatLimitedString(s, repeatLimit);
        System.out.println("Optimized Frequency Solution: " + result2);
    }
}
