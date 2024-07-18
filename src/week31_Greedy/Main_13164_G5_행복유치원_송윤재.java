package week31_Greedy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_13164_G5_행복유치원_송윤재 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int N, K, student[], diff[];
	
	static void init() {
		student = new int[N];
		diff = new int[N - 1];
	}
		
	static void input() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		init();
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			student[i] = Integer.parseInt(st.nextToken());
		}
	}

	static void solve() {
		for(int i = 1; i < N; i++) {
			diff[i - 1] = student[i] - student[i - 1];
		}
		Arrays.sort(diff);
		int result = 0;
		for(int i = 0; i < N -K; i++) {
			result += diff[i];
		}
		sb.append(result);
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
