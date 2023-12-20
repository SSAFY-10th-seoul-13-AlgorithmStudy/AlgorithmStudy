package week07_DynamicProgramming;

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

public class Main_12015_G2_가장긴증가하는부분수열2_송윤재 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int N, A[], dp[], length;
	
	static void init() {
		A = new int[N];
		dp = new int[N + 1];
		// dp[i]는 i의 길이를 갖는 최장 증가 수열의 맨 뒤의 값(가장 큰 값)을 값으로 가진다
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
		
		if(left > right) { // 중간에 들어갈 곳을 못 찾은 경우
			if(dp[mid] >= target) { // 맨 앞에 들어가야 할 때
				dp[mid] = target;
				return;
			}
			else { // 맨 뒤에 들어가야 할 때
				dp[length++] = target;
				return;
			}
		}
		
		if(dp[mid] < target && target <= dp[mid + 1]) { // 중간에 들어갈 곳
			dp[mid + 1] = target;
			return;
		}
		
		if(dp[mid] < target) binarySearch(mid + 1, right, target);
		else binarySearch(left, mid - 1, target);
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
