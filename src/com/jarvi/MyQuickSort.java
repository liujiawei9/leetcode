package com.jarvi;

/**
 * 快速排序
 */
public class MyQuickSort {

    public static void main(String[] args) {
        test(new int[]{7,6,4,3,5,1,9,2});
        test(new int[]{10,7,2,4,7,62,3,4,2,1,8,9,19});
        test(new int[]{6,5,4,3,2,1,0});
        test(new int[]{999});
        test(new int[]{3, 3, 1, 1, 2, 2, 9});
        test(new int[]{});
        test(null);
    }

    private static void test(int[] arr) {
        if (arr == null) {
            return;
        }
        sort(arr);
        for (int a : arr) {
            System.out.print(a + "\t");
        }
        System.out.println();
    }

    public static void sort(int[] nums) {
        if (nums == null) {
            return;
        }
        if (nums.length == 0) {
            return;
        }
        sort(nums, 0, nums.length - 1);
    }

    private static void sort(int[] nums, int left, int right) {
        if (left >= right) {
            return;
        }

        int i = left;
        int j = right;
        while (i < j) {
            while (i < j && nums[right] >= nums[i]) {
                i++;
            }
            while (i < j && nums[right] <= nums[j]) {
                j--;
            }
            swap(nums, i, j);
        }
        swap(nums, i, right);

        sort(nums, left, i - 1);
        sort(nums, i + 1, right);
    }

    private static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

}