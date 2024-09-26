package week38_BruteForce;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main_17136_G2_색종이붙이기_송윤재 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int map[][], paper[], result;
	
	static void init() {
		map = new int[10][10];
		paper = new int[] {0, 5, 5, 5, 5, 5};
		result = Integer.MAX_VALUE;
	}
		
	static void input() throws IOException {
		init();
		for(int i = 0; i < 10; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 10; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}		
	}

	static void solve() {
		dfs(0, 0, 0);
		sb.append(result != Integer.MAX_VALUE ? result : -1);
	}
	
	static void dfs(int x, int y, int cnt) {
		if(x >= 9 && y > 9) {
			result = Math.min(result, cnt);
			return;
		}
		
		if(result <= cnt) 
			return;
		
		if(y > 9) {
			dfs(x + 1, 0, cnt);
			return;
		}
		
		if(map[x][y] == 1) {
			for(int i = 5; i > 0; i--) {
				if(paper[i] > 0 && isValid(x, y, i)) {
					reflect(x, y, i, 0);
					paper[i]--;
					dfs(x, y + 1, cnt + 1);
					reflect(x, y, i, 1);
					paper[i]++;
				}
			}
		} else {
			dfs(x, y + 1, cnt);
		}
	}
	
	static void reflect(int x, int y, int size, int state) {
		for(int i = x; i < x + size; i++) {
			for(int j = y; j < y + size; j++) {
				map[i][j] = state;
			}
		}
	}
	
	static boolean isValid(int x, int y, int size) {
		for(int i = x; i < x + size; i++) {
			for(int j = y; j < y + size; j++) {
				if(i < 0 || i >= 10 || j < 0 || j >= 10)
					return false;
				if(map[i][j] != 1)
					return false;
			}			
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
