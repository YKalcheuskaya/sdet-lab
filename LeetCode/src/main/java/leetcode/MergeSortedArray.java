package leetcode;
/*
nums1 — отсортированный массив длиной m + n,
в котором первые m элементов — настоящие числа,
а последние n — пустые (нули).
nums2 — отсортированный массив длиной n.
Нужно слить оба массива в один отсортированный массив,
сохранив результат в nums1.
 */

public class MergeSortedArray {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1, j = n - 1, k = m + n - 1;
        while (j >= 0) {
            if (i >= 0 && nums1[i] > nums2[j]) nums1[k--] = nums1[i--];
            else nums1[k--] = nums2[j--];
        }
    }
}
