/**
 * @Author: RookieX
 * @Date: 2021/9/14 8:17 下午
 * @Description: 快速排序
 */
public class QuickSort {
    public static void quickSort(int[] arr, int low, int high) {
        int i, j, temp, t;
        if (low > high) {
            return;
        }

        i = low;
        j = high;

        //temp就是基准位
        temp = arr[low];

        while (i < j) {
            //先从右边, 依次往左递减
            while (temp <= arr[j] && i < j) {
                j--;
            }
            //再从左边, 依次往右递减
            while (temp >= arr[i] && i < j) {
                i++;
            }
            //如果满足条件就交换
            if (i < j) {
                t = arr[j];
                arr[j] = arr[i];
                arr[i] = t;
            }
        }

        //最后将基准为与 i 和 j 相等的位置的数字交换
        arr[low] = arr[i];
        arr[i] = temp;
        //递归调用左半数组
        quickSort(arr, low, j - 1);
        //递归调用右半数组
        quickSort(arr, j + 1, high);
    }

    public static void main(String[] args) {
        int[] arr = {10, 4, 5, 7, 9, 8, 3, 2};
        quickSort(arr, 0, arr.length - 1);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }
}
