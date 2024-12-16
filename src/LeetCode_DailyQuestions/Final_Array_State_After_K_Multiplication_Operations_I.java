package LeetCode_DailyQuestions;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Final_Array_State_After_K_Multiplication_Operations_I {
    public static void main(String []args){
        Final_Array_State_After_K_Multiplication_Operations_I solution = new Final_Array_State_After_K_Multiplication_Operations_I();

        // Example 1
        int[] nums1 = {2, 1, 3, 5, 6};
        int k1 = 5;
        int multiplier1 = 2;
        System.out.println("Example 1: " + Arrays.toString(solution.Solution1(nums1, k1, multiplier1)));

        // Example 2
        int[] nums2 = {1, 2};
        int k2 = 3;
        int multiplier2 = 4;
        System.out.println("Example 2: " + Arrays.toString(solution.Solution2(nums2, k2, multiplier2)));
    }

    public int[] Solution1(int[] nums, int k, int multiplier) {

        for (int i = 0; i < k; i++) {

            int minIndex = 0;
            for (int j = 1; j < nums.length; j++) {
                if (nums[j] < nums[minIndex]) {
                    minIndex = j;
                }
            }

            nums[minIndex] *= multiplier;
        }

        return nums;
    }

    public int[] Solution2(int[] nums, int k, int multiplier) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                if(a[0] == b[0])
                    return a[1] - b[1];
                return a[0] - b[0];
            }
        });
        for(int i=0; i<nums.length; i++)
            pq.add(new int[]{nums[i], i});

        for(int i=0;i<k;i++) {
            int[] val = pq.poll();
            nums[val[1]] *= multiplier;
            val[0] = nums[val[1]];
            pq.add(val);
        }
        return nums;
    }
}
