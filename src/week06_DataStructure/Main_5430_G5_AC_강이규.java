package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main_5430_G5_AC_강이규 {
    static int N;
    static Deque<Integer> q;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            String p = br.readLine();
            N = Integer.parseInt(br.readLine());
            q = new ArrayDeque<>(N);

            // arr 입력 -> 큐
            String line = br.readLine();
            line = line.substring(1, line.length()-1); // [] 제거
            st = new StringTokenizer(line, ",");
            for (int i = 0; i < N; i++) {
                q.offer(Integer.parseInt(st.nextToken()));
            }
            // 로직 실행
            solve(p);
            sb.append("\n");
        }
        System.out.print(sb);
    }

    private static void solve(String p) {
        boolean reverse = false;

        int len = p.length();
        for (int o = 0; o < len; o++) {
            char oper = p.charAt(o);
            if (oper == 'D') {
                if (q.isEmpty()) {
                    sb.append("error");
                    return;
                }
                if (reverse) q.pollLast();
                else q.pollFirst();
            }
            else reverse = !reverse;
        }
        // 결과 출력(길이가 0일 때 : [])
        sb.append("[");

        int size = q.size();
        while (size-- > 0) {
            sb.append(reverse ? q.pollLast() : q.pollFirst()).append(",");
        }
        int sbLen = sb.length();
        if (sb.charAt(sbLen-1) != '[') sb.deleteCharAt(sbLen-1);
        sb.append("]");
    }
}
