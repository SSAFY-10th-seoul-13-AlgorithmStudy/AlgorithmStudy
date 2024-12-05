package week48_DynamicProgramming;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main_1256_G2_사전_송윤재 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int N, M, K;
	static long combi[][];
	
	static void init() {
		combi = new long[201][201];
		combi[1][0] = 1;
		combi[1][1] = 1;
		
		for(int i = 2; i < 201; i++) {
			combi[i][0] = 1;
			for(int j = 1; j < 201; j++) {
				combi[i][j] = combi[i - 1][j - 1] + combi[i -1][j];
				if(combi[i][j] > 1_000_000_000)
					combi[i][j] = 1_000_000_001;
			}
		}
	}
	
	static void input() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		init();
	}

	static void solve() {
		if(K-- > combi[N + M][M])
			sb.append(-1);
		else {
			for(int i = N + M - 1; i >= 0; i--) {
				if(K >= combi[i][M] && M > 0) {
					K -= combi[i][M--];
					sb.append('z');
				} else
					sb.append('a');
			}
		}
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
