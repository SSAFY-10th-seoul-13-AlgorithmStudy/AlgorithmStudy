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

public class Main_16565_G2_N포커_송윤재 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int N, combi[][];
	static final int MOD = 10_007;
	
	static void init() {
		combi = new int[53][53]; // combi[n][r] : nCr
	}
	
	static void input() throws IOException{
		N = Integer.parseInt(br.readLine());
		init();
	}
	
	static void solve() {
		int result = 0;
		for(int i = 0; i < 53; i++) combi[i][0] = 1; // nC0은 항상 1
		
		for(int i = 1; i < 53; i++)
			for(int j = 1; j < 53; j++)
				combi[i][j] = (combi[i - 1][j] + combi[i - 1][j - 1]) % MOD;
		// 파스칼의 삼각형. i번째 숫자를    안 뽑는 경우 + 뽑는 경우
		
		for(int i = 1; i < 14; i++) { // 포커의 쌍의 개수들 홀수 일 땐 포함, 짝수 일 땐 배제
			if(i * 4 > N) break;
			int temp = (combi[13][i] * combi[52 - 4 * i][N - 4 * i]) % MOD;
			// 만들어 질 수 있는 13개의 가짓수 중 i개를 고르고 나머지를 배치하는 경우의 수
			
			if(i % 2 == 1) result = (result + temp) % MOD;
			else result = (result - temp + MOD) % MOD;
		}
		sb.append(result);
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
