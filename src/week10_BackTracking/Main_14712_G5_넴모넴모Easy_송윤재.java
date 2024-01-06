package week10_BackTracking;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main_14712_G5_넴모넴모Easy_송윤재 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int N, M, cnt;
	static boolean map[][];
	
	static void init() {
		map = new boolean[N + 1][M + 1];
	}

	static void input() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		init();
	}

	static void solve() {
		dfs(1, 1);
		sb.append(cnt);
	}
	
	static void dfs(int r, int c) {
		if(c > M) { r++; c = 1;}
		
		if(r > N) {
			cnt++;
			return;
		}
		
		if(map[r - 1][c] && map[r][c - 1] && map[r - 1][c - 1]) { // 네모가 못 들어가는 경우. BackTracking
			dfs(r, c + 1);
		}
		else {
			dfs(r, c + 1); // 네모를 안 넣는 경우
			
			map[r][c] = true;
			dfs(r, c + 1); // 네모를 넣는 경우
			map[r][c] = false;
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
