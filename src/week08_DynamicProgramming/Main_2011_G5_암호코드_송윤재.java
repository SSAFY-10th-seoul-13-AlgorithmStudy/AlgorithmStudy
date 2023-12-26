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

public class Main_2011_G5_암호코드_송윤재 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int arr[], dp[], size;
	static String pw;
	static final int MOD = 1_000_000, MAX = 5001;
	
	static void input() throws IOException{
		pw = br.readLine();
		size = pw.length();
		arr = new int[MAX];
		dp = new int[MAX];
		for(int i = 1, m_size = size + 1; i < m_size; i++) {
			arr[i] = pw.charAt(i - 1) - '0';
		}
	}
	
	static void solve() {
		dp[0] = 1;
		for(int i = 1, m_size = size + 1; i < m_size; i++) {
			if(arr[i] >= 1 && arr[i] <= 9) // 한 자릿수에서 알파벳이 만들어지는 경우
				dp[i] = (dp[i - 1] + dp[i]) % MOD;
			
			if(i == 1 || arr[i - 1] == 0) continue; // 앞자리수를 볼 필요 없는 경우
			
			int temp = arr[i] + arr[i - 1] * 10;
			if(temp >= 10 && temp <= 26) // 두 자릿수에서 알파벳이 만들어지는 경우
				dp[i] = (dp[i - 2] + dp[i]) % MOD;
		}
		sb.append(dp[size]);
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
