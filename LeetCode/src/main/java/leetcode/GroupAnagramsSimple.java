package leetcode;

public class GroupAnagramsSimple {
    public static void main(String[] args) {
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        System.out.println(groupAnagrams(strs));
    }

    public static java.util.List<java.util.List<String>> groupAnagrams(String[] strs) {
        java.util.Map<String, java.util.List<String>> map = new java.util.HashMap<>();

        for (String s : strs) {
            char[] chars = s.toCharArray();
            java.util.Arrays.sort(chars);
            String key = new String(chars);

            if (!map.containsKey(key)) {
                map.put(key, new java.util.ArrayList<>());
            }
            map.get(key).add(s);
        }

        return new java.util.ArrayList<>(map.values());
    }

}
