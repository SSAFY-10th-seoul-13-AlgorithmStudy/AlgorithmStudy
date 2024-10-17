package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_1398_G2_동전문제_강이규 {
    /*
    input을 2자리씩 끊으면, 1, 10, 25로 0~99를 만드는 상황들의 합이 된다.
     */
    static int[] dp = new int[100]; // 1, 10, 25 를 조합해 0 ~ 99 를 만드는 최소 동전 수

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        initDp();

        StringBuilder output = new StringBuilder();
        while (T-- > 0) {
            long result = 0;

            long price = Long.parseLong(br.readLine());
            while (price >= 10) {
                long curPart = price % 100;
                price /= 100;
                long ten = curPart / 10;
                long one = curPart % 10;
                int idx = (int) (ten * 10 + one);

                result += dp[idx];
            }
            // 1의 자리가 남았다면
            int idx = (int) (price % 10);
            result += dp[idx];

            output.append(result).append("\n");
        }
        System.out.print(output);
    }

    private static void initDp() {
        for (int i = 1; i < 100; i++) {
            dp[i] = dp[i-1] + 1;
            if (i >= 10) dp[i] = Math.min(dp[i], dp[i - 10] + 1);
            if (i >= 25) dp[i] = Math.min(dp[i], dp[i - 25] + 1);
        }
    }
}
