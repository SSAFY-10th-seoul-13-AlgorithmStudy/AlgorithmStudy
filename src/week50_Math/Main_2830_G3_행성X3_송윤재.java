package week50_Math;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main_2830_G3_행성X3_송윤재 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int X3[], N, binary[];
	
	static void init() {
		X3 = new int[N];
		binary = new int[20]; // 2^20이 1,000,000 보다 크므로
	}
	
	static void input() throws IOException {
		N = Integer.parseInt(br.readLine());
		init();
		for(int i = 0; i < N; i++) {
			X3[i] = Integer.parseInt(br.readLine());
		}
	}
	
	static void solve() {
		long result = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 19; j >= 0; j--) {
				int temp = 1 << j;
				if(X3[i] >= temp) {
					X3[i] -= temp;
					binary[j]++;
				}
			}
		}
		for(int j = 19; j >= 0; j--) {
			int temp = 1 << j;
			result += temp * (long)(N - binary[j]) * binary[j];
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
