package Two_Pointers.Arrays;
import java.util.Scanner;

class LongestPalindromicSubstring {
    public static void main(String []args){
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        System.out.println(longestPalindrome(s));
    }
    public static String longestPalindrome(String s) {
        if(s.length() <= 1){
            return s;
        }
        String maxstr = s.substring(0,1);
        for(int i =0; i<s.length();i++){
            String odd = checkCenter(s,i,i);
            String even = checkCenter(s,i,i + 1);

            if(odd.length() > maxstr.length()){
                maxstr = odd;
            }

            if(even.length() > maxstr.length()){
                maxstr = even;
            }
        }
        return maxstr;
    }
    private static String checkCenter(String str ,int left, int right){
        while(left >= 0 && right < str.length() && str.charAt(left) == str.charAt(right)){
            left--;
            right++;
        }
        return str.substring(left+1,right);
    }
}