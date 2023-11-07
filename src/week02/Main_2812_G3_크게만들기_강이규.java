package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_2812_G3_크게만들기_강이규 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        String line = br.readLine();
        for (int i = 0; i < N; i++) {
            arr[i] = line.charAt(i) - '0';
        }
        Stack<Integer> s = new Stack<>();
        int idx = 0;
        while (idx < N) {
            int cur = arr[idx];
            if (!s.isEmpty() && cur > s.peek() && K > 0) {
                s.pop();
                K--;
                continue;
            }
            s.push(cur);
            idx++;
        }
        int size = s.size();
        size -= K;
        Stack<Integer> s2 = new Stack<>();
        while (!s.isEmpty()) {
            s2.push(s.pop());
        }

        StringBuilder sb = new StringBuilder();
        while (!s2.isEmpty() && size-- > 0) {
            sb.append(s2.pop());
        }
        System.out.println(sb);
    }
}
