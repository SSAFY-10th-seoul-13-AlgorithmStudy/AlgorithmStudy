package week57_DynamicProgramming;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main_2216_G3_문자열과점수_송윤재 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int A, B, C;
	static char[] X, Y;
	
	static void input() throws IOException{
		st = new StringTokenizer(br.readLine());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		X = br.readLine().toCharArray();
		Y = br.readLine().toCharArray();
	}
	
	static void solve() {
		int xlen = X.length;
		int ylen = Y.length;
		
		int dp[][] = new int[xlen + 1][ylen + 1];
		
		for(int i = 1; i <= xlen; i++) {
			dp[i][0] = B * i;
		}
		
		for(int i = 1; i <= ylen; i++) {
			dp[0][i] = B * i;
		}
		
		dp[0][0] = 0;
		
		for(int i = 1; i <= xlen; i++) {
			for(int j = 1; j <= ylen; j++) {
				dp[i][j] = Math.max(dp[i - 1][j] + B, dp[i][j - 1] + B);
				dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + (X[i - 1] == Y[j - 1] ? A  : C));
			}
		}
		
		sb.append(dp[xlen][ylen]);
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
