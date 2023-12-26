package week08_DynamicProgramming;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_18427_G4_함께블록쌓기_송윤재 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int N, M, H, dp[][];
	static ArrayList<Integer> block[];
	static final int MOD = 10_007;
	
	static void init() {
		dp = new int[N + 1][1001]; // dp[i][j] : i명의 학생들의 블록으로 j 높이를 채우는 경우의 수
		dp[0][0] = 1;
		block = new ArrayList[N + 1];
	}
	
	static void input() throws IOException{
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		init();
		
		for(int i = 1, length = N + 1; i < length; i++) {
			st = new StringTokenizer(br.readLine());
			dp[i][0] = 1;
			block[i] = new ArrayList<>();
			while(st.hasMoreTokens()) {
				block[i].add(Integer.parseInt(st.nextToken()));
			}
		}
	}
	
	static void solve() {
		for(int i = 1, length = N + 1; i < length; i++) {
			for(int j = 1, height = H + 1; j < height; j++) {
				for(int cur : block[i]) {
					if(j < cur) continue;
					dp[i][j] = (dp[i - 1][j - cur] + dp[i][j]) % MOD; // 블럭을 쌓은 경우의 수
				}
				dp[i][j] = (dp[i - 1][j] + dp[i][j]) % MOD; // 블럭을 쌓지 않은 경우의 수
			}
		}
		sb.append(dp[N][H]);
	}
	
	public static void main(String[] args) throws IOException{
		input();
		solve();
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}
