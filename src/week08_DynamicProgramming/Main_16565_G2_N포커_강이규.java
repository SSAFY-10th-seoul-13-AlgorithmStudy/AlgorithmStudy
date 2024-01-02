package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_16565_G2_N포커_강이규 {

    static int N;
    static int[][] combi;
    static final int MOD = 10007;


    public static void main(String[] args) throws IOException {
        init();
        System.out.println(solve());
    }

    private static int solve() {
        int res = 0;
        for (int i = 1; i <= 13; i++) {
            if (i * 4 > N) break;

            int cnt = (combi[13][i] * combi[52 - 4*i][N - 4*i]) % MOD;

            if (i % 2 == 1) res = (res + cnt) % MOD;
            else res = (res - cnt + MOD) % MOD;
        }
        return res;
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        combi = new int[53][53];

        for (int i = 0; i < 53; i++)
            combi[i][0] = 1;

        for (int i = 1; i < 53; i++) {
            for (int j = 1; j < 53; j++) {
                combi[i][j] = (combi[i-1][j-1] + combi[i-1][j]) % MOD;
            }
        }
    }
}
