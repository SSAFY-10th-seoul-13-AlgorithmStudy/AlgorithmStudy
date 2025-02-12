package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2216_G3_문자열과점수_강이규 {
    static int A, B, C;
    static int[][] dp;
    static char[] X, Y;
    static int xLen, yLen;

    public static void main(String[] args) throws IOException {
        init();
        dp();
        System.out.println(dp[xLen-1][yLen-1]);
    }

    private static void dp() {
        for (int i = 1; i < xLen; i++) {
            dp[i][0] = dp[i-1][0] + B;
        }
        for (int i = 1; i < yLen; i++) {
            dp[0][i] = dp[0][i-1] + B;
        }

        for (int i = 1; i < xLen; i++) {
            for (int j = 1; j < yLen; j++) {
                int compareCurrent = X[i] != Y[j] ? C : A;
                dp[i][j] = Math.max(Math.max(dp[i-1][j] + B, dp[i][j-1] + B), dp[i-1][j-1] + compareCurrent);
            }
        }
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        X = (" " + br.readLine()).toCharArray();
        Y = (" " + br.readLine()).toCharArray();
        xLen = X.length;
        yLen = Y.length;

        dp = new int[xLen][yLen];
    }

}
