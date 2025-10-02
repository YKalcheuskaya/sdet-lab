package leetcode;

import java.util.Deque;
import java.util.ArrayDeque;

public class ValidParentheses {

    public static boolean isValid(String s) {
        Deque<Character> stack = new ArrayDeque<>();           // (1) создаём стек (быстрее, чем Stack)

        for (int i = 0; i < s.length(); i++) {                 // (2) сканируем строку слева направо
            char c = s.charAt(i);                              // (3) берём текущий символ

            if (c == '(') {                                    // (4) если открывающая — кладём ожидаемую закрывающую
                stack.push(')');
            } else if (c == '[') {
                stack.push(']');
            } else if (c == '{') {
                stack.push('}');
            } else {                                           // (5) иначе — у нас закрывающая
                if (stack.isEmpty()) return false;             // (5a) нечем закрывать
                char need = stack.pop();                       // (5b) снимаем то, что ожидали наверху
                if (need != c) return false;                   // (5c) тип скобки не совпал — невалидно
            }
        }

        return stack.isEmpty();                                // (6) всё закрыто? стек должен быть пуст
    }

    public static void main(String[] args) {
        System.out.println(isValid("()[]{}"));   // true
        System.out.println(isValid("([{}])"));   // true
        System.out.println(isValid("(]"));       // false
        System.out.println(isValid("([)]"));     // false
        System.out.println(isValid("((("));      // false
    }
}

