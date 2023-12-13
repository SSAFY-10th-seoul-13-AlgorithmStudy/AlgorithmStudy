package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main_3015_P5_오아시스재결합_강이규 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        Stack<Pair> s = new Stack<>();
        long res = 0;

        for (int i = 0; i < n; i++) {
            int input = Integer.parseInt(br.readLine());
            Pair cur = new Pair(input, 1);

            while (!s.isEmpty() && s.peek().height <= input) {
                Pair pop = s.pop();
                res += pop.cnt;

                if (pop.height == input) cur.cnt += pop.cnt;
            }
            if (!s.isEmpty()) res++;

            s.push(cur);
        }
        System.out.println(res);
    }

    static class Pair {
        int height, cnt;
        Pair(int height, int cnt) {
            this.height = height;
            this.cnt = cnt;
        }
    }
}
