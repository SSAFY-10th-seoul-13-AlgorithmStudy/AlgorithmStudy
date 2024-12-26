package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_24337_G3_가희와탑_강이규 {

    static int N, A, B;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());

        solve();
    }

    private static void solve() {
        if (N < A + B - 1) {
            System.out.println(-1);
            System.exit(0);
        }
        int[] res = new int[N];
        Arrays.fill(res, 1);

        if (A == 1) {
            res[0] = B;
            int remain = B - 1;
            int val = remain;
            for (int i = N - remain, end = N; i < end; i++) {
                res[i] = val--;
            }
        } else {
            int idx = N - (A + B - 1);
            int val = 1;

            // A
            int loopCnt = A - 1;
            while (loopCnt-- > 0) {
                res[idx++] = val++;
            }
            // K
            res[idx++] = Math.max(A, B);
            // B
            val = B - 1;
            loopCnt = B - 1;
            while (--loopCnt > 0) {
                res[idx++] = val--;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int a : res) {
            sb.append(a).append(" ");
        }
        System.out.println(sb);
    }
}
