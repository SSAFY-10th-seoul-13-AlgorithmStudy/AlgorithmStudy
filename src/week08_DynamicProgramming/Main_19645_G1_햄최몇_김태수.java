package week08_DynamicProgramming;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class Main_19645_G1_햄최몇_김태수 {
	static final int N_MAX = 50;
    static final int T_MAX = 2500;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[] a = new int[N_MAX + 1];
        int sum = 0;

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
            sum += a[i];
        }

        boolean[][] dp = new boolean[T_MAX + 1][T_MAX + 1];
        dp[0][0] = true;

        for (int i = 1; i <= n; i++) {
            for (int j = sum; j >= 0; j--) {
                for (int k = sum; k >= 0; k--) {
                    if (j - a[i] >= 0) {
                        dp[j][k] |= dp[j - a[i]][k];
                    }
                    if (k - a[i] >= 0) {
                        dp[j][k] |= dp[j][k - a[i]];
                    }
                }
            }
        }

        int ans = 0;
        for (int i = 0; i <= sum; i++) {
            for (int j = 0; j <= i; j++) {
                if (dp[i][j] && j >= (sum - i - j)) {
                    ans = Math.max(ans, sum - i - j);
                }
            }
        }

        System.out.println(ans);
    }
}
