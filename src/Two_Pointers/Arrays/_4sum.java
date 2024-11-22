package Two_Pointers.Arrays;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class _4sum{
    public static void main(String []args){
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the number of elements: ");
        int n = sc.nextInt();

        int[] nums = new int[n];
        System.out.println("Enter the elements:");
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }

        System.out.print("Enter the target sum: ");
        int target = sc.nextInt();

        _4sum obj = new _4sum();
        List<List<Integer>> result = obj.fourSum(nums, target);

        System.out.println("Quadruplets that sum to the target:");
        for (List<Integer> quadruplet : result) {
            System.out.println(quadruplet);
        }
    }

    public static List<List<Integer>> fourSum(int[] nums, int target) {

        if (nums.length < 4) {
            return new ArrayList<>();
        }

        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        int size = nums.length;

        for (int i = 0; i < size; i++) {

            if (i > 0 && nums[i - 1] == nums[i]) {
                continue;
            }

            for (int j = i + 1; j < size; j++) {

                if (j != i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }

                int k = j + 1;
                int l = size - 1;

                while (k < l) {
                    long sum = (long) nums[i] + nums[j] + nums[k] + nums[l];

                    if (sum == target) {
                        result.add(Arrays.asList(nums[i], nums[j], nums[k], nums[l]));
                        k++;
                        l--;

                        while (k < l && nums[k] == nums[k - 1]) {
                            k++;
                        }

                        while (k < l && nums[l] == nums[l + 1]) {
                            l--;
                        }
                    } else if (sum < target) {
                        k++;
                    } else {
                        l--;
                    }
                }
            }
        }

        return result;
    }
    }