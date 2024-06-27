package week28_Math;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_1222_G2_홍준프로그래밍대회_송윤재 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int N, count[], max;
	
	static void init() {
		count = new int[2_000_001]; // 학생 수 별 학교의 개수
	}
	
	static void input() throws IOException {
		N = Integer.parseInt(br.readLine());
		
		init();

		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			int num = Integer.parseInt(st.nextToken());
			count[num]++;
		}
	}

	static void solve() {		
		long max = 0;
		
		for(int i = 1; i < 2_000_001; i++) {
			long temp = 0;
			for(int j = i; j < 2_000_001; j += i) {
				temp += count[j]; // j를 약수로 갖는 학교의 개수들
			}
			if(temp > 1) {
				max = Math.max(max, temp * i);
			}
		}
		sb.append(max);
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
