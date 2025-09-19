package leetcode;

import java.util.HashMap;
import java.util.Map;

public class SolutionLongestSubstring {

    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        int maxLength = 0;
        java.util.Map<Character, Integer> charIndexMap = new java.util.HashMap<>();
        int left = 0;

        for (int right = 0; right < n; right++) {
            char currentChar = s.charAt(right);
            if (charIndexMap.containsKey(currentChar)) {
                left = Math.max(left, charIndexMap.get(currentChar) + 1);
            }
            charIndexMap.put(currentChar, right);
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }

}

//import java.util.*;
//
//class SolutionLongestSubstring {
//    public int lengthOfLongestSubstring(String s) {
//        Map<Character, Integer> lastSeen = new HashMap<>();
//        int l = 0, maxLen = 0;
//
//        for (int r = 0; r < s.length(); r++) {
//            char c = s.charAt(r);
//            if (lastSeen.containsKey(c) && lastSeen.get(c) >= l) {
//                l = lastSeen.get(c) + 1;
//            }
//            lastSeen.put(c, r);
//            maxLen = Math.max(maxLen, r - l + 1);
//        }
//        return maxLen;
//    }
//}


