package leetcode;

public class LongestSubstring {
    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        int[] index = new int[128]; // ASCII size
        int maxLength = 0, left = 0;

        for (int right = 0; right < n; right++) {
            char currentChar = s.charAt(right);
            left = Math.max(index[currentChar], left); // move left pointer
            maxLength = Math.max(maxLength, right - left + 1);
            index[currentChar] = right + 1; // update last seen index
        }

        return maxLength;
    }
}

//import java.util.*;
//
//public class LongestSubstring {
//    public int lengthOfLongestSubstring(String s) {
//        Set<Character> set = new HashSet<>();
//        int left = 0, maxLen = 0;
//
//        for (int right = 0; right < s.length(); right++) {
//            char c = s.charAt(right);
//            while (set.contains(c)) {
//                set.remove(s.charAt(left));
//                left++;
//            }
//            set.add(c);
//            maxLen = Math.max(maxLen, right - left + 1);
//        }
//        return maxLen;
//    }
//}
