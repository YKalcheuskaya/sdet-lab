package leetcode;

import java.util.HashMap;
import java.util.Map;

public class SubArraySumEqualsK {
    public int subArraySum(int[] nums, int k) {
        int count = 0;
        for (int start = 0; start < nums.length; start++) {
            int sum = 0;
            for (int end = start; end < nums.length; end++) {
                sum += nums[end];
                if (sum == k) {
                    count++;
                }
            }
        }
        return count;
    }
}


//import java.util.*;
//
//public class SubarraySumEqualsK {
//    public int subarraySum(int[] nums, int k) {
//        Map<Integer, Integer> freq = new HashMap<>();
//        freq.put(0, 1);              // пустой префикс
//        int sum = 0, count = 0;
//
//        for (int x : nums) {
//            sum += x;
//            count += freq.getOrDefault(sum - k, 0);
//            freq.put(sum, freq.getOrDefault(sum, 0) + 1);
//        }
//        return count;
//    }
//}
//
