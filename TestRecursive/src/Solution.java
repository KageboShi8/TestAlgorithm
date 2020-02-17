/**
 * 递归的细节
 */

public class Solution {
    public static void main(String[] args) {
        int test = 5;
        int result = recursive(test);
        System.out.println("result=" + result);
    }

    private static int recursive(int test) {
        if (test == 0) {
            return test;
        }
        test--;
        System.out.println(test);
        recursive(test);
        test++;
        System.out.println(test+"  ~~~~");
        return test;
    }
}
