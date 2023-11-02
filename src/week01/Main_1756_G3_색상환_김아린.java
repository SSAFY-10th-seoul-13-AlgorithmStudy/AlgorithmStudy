package week01;

import java.io.*;

public class Main_1756_G3_색상환_김아린 {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final int MOD = 1_000_000_003;

    static int n, k;
    // 행은 가지고 있는 색의 개수를 의미한다.
    // 열은 선택한 색의 개수를 의미한다.
    static int dp[][];

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        k = Integer.parseInt(br.readLine());

        dp = new int[n + 1][n + 1];

        for(int i = 1; i <= n; i++){
            // i개 중에서 1개를 선택하는 방법은 i개이다.
            dp[i][1] = i;
            // 0개를 선택한 경우는 1로 초기화한다.
            // 점화식을 위해서는 1로 초기화해야한다.
            dp[i][0] = 1;
        }

        // i가 3미만인 경우는 계산할 필요가 없다.
        for(int i = 3; i <= n; i++){
            // n개의 수 중 n/2개 보다 더 많이 고르는 경우는 0가지이다.
            // 그렇기 때문에 j의 범위를 다음과 같이 설정한다.
            for(int j = 2; j <= (i + 1) / 2; j++){
                // i번째 색을 선택하지 않은 경우 + i번째 색을 선택한 경우
                dp[i][j] = (dp[i - 1][j] + dp[i - 2][j - 1]) % MOD;
            }
        }

        // n번째 색을 선택한 경우 + n번째 색을 선택하지 않은 경우
        bw.write((dp[n - 3][k - 1] + dp[n - 1][k]) % MOD + "\n");
        bw.close();
        br.close();
    }

}