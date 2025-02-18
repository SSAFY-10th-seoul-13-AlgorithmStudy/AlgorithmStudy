package week58_DynamicProgramming;

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

public class Main_2352_G2_반도체설계_송윤재 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int N, A[], dp[], length;
	
	static void init() {
		A = new int[N];
		dp = new int[N];
	}
	
	static void input() throws IOException{
		N = Integer.parseInt(br.readLine());
		init();
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
	}
	
	static void solve() {
		for(int i = 0; i < N; i++) {
			binarySearch(0, length, A[i]);
		}
		sb.append(length);
	}
	
	static void binarySearch(int left, int right, int target) {
		int mid = (left + right) / 2;
		
		if(left == right) {
			if(dp[mid] > target)
				dp[mid] = target;
			else
				dp[length++] = target;
			return;
		}
		
		if(dp[mid] < target) binarySearch(mid + 1, length, target);
		else binarySearch(left, mid, target);
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
