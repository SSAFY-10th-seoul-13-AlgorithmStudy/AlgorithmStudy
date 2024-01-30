package week12_BinarySearch;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main_2473_G3_세용액_송윤재 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int N, idx1, idx2, idx3;
	static long liquid[], min;
	
	static void init() {
		liquid = new long[N];
		min = Long.MAX_VALUE;
	}
	
	static void input() throws IOException {
		N = Integer.parseInt(br.readLine());
		init();
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++)
			liquid[i] = Long.parseLong(st.nextToken());
	}

	static void solve() {
		Arrays.sort(liquid);
		for(int i = 0; i < N - 2; i++) {
			int start = i + 1;
			int end = N - 1;
			while(start < end) { // 투 포인터
				long temp = liquid[i] + liquid[start] + liquid[end];
				if(Math.abs(temp) < min) {
					min = Math.abs(temp);
					idx1 = i; idx2 = start; idx3 = end; // 값 갱신
				}
				if(temp < 0) start++;
				else end--;
			}
		}
		sb.append(liquid[idx1]).append(" ").append(liquid[idx2]).append(" ").append(liquid[idx3]);
	}	
	
	public static void main(String[] args) throws IOException {
		input();
		solve();

		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}
