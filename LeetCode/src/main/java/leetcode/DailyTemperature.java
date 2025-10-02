package leetcode;

import java.util.Deque;
import java.util.ArrayDeque;

public class DailyTemperature {

    public static int[] dailyTemperatures(int[] T) {
        int n = T.length;
        int[] ans = new int[n];                 // (1) по умолчанию нули
        Deque<Integer> st = new ArrayDeque<>(); // (2) стек индексов с убывающими температурами

        for (int i = 0; i < n; i++) {           // (3) идём слева направо
            while (!st.isEmpty() && T[i] > T[st.peek()]) { // (4) нашли более тёплый день для вершины
                int j = st.pop();               // (4a) день, для которого ждём повышение
                ans[j] = i - j;                 // (4b) разница дней — ответ для j
            }
            st.push(i);                         // (5) текущий день пока ждать будущего более тёплого
        }
        return ans;                             // (6) оставшиеся индексы уже 0 — нет более тёплого
    }

    public static void main(String[] args) {
        int[] T = {73,74,75,71,69,72,76,73};
        int[] res = dailyTemperatures(T);
        // Ожидаемо: [1,1,4,2,1,1,0,0]
        for (int x : res) System.out.print(x + " ");
    }
}
