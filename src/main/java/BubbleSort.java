import java.util.Arrays;

/**
 * @Author: RookieX
 * @Date: 2021/9/14 6:44 下午
 * @Description: 冒泡排序
 */
public class BubbleSort {
    public static void main(String[] args) {
        int arr[] = new int[]{1, 2, 3, 6, 7, 5, 4};
        BubbleSort.bubbleSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void bubbleSort(int[] arr) {
        int temp;
        for (int i = 0; i < arr.length - 1; i++) {  //冒泡趟数
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j + 1] < arr[j]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }
}
