package week42_DynamicProgramming;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main_1398_G2_동전문제_송윤재 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int[] dp;
	static List<Integer> choco;
	
	static void init() {
		choco = new ArrayList<>();
		dp = new int[100];
		init_dp();
	}
	
	static void init_dp() {
		for(int i = 1; i < 100; i++) {
			dp[i] = i;
		}
		
		for(int i = 1; i < 100; i++) {			
			if(i < 10) continue;
			dp[i] = Math.min(dp[i], dp[i - 10] + 1);
			if(i < 25) continue;
			dp[i] = Math.min(dp[i], dp[i - 25] + 1);
		}
	}
		
	static void input() throws IOException {
		init();
		String str = br.readLine();
		for(int i = str.length() - 1; i > 0; i--) {
			int temp = str.charAt(i--) - '0' +  10 * (str.charAt(i) - '0');
			choco.add(temp);
		}
		if(str.length() % 2 == 1) {
			choco.add(str.charAt(0) - '0');
		}
	}

	static void solve() {
		int count = 0;
		for(int c : choco) {
			count += dp[c];
		}
		sb.append(count).append("\n");
	}
	
	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());
		for(int tc = 0; tc < T; tc++) {
			input();
			solve();
		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}
