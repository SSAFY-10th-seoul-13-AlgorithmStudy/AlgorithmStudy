package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_1863_G4_스카이라인쉬운거_강이규 {

    static int N;
    static Stack<Integer> s;
    static int res;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        res = 0;

        s = new Stack<>();
        s.push(0);
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            action(y);
        }
        action(0);
        System.out.println(res);
    }

    private static void action(int y) {
        while (y < s.peek()) {
            s.pop();
            res++;
        }
        if (y != s.peek()) s.push(y);
    }
}
