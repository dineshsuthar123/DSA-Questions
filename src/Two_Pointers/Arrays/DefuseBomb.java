package Two_Pointers.Arrays;
import java.util.Scanner;

public class DefuseBomb {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the size of the array: ");
        int n = scanner.nextInt();

        int[] code = new int[n];
        System.out.println("Enter the elements of the array:");
        for (int i = 0; i < n; i++) {
            code[i] = scanner.nextInt();
        }

        System.out.print("Enter the value of k: ");
        int k = scanner.nextInt();

        Solution solution = new Solution();
        int[] result = solution.decrypt(code, k);

        // Print the result
        System.out.println("Output: " + java.util.Arrays.toString(result));
    }
}

class Solution {
    public int[] decrypt(int[] code, int k) {
        int []result = new int[code.length];
        if(k == 0) return result;
        int start = 0;
        int end = 0;
        int n = code.length;
        int sum = 0;

        if(k > 0){
            start = 1;
            end = k;
        }
        else{
            start = n - Math.abs(k);
            end = n - 1;
        }
        for(int i =start; i<=end; i++)
            sum += code[i];
        for(int i =0; i<n ; i++){
            result[i] = sum;
            sum = sum  - code[(start)%n];
            sum = sum + code[(end + 1)%n];
            start++;
            end++;
        }
        return result;
    }
}