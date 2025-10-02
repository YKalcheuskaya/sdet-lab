package leetcode;

//SLIDING WINDOW + HASHMAP

public class LongestSubstringNoRepeat {
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


    //public int lengthOfLongestSubstring(String s) {
    //int left = 0;
    //int maxLen = 0;
    // Set<Character> window = new HashSet<>();
    //for (int right = 0; right < s.length; right++) {
   // while (window.contains(s.right)) {
   // window.remove(s.charAt(left));
    // left++;
    //}
    // window.add(s.charAt(right));
    // maxLen = Math.max(maxLen, right - left + 1);
    //}
    // return maxLen;


}
