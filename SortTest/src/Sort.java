import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * 此篇讨论排序
 * sort1 归并排序
 */


public class Sort {
    public static void main(String[] args) {
        int[] sss = new int[]{5, 4, 6, 2};
        int[] result = sort1(sss);
        for (int i = 0; i < result.length; i++) {
            System.out.println(result[i]);
        }
    }

    //归并排序
    private static int[] sort1(int[] sss) {
        mergeSort(sss, 0, sss.length - 1);
        return sss;
    }

    private static void mergeSort(int[] sss, int l, int r) {
        int mid = (l + r) / 2;
        if (l < r) {
            mergeSort(sss, l, mid);
            mergeSort(sss, mid + 1, r);
            merge(sss, l, mid, r);
        }
    }

    private static void merge(int[] a, int low, int mid, int high) {
//        int[] temp = new int[high - low + 1];
//        int i = low;// 左指针
//        int j = mid + 1;// 右指针
//        int k = 0;
//        // 把较小的数先移到新数组中
//        while (i <= mid && j <= high) {
//            if (a[i] < a[j]) {
//                temp[k++] = a[i++];
//            } else {
//                temp[k++] = a[j++];
//            }
//        }
//        // 把左边剩余的数移入数组
//        while (i <= mid) {
//            temp[k++] = a[i++];
//        }
//        // 把右边边剩余的数移入数组
//        while (j <= high) {
//            temp[k++] = a[j++];
//        }
//        // 把新数组中的数覆盖nums数组
//        for (int k2 = 0; k2 < temp.length; k2++) {
//            a[k2 + low] = temp[k2];
//        }
        int[] temp = new int[high - low + 1];
        int l = low;
        int r = mid + 1;
        int k = 0;
        while (l <= mid && r <= high) {
            temp[k++] = a[l] < a[r] ? a[l++] : a[r++];
        }
        while (l <= mid) {
            temp[k++] = a[l++];
        }
        while (r <= high) {
            temp[k++] = a[r++];
        }
        for (int i = 0; i < temp.length; i++) {
            a[i + low] = temp[i];
        }
    }


}
