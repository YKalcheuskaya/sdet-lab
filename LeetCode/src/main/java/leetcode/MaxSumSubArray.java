package leetcode;

// SLIDING WINDOW

public class MaxSumSubArray {
    public int maxSubArray(int[] nums) {
        int maxSoFar = nums[0];
        int maxEndingHere = nums[0];

        for (int i = 1; i < nums.length; i++) {
            maxEndingHere = Math.max(nums[i], maxEndingHere + nums[i]);
            maxSoFar = Math.max(maxSoFar, maxEndingHere);
        }

        return maxSoFar;
    }
}


// public class MaxSubArray {
// public int maxSubArray(int[] nums, int k) {
// int windowSum = 0;
// int maxSum = 0;
// for (int i = 0; i < k; i++) {
// windowSum += nums[i];
// }
//maxSum += windowSum;
//for (int i = k; i < nums.length; i++) {
// windowSum += nums[i] - nums[i - k];
// maxSum = Math.max(maxSum, windowSum);
//}
//return maxSum;
//
//
//
//
//
//
//
//
//
