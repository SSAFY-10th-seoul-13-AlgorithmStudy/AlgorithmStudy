package week08_DynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_2011_G5_¾ÏÈ£ÄÚµå_±èÈñ¿¬ {

	private static final int MOD = 1000000;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();

		int[] dp = new int[str.length()+1];

		if(str.charAt(0) == '0'){
			System.out.println(0);
			return;
		}

		dp[0] = 1;
		dp[1] = 1;

		for(int i=2; i<=str.length(); i++){
			if(str.charAt(i-1) != '0'){
				dp[i] += dp[i-1];
				dp[i] %= MOD;
			}

			int num = Integer.parseInt(str.substring(i-2, i));
			if(num >= 10 && num <= 26){
				dp[i] += dp[i-2];
				dp[i] %= MOD;
			}
		}

		System.out.println(dp[str.length()]);
	}
}
