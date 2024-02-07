package week13_BruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_1285_G1_동전뒤집기_강이규 {

    static int N;
    static int[] coins; // 비트마스크 / 0 뒷면 1 앞면
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        init();
        subset(0,0);
        System.out.println(min);
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        coins = new int[N];
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            int row = 0;
            int flag = 1;
            for (int j = N-1; j >= 0; j--) {
                coins[i] |= line.charAt(j) == 'H' ? flag : 0;
                flag <<= 1;
            }
        }
    }

    /**
     * 뒤집을 행 지정
     */
    private static void subset(int depth, int start) {
        if (depth != 0) {
            cal();
        }
        for (int i = start; i < N; i++) {
            coins[i]  = ~coins[i]; // visited
            subset(depth+1, i+1);
            coins[i]  = ~coins[i];
        }
    }

    /**
     * 열을 체크하면서 sum(min(T, N-T)) 후
     * min 업데이트
     */
    private static void cal() {
        int sum = 0;
        for (int c = 0; c < N; c++) {
            int t = 0;
            for (int r = 0; r < N; r++) {
                t += (coins[r] & (1 << c)) != 0 ? 1 : 0;
            }
            sum += Math.min(t, N - t);
        }
        min = Math.min(sum, min);
    }
}
