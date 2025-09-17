package leetcode;

public class Main {
    public static void main(String[] args) {
        TwoSum solution = new TwoSum();
        TwoOtherSum solution2 = new TwoOtherSum();
        int[] nums = {2, 7, 11, 15};
        int target = 9;
        int[] result = solution.twoSum(nums, target);
        int[] result2 = solution2.twoSum(nums, target);
        System.out.println("Indices: [" + result[0] + ", " + result[1] + "]");
        System.out.println("Indices: [" + result2[0] + ", " + result2[1] + "]");
    }
}
