package Two_Pointers.Arrays;
import java.util.Scanner;

class ContainerWithMostWater{
    public static void main(String []args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int []height = new int[n];
        for (int i = 0; i < n; i++) {
            height[i] = sc.nextInt();
        }
        System.out.println(maxArea(height));
    }

    public static int maxArea(int[] height) {
        int maxArea = 0;
        int left = 0;
        int right = height.length - 1;

        while (left < right) {
            int leftHeight = height[left];
            int rightHeight = height[right];
            int width = right - left;
            int currentArea = Math.min(leftHeight, rightHeight) * width;
            maxArea = Math.max(maxArea, currentArea);
            if (leftHeight < rightHeight) {
                left++;
                while (left < right && height[left] <= leftHeight) {
                    left++;
                }
            } else {
                right--;
                while (left < right && height[right] <= rightHeight) {
                    right--;
                }
            }
        }

        return maxArea;
    }
}