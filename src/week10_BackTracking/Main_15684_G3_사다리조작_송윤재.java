package week10_BackTracking;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main_15684_G3_사다리조작_송윤재 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int N, M, H;
	static boolean ladders[][], find;
	
	static void init() {
		ladders = new boolean[H + 1][N + 1];
	}

	static void input() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		init();
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			ladders[a][b] = true; // a 높이에서 b -> b + 1로 이동할 수 있는지
		}
	}
	
	static void solve() {
		int result = -1;
		for(int i = 0; i < 4; i++) {
			dfs(0, i, 1);
			if(find) {
				result = i;
				break;
			}
		}
		sb.append(result);
	}
	
	static void dfs(int depth, int target, int nr) {
		if(depth == target) {
			if(check()) 
				find = true;
			return;
		}
		
		for(int r = nr; r <= H; r++) {
			for(int c = 1; c < N; c++) {
				if(!ladders[r][c - 1] && !ladders[r][c] && !ladders[r][c + 1]) {
					// 놓으려는 근처에 사다리가 없는 경우
					ladders[r][c] = true;
					dfs(depth + 1, target, r);
					ladders[r][c] = false;
				}
			}
		}
	}
	
	static boolean check() {
		for(int i = 1; i <= N; i++) {
			int cur = i;
			for(int j = 1; j <= H; j++) {
				if(ladders[j][cur]) cur++; // 오른쪽으로 이동 가능 할 때
				else if(cur == 1); // cur == 1 일 때 왼쪽 이동 불가
				else if(ladders[j][cur - 1]) cur--; // 왼쪽으로 이동 가능 할 때
			}
			if(cur != i) return false;
		}
		return true;
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
