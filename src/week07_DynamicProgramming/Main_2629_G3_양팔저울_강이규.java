package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main_2629_G3_양팔저울_강이규 {
    /*
    풀이 :
    - dp[i][j] = i번째 추까지 탐색했을 때, j를 넘지 않는 max sum --> j가 1씩 커질 것이므로, 가능한 모든 조합을 만들 수 있다.
    - 이 때 각 값은 set에 넣어두고,
        1) cur == cur k 인지,
        2) 혹은 cur - cur k가 set에 있는지 검사한다.
    - 1,2 중 하나라도 된다면, YES
    - i = 30, max j = 15,000 (500 * 30)이므로, 연산 -> 45만 정도
     */
    static int N;
    static int[] weights;
    static int M;
    static int maxK;
    static int[] marbles;
    static int[][] dp;
    static Set<Integer> possibles; // 조합 가능한 결과값을 담을 set
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        init();
        dp();
        check();
        System.out.println(sb);
    }

    private static void check() {
        Marble : for (int marble : marbles) {
            for (int possible : possibles) {
                if (possible == marble // 1)
                    || possibles.contains(possible - marble)) { // 2)
                    sb.append("Y ");
                    continue Marble;
                }
            }
            sb.append("N ");
        }
    }

    private static void dp() {
        for (int i = 1; i <= N; i++) { // 추
            int w = weights[i];
            for (int j = 1; j <= maxK; j++) { // 중량
                dp[i][j] = dp[i-1][j];
                if (w <= j) dp[i][j] = Math.max(dp[i-1][j-w] + w, dp[i][j]);
                possibles.add(dp[i][j]);
            }
        }
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        sb = new StringBuilder();
        // 추
        N = Integer.parseInt(br.readLine());
        weights = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            weights[i] = Integer.parseInt(st.nextToken());
        }
        // 구슬
        M = Integer.parseInt(br.readLine());
        marbles = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            marbles[i] = Integer.parseInt(st.nextToken());
        }

        maxK = (500 * N);
        dp = new int[N+1][maxK + 1];
        possibles = new HashSet<>();
    }
}
