package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_1082_G3_방번호_강이규 {

    static int N, M;
    static int[] cost;
    static String[][] dp; // dp[i][j] = i원으로 만들 수 있는 가장 큰 j자리 수

    public static void main(String[] args) throws IOException {
        init();
        dp();
        System.out.println(getResult());
    }

    private static String getResult() {
        int J = dp[0].length - 1;
        for (int j = J; j > 0; j--) {
            for (int i = M; i > 0; i--) {
                if (!dp[i][j].isEmpty()) {
                    return dp[i][j];
                }
            }
        }
        return "invalid";
    }

    private static String max(String a, String b) {
        int aLen = a.length();
        int bLen = b.length();
        if (aLen != bLen)
            return aLen > bLen ? a : b;
        for (int i = 0; i < aLen; i++) {
            char ac = a.charAt(i);
            char bc = b.charAt(i);
            if (ac == bc) continue;
            return ac > bc ? a : b;
        }
        return a.charAt(0) > b.charAt(0) ? a : b;
    }

    private static void dp() {
        Arrays.fill(dp[0], "");
        int J = dp[0].length - 1;
        for (int i = 1; i <= M; i++) {
            Arrays.fill(dp[i], "");
            for (int j = 1; j <= J; j++) {
                String max = dp[i-1][j];
                for (int cur = N-1; cur >= 0; cur--) {
                    if (cost[cur] <= i) {
                        String b = dp[i-cost[cur]][j-1] + cur;
                        if (b.length() > 1 && b.charAt(0) == '0') continue;
                        max = max(max, b);
                    }
                }
                dp[i][j] = max;
            }
        }
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        cost = new int[N];
        int minP = 51;
        for (int i = 0; i < N; i++) {
            cost[i] = Integer.parseInt(st.nextToken());
            minP = Math.min(minP, cost[i]);
        }
        M = Integer.parseInt(br.readLine());
        dp = new String[M + 1][(M / minP) + 1];
    }
}