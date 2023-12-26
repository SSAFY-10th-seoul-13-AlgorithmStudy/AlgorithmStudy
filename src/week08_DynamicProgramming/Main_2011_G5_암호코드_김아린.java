package week08_DynamicProgramming;

import java.io.*;
import java.util.*;

public class Main_2011_G5_암호코드_김아린 {
	private static final int MOD = 1000000;

	public static void main(String[] args) throws IOException {
		
		//예외처리가 빡셌던 문제
		//https://leeeehhjj.tistory.com/41
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        String str = st.nextToken();
        long[] dp = new long[str.length()+1];
        if (str.charAt(0) == '0') { //0입력하면
            System.out.println(0);
            return;
        }
        else {
            dp[0] = 1;
            dp[1] = 1;
            for (int i = 2; i <= str.length(); i++) {
                if (str.charAt(i-1) == '0') {
                    if (str.charAt(i-2) == '1' || str.charAt(i-2) == '2')
                        dp[i] = dp[i-2] % MOD;
                    else
                        break;
                }
                else {
                    int result = Integer.parseInt(str.substring(i-2,i));
                    if (result < 27 && result > 9)
                        dp[i] = (dp[i-1] + dp[i-2]) % MOD;
                    else {
                        dp[i] = dp[i-1] % MOD;
                    }
                }
            }
        }
        System.out.println(dp[str.length()] % MOD);
 	}
}
