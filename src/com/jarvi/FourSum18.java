package com.jarvi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FourSum18 {

    public static void main(String[] args) {
        //[[-4,0,1,2],[-1,-1,0,1]]
        System.out.println(fourSum(new int[]{-1, 0, 1, 2, -1, -4}, -1));
        //[[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
        System.out.println(fourSum(new int[]{1, 0, -1, 0, -2, 2}, 0));
        // [[-5,-4,-3,1]]
        System.out.println(fourSum(new int[]{1, -2, -5, -4, -3, 3, 3, 5}, -11));
    }

    public static List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> results = new ArrayList<>();
        if (nums == null || nums.length < 4) {
            return results;
        }
        Arrays.sort(nums);
        for (int a = 0; a < nums.length - 3; a++) {
            if (nums[a] + nums[a + 1] + nums[a + 2] + nums[a + 3] > target) {
                break;
            }
            if (a > 0 && nums[a] == nums[a - 1]) {
                continue;
            }
            for (int b = a + 1; b < nums.length - 2; b++) {
                if (nums[a] + nums[b] + nums[b + 1] + nums[b + 2] > target) {
                    break;
                }
                if (b > a + 1 && nums[b] == nums[b - 1]) {
                    continue;
                }
                int c = b + 1;
                int d = nums.length - 1;
                while (c < d) {
                    int sum = nums[a] + nums[b] + nums[c] + nums[d];
                    if (sum == target) {
                        results.add(Arrays.asList(nums[a], nums[b], nums[c], nums[d]));
                        while (c < d && nums[c] == nums[++c]) ;
                        while (c < d && nums[d] == nums[--d]) ;
                    } else if (sum > target) {
                        while (c < d && nums[d] == nums[--d]) ;
                    } else {
                        while (c < d && nums[c] == nums[++c]) ;
                    }
                }
            }
        }
        return results;
    }


}
