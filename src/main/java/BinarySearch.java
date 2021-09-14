import java.util.Arrays;

/**
 * @Author: RookieX
 * @Date: 2021/9/14 7:03 下午
 * @Description: 二分查找
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 4, 5, 7, 9};
        int key = 30;
        System.out.println(BinarySearch(arr, key));
        System.out.println(BinarySearch(arr, key, 1, 9));

    }

    /**
     * @Author: RookieX
     * @Date: 2021/9/14 7:30 下午
     * @param arr
     * @param key
     * @return: int
     * @Description: 不使用递归实现二分查找(使用 while)
     */
    public static int BinarySearch(int[] arr, int key) {

        int min = 0;
        int max = arr.length - 1;
        int mid;
        if (arr.length == 0 || key < arr[0] || key > arr[arr.length - 1]) {
            return -1;
        }
        while (min <= max) {
            mid = (min + max) / 2;
            if (arr[mid] > key) {
                max = mid - 1;
            } else if (arr[mid] < key) {
                min = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    /**
     * @Author: RookieX
     * @Date: 2021/9/14 7:41 下午
     * @param arr
     * @param key
     * @param min
     * @param max
     * @return: int
     * @Description: 使用递归实现二分查找
     */
    public static int BinarySearch(int[] arr, int key, int min, int max) {

        if (arr.length == 0 || key < arr[0] || key > arr[arr.length - 1] || min > max) {
            return -1;
        }
        int mid = (min + max) / 2;
        if (arr[mid] > key) {
            return BinarySearch(arr, key, min, mid - 1);
        } else if (arr[mid] < key) {
            return BinarySearch(arr, key, mid + 1, max);
        } else {
            return mid;
        }
    }
}

