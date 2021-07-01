package com.jarvi;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;

public class LargestKth215 {

    private static final Random random = new Random(System.currentTimeMillis());

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.println(findKthLargest(new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6}, 4));
        }
    }

    /**
     * 排序法
     * 时间复杂度：O(nlogn)
     * 空间复杂度：O(logn)
     */
    public static int findKthLargest(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length - k];
    }

    /**
     * 小顶堆
     * 时间复杂度：O(nlogk)，建堆的时间代价是O(k)，删除的总代价是 O(nlogk)，因为 k < n，故渐进时间复杂为 O(k + nlog k) = O(nlogk)。
     * 空间复杂度：O(k)，队列存放K个元素
     */
    public static int findKthLargest2(int[] nums, int k) {
        Queue<Integer> queue = new PriorityQueue<>();
        for (int num : nums) {
            if(queue.size() < k) {
                queue.offer(num);
            } else if(queue.peek() < num) {
                queue.poll();
                queue.offer(num);
            }
        }
        return queue.peek();
    }

    /**
     * 快速选择
     * 时间复杂度：O(n)
     * 空间复杂度：O(logn)
     */
    public static int findKthLargest1(int[] nums, int k) {
        return quickSelect(nums, k - 1, 0, nums.length - 1);
    }

    private static int quickSelect(int[] nums, int k, int left, int right) {
        int i = left;
        int j = right;
        int randomIndex = random.nextInt(right - left + 1) + left;
        swap(nums, randomIndex, right);
        while(i < j) {
            while(i < j && nums[i] >= nums[right]) i++;
            while(i < j && nums[j] <= nums[right]) j--;
            swap(nums, i, j);
        }
        swap(nums, i, right);
        if(i > k) return quickSelect(nums, k, left, i - 1);
        if(i < k) return quickSelect(nums, k, i + 1, right);
        return nums[i];
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

}
