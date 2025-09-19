package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution3Sum {

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);                  // 1) сортировка
        int n = nums.length;

        for (int i = 0; i < n - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue; // skip dup for i
            int l = i + 1, r = n - 1;                       // 2) два указателя

            while (l < r) {
                int sum = nums[i] + nums[l] + nums[r];
                if (sum < 0) l++;                           // 3) двигаем к 0
                else if (sum > 0) r--;
                else {                                      // 0 найден
                    res.add(Arrays.asList(nums[i], nums[l], nums[r]));
                    while (l < r && nums[l] == nums[l + 1]) l++; // skip dup for l
                    while (l < r && nums[r] == nums[r - 1]) r--; // skip dup for r
                    l++; r--;
                }
            }
        }
        return res;
    }
}