package com.jarvi;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 剑指 Offer 40. 最小的k个数
 * <p>
 * 输入整数数组 arr ，找出其中最小的 k 个数。
 * 例如，输入4、5、1、6、2、7、3、8这8个数字，则最小的4个数字是1、2、3、4。
 * <p>
 * 示例 1：
 * 输入：arr = [3,2,1], k = 2
 * 输出：[1,2] 或者 [2,1]
 * <p>
 * 示例 2：
 * 输入：arr = [0,1,2,1], k = 1
 * 输出：[0]
 * <p>
 * 限制：
 * 0 <= k <= arr.length <= 10000
 * 0 <= arr[i] <= 10000
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/zui-xiao-de-kge-shu-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MinKNums40 {


    /**
     * 排序法
     * 复杂度分析
     * 时间复杂度：O(nlogn)，其中 n 是数组 arr 的长度。算法的时间复杂度即排序的时间复杂度。
     * 空间复杂度：O(logn)，排序所需额外的空间复杂度为 O(logn)。
     */
    public int[] getLeastNumbers(int[] arr, int k) {
        if (k == 0 || arr.length == 0) {
            return new int[0];
        }
        Arrays.sort(arr);
        return Arrays.copyOf(arr, k);
    }

    /**
     * 优先队列（大顶堆）
     * 时间复杂度：O(nlogn)
     * 空间复杂度：O(k)
     */
    public int[] getLeastNumbers1(int[] arr, int k) {
        if (k == 0 || arr.length == 0) {
            return new int[0];
        }
        Queue<Integer> queue = new PriorityQueue<>((o1, o2) -> o2 - o1);
        for (int i : arr) {
            if (queue.size() < k) {
                queue.offer(i);
            } else if (queue.peek() > i) {
                queue.poll();
                queue.offer(i);
            }
        }
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = queue.poll();
        }
        return result;
    }

    /**
     * 快速选择法
     * 时间复杂度O(n) ：
     * 其中 n 为数组元素数量；对于长度为 n 的数组执行哨兵划分操作的时间复杂度为O(n) ；
     * 每轮哨兵划分后根据 k 和 i 的大小关系选择递归，由于 i 分布的随机性，则向下递归子数组的平均长度为 n/2；
     * 因此平均情况下，哨兵划分操作一共有  n+ n/2 + n/4 + … + n/n = n+ (n-1)/2 < 2n，即总体时间复杂度为 O(n) 。
     * 空间复杂度 O(logn) ：
     * 划分函数的平均递归深度为 O(logn) 。
     *
     */
    public int[] getLeastNumbers2(int[] arr, int k) {
        if (k >= arr.length) return arr;
        return quickSort(arr, k, 0, arr.length - 1);
    }

    private int[] quickSort(int[] arr, int k, int left, int right) {
        int i = left;
        int j = right;
        while (i < j) {
            while (i < j && arr[j] >= arr[left]) j--;
            while (i < j && arr[i] <= arr[left]) i++;
            swap(arr, i, j);
        }
        swap(arr, i, left);
        if (i > k) return quickSort(arr, k, left, i - 1);
        if (i < k) return quickSort(arr, k, i + 1, right);
        return Arrays.copyOf(arr, k);
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
