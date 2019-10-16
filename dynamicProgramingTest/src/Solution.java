import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) {
        //跳台阶问题
        //int result = jumpFloor(4);
        //int result = jumpFloorExtra(4);

        //数组最大递增子序列; 方法0是我写的，方法1是动态规划
//        int[] testArray = new int[]{4, 1, 5, 9, 2, 6, 5};
//        int result = maxChildArray1(testArray);

        //数组最大连续子序列和
//        int[] testArray = new int[]{6, -1, 3, -4, -6, 9, 2, -2, 5};
//        int result = maxContinueArraySum(testArray);

        //数字塔中最大路径
//        int[][] ints = initNumberTower();
//        int result = maxNumInRotateArray3(ints);

        //最大公共子串问题 此处的result是最大字串长度
//        String s1 = "BDCABA";
//        String s2 = "ABCBDAB";
//        int result = maxArraySameOrderMethod(s1, s2);

        //背包问题，这个问题极其常见，在此详述一下,背包问题一般来说分3种，0-1背包，多重背包，完全背包
        // 在N件物品取出若干件放在容量为W的背包里，每件物品的体积为W1，W2……Wn（Wi为整数），与之相对应的价值为P1,P2……Pn（Pi为整数），求背包能够容纳的最大价值。取出物品不限制次数，此为完全背包；
//        int[] w = new int[]{2, 3, 4};
//        int[] p = new int[]{3, 5, 7};
//        int W = 10;
//        int N = 3;
//        int[] num = new int[]{5, 5, 5};
        //完全背包
//        int result = packageHelper1(w, p, W, N);
        //多重背包
//        int result = packageHelper2(w, p, W, N, num);
        //0-1背包
//        int result = packageHelper3(w, p, W, N);

        //找零钱问题，这个基本和背包问题差不多
//        int[] value = new int[]{1, 1, 2, 2, 5};
//        int num = 5;
//        int result = smallMoney(value, num);

        //最长回文串
        String str = "ABCBD";
        int result = LongestPalindrome(str);

        System.out.println("result=" + result);

    }


    //跳上n级台阶，每次只能跳1层或者2层，有多少跳法；
    private static int jumpFloor(int number) {
        if (number == 1) {
            return 1;
        }
        if (number == 2) {
            return 2;
        }
        if (number > 2) {
            return jumpFloor(number - 1) + jumpFloor(number - 2);
        }
        return -1;
    }

    //跳上n级台阶，每次可以调1层，2层，甚至是n层，有多少跳法；
    private static int jumpFloorExtra(int number) {
        if (number == 1) {
            return 1;
        }
        if (number > 1) {
            return 2 * jumpFloorExtra(number - 1);
        }
        return -1;
    }

    //最大不连续递增子序列
    private static int maxChildArray0(int[] testArray) {
        int len = testArray.length;
        int tempResult = 1;
        int result = 1;
        for (int i = 0; i < len; i++) {
            int tempMax = testArray[i];
            result = Math.max(tempResult, result);
            tempResult = 1;
            for (int j = i + 1; j < len; j++) {
                if (tempMax < testArray[j]) {
                    tempMax = testArray[j];
                    tempResult++;
                }
            }
        }

        return result;
    }

    //此处的动态规划的思路很标准，提供一个一维（有些情况是二维）数组temp[i],这个数组记录每一个位置的最大递增子序列的长度，temp[i]和temp[i+1]很明显
    //是有关系的，毕竟随着i的递增，最大递增子序列最多只能大1，也就是说，只需要和前述分别比大小就好了，若然大，就比较此处temp是否够大，不够大，就更新temp，够大就不变，
    //此题的状态方程过于奇怪，temp[i]=max{temp[i-1], temp[i-1]+1}，就不在此赘述了。
    private static int maxChildArray1(int[] testArray) {
        int len = testArray.length;
        int[] temp = new int[len];
        for (int i = 0; i < len; i++) {
            temp[i] = 1;//初始值都为1
        }
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < i; j++) {
                if (testArray[i] > testArray[j] && temp[j] + 1 > temp[i]) {
                    temp[i] = temp[j] + 1;
                }
            }
        }
        //取数组中最大值
        int max = temp[0];
        //从temp数组里取出最大的值
        for (int i = 1; i < len; i++) {
            if (temp[i] > max) {
                max = temp[i];
            }
        }
        return max;
    }

    //最大连续子序列和 此处的动态规划就很简单了，抓住了下一个数只要加上不如上一个和就行了，基本和求最大值差不多，连temp[i]都不用更新。
    private static int maxContinueArraySum(int[] testArray) {
        int max = testArray[0];
        int sum = testArray[0];
        for (int i = 1; i < testArray.length; i++) {
            sum = Math.max(sum + testArray[i], testArray[i]);
            if (sum >= max) {
                max = sum;
            }
        }
        return max;
    }

    private static int[][] initNumberTower() {
        int[][] ints = new int[5][5];
        ints[0][0] = 3;
        ints[1][0] = 1;
        ints[1][1] = 5;
        ints[2][0] = 8;
        ints[2][1] = 4;
        ints[2][2] = 3;
        ints[3][0] = 2;
        ints[3][1] = 6;
        ints[3][2] = 7;
        ints[3][3] = 9;
        ints[4][0] = 6;
        ints[4][1] = 2;
        ints[4][2] = 3;
        ints[4][3] = 5;
        ints[4][4] = 1;
        return ints;
    }

    //数字塔最大路径和（只能走正下方或者右方） 比较好理解的二维数组解法，当然其也有一维数组解法，所有的二维数组解法都有滚动一维数组解法的替代。
    //这两种解法都是从上往下分析的方法
    private static int maxNumInRotateArray0(int[][] testArray) {
        int max = 0;
        int[][] dp = new int[testArray.length][testArray.length];

        for (int i = 1; i < testArray.length; i++) {
            for (int j = 0; j <= i; j++) {
                if (j == 0) {
                    dp[i][j] = dp[i - 1][j] + testArray[i][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j - 1], dp[i - 1][j]) + testArray[i][j];
                }
                max = Math.max(dp[i][j], max);
            }
        }
        return max;
    }

    //这里j的顺序一定要是倒叙，要不然会让每个地方的j前的重复叠加，使得temp[i]值完全不准；
    private static int maxNumInRotateArray1(int[][] testArray) {
        int[] temp = new int[testArray.length];
        temp[0] = testArray[0][0];
        for (int i = 1; i < testArray.length; i++) {
            for (int j = i; j >= 0; j--) {
                if (j == i) {
                    temp[i] = temp[i - 1] + testArray[i][j];
                } else if (j == 0) {
                    temp[0] = temp[0] + testArray[i][0];
                } else {
                    temp[j] = Math.max(temp[j], temp[j - 1]) + testArray[i][j];
                }
            }
        }

        int max = temp[0];
        for (int i = 1; i < testArray.length; i++) {
            if (temp[i] > max)
                max = temp[i];
        }
        return max;
    }

    //从下往上的分析方法 二维数组
    private static int maxNumInRotateArray2(int[][] testArray) {
        int[][] dp = new int[testArray.length][testArray.length];
        for (int j = 0; j < testArray.length; j++) {
            dp[testArray.length - 1][j] = testArray[testArray.length - 1][j];
        }
        for (int i = testArray.length - 2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                dp[i][j] = Math.max(dp[i + 1][j + 1], dp[i + 1][j]) + testArray[i][j];
            }
        }
        return dp[0][0];
    }

    //从下往上的分析方法 一维数组
    private static int maxNumInRotateArray3(int[][] testArray) {
        int temp[] = new int[testArray.length];
        for (int i = 0; i < testArray.length; i++) {
            temp[i] = testArray[testArray.length - 1][i];
        }
        for (int i = testArray.length - 2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                temp[j] = Math.max(temp[j], temp[j + 1]) + testArray[i][j];
            }
        }
        return temp[0];


    }

    //最大公共子串问题 其实这个最长子串没什么意思，因为不是连续最长子串，其结果没有什么意义，就和上面的题一摸一样了，只是在设计二维数组的时候有一些小技巧而已。
    private static int maxArraySameOrderMethod(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();

        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            dp[i][0] = 0;
        }
        for (int i = 0; i <= n; i++) {
            dp[0][i] = 0;
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                }
            }
        }
        return dp[m][n];
    }

    //背包问题，w指单个体积，p指单个价值，v指总体积,n指个数,此为完全背包
    private static int packageHelper1(int[] weight, int[] value, int v, int n) {
        int[][] dp = new int[n + 1][v + 1];
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < v + 1; j++) {
                if (j < weight[i - 1]) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - weight[i - 1]] + value[i - 1]);
                }
            }
        }
        return dp[n][v];

    }

    private static int packageHelper1_1(int[] weight, int[] value, int v, int n) {
        int[] dp = new int[v + 1];
        for (int i = 0; i < n + 1; i++) {
            for (int j = 0; j < v + 1; j++) {
                dp[j] = Math.max(dp[j], dp[j - weight[i - 1]] + value[i - 1]);
            }
        }
        return dp[v];


    }

    //背包问题，w指单个体积，p指单个价值，num指单个可使用的次数，v指总体积，n指个数，此为多重背包
    private static int packageHelper2(int[] weight, int[] value, int v, int n, int num[]) {
        int[][] dp = new int[n + 1][v + 1];
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < v + 1; j++) {
                if (j < weight[i - 1]) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    //这里是次数判断，在二维数组中每一横行都是一种材料的堆叠，所以直接除就可以得到用了几次这种材料，再与限制次数相比较，得到合适的值
                    int maxV = Math.min(num[i - 1], j / weight[i - 1]);
                    //这里是将每一个合适的值都尝试了一下，毕竟，需要的是最大值，k最大不代表dp[i][j]最大
                    for (int k = 0; k < maxV + 1; k++) {
                        dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - k * weight[i - 1]] + k * value[i - 1]);
                    }
                }
            }
        }
        return dp[n][v];
    }

    //这个0-1背包和完全背包有啥区别？看来得等到题目的时候才会了，dp[i][j]=dp[i][j−a[i]] 而不是dp[i−1][j−a[i]]，这是完全背包和01背包的差别。因为每个都可以重复加入。
    //0-1背包的问题在于获得dp[i][j]的时候不能用本行的i，要用上一行i的数组值，因为自己的值已经不能再次被使用了，切中了每个i只能用一次的要求。
    private static int packageHelper3(int[] weight, int[] value, int v, int n) {
        int[][] dp = new int[n + 1][v + 1];
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < v + 1; j++) {
                if (j < weight[i - 1]) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - weight[i - 1]] + value[i - 1]);
                }
            }
        }

        return 0;
    }

    //找零钱，返回找零钱的可能数目,完全背包问题
    private static int smallMoney(int[] num, int target) {
        int n = num.length;
        int[][] dp = new int[n + 1][target + 1];
        for (int i = 0; i < n; i++) {
            dp[i][0] = 1;
        }
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < target + 1; j++) {
                if (j >= num[i - 1]) {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - num[i - 1]];
                    System.out.println("dp[" + i + "][" + j + "]=" + dp[i][j]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        return dp[n][target];
    }

    //最长回文串
    private static int LongestPalindrome(String str) {
        int n = str.length();
        int max = 1;
        boolean[][] dp = new boolean[n][n];
        for (int r = 1; r < n; r++) {
            for (int l = 0; l < r; l++) {
                if (str.charAt(r) == str.charAt(l) && (dp[l + 1][r - 1]) || r - l <= 2) {
                    dp[l][r] = true;
                    max = Math.max(r - l + 1, max);
                } else {

                }
            }
        }
        return max;
    }
}
