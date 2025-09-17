package basics;

import java.util.*;

public class WordStats {
    public static Map<String,Integer> frequencies(String text) {
        Map<String,Integer> freq = new HashMap<>();
        for (String w : text.toLowerCase().split("\\W+")) {
            if (w.isEmpty()) continue;
            freq.put(w, freq.getOrDefault(w, 0) + 1);
        }
        return freq;
    }
    public static void main(String[] args) {
        String s = "Java is great, and Java collections are great!";
        Map<String,Integer> f = frequencies(s);
        System.out.println("Frequencies: " + f);
        Set<String> uniques = new HashSet<>(f.keySet());
        System.out.println("Unique words: " + uniques);
        String top = null; int best = -1;
        for (var e : f.entrySet()) if (e.getValue() > best) { best = e.getValue(); top = e.getKey(); }
        System.out.println("Top word: " + top + " -> " + best);
    }
}
