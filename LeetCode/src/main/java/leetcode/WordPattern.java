package leetcode;
/*
Дана строка-паттерн pattern (напр. "abba") и строка s со словами (напр. "dog cat cat dog").
Нужно проверить, есть ли биекция между символами паттерна и словами:
одна буква ↔ одно слово (в обе стороны уникально), и порядок должен совпадать.
Идея (в одно предложение)
Проходим слова и буквы синхронно и поддерживаем две HashMap:
char→word и word→char. На каждом шаге проверяем согласованность соответствий.
 */

import java.util.HashMap;
import java.util.Map;

public class WordPattern {

    public boolean wordPattern(String pattern, String s) {
        String[] words = s.split(" ");
        if (pattern.length() != words.length) {
            return false;
        }

        java.util.Map<Character, String> charToWord = new java.util.HashMap<>();
        java.util.Map<String, Character> wordToChar = new java.util.HashMap<>();

        for (int i = 0; i < pattern.length(); i++) {
            char c = pattern.charAt(i);
            String w = words[i];

            if (charToWord.containsKey(c)) {
                if (!charToWord.get(c).equals(w)) {
                    return false;
                }
            } else {
                charToWord.put(c, w);
            }

            if (wordToChar.containsKey(w)) {
                if (wordToChar.get(w) != c) {
                    return false;
                }
            } else {
                wordToChar.put(w, c);
            }
        }

        return true;
    }
}


//import java.util.*;
//
//public class WordPattern {
//    public boolean wordPattern(String pattern, String s) {
//        String[] words = s.split(" ");
//        if (pattern.length() != words.length) return false;
//
//        Map<Character, String> c2w = new HashMap<>();
//        Map<String, Character> w2c = new HashMap<>();
//
//        for (int i = 0; i < pattern.length(); i++) {
//            char c = pattern.charAt(i);
//            String w = words[i];
//
//            // если уже есть сопоставление, оно должно совпадать
//            if (c2w.containsKey(c) && !c2w.get(c).equals(w)) return false;
//            if (w2c.containsKey(w) && w2c.get(w) != c)       return false;
//
//            // если ещё нет — создаём
//            c2w.putIfAbsent(c, w);
//            w2c.putIfAbsent(w, c);
//        }
//        return true;
//    }
//}
//
