package week29_BFS_DFS;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_9207_G4_페그솔리테어_송윤재 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static char[][] origin;
	static int dr[] = {-1, 1, 0, 0};
	static int dc[] = {0, 0, -1, 1};
	static int minPin, minDepth, maxPin;
	
	static void init() {
		origin = new char[5][9];
		minPin = Integer.MAX_VALUE;
		minDepth = Integer.MAX_VALUE;
		maxPin = 0;		
	}
	
	static void input() throws IOException {
		init();
		for(int i = 0; i < 5; i++) {
			String str = br.readLine();
			for(int j = 0; j < 9; j++) {
				origin[i][j] = str.charAt(j);
				if(origin[i][j] == 'o') maxPin++;
			}
		}
	}

	static void solve() {		
		dfs(maxPin, 0);
		sb.append(minPin).append(" ").append(minDepth).append("\n");
	}
	
	
	static void dfs(int pins, int depth) {
		if(depth > 7) return;
		if(pins < minPin) {
			minPin = pins;
			minDepth = depth;
		} else if(pins == minPin && depth < minDepth){
			minDepth = depth;
		}
		
		if(depth > minDepth) return;
		
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 9; j++) {			
				if(origin[i][j] == 'o') {
					for(int d = 0; d < 4; d++) {
						int mi = i + dr[d];
						int mj = j + dc[d];
						int ni = i + 2 * dr[d];
						int nj = j + 2 * dc[d];
						
						if(ni < 0 || ni >= 5 || nj < 0 || nj >= 9) continue;
						if(origin[ni][nj] != '.') continue;
						if(origin[mi][mj] != 'o') continue;
						
						origin[i][j] = '.';
						origin[mi][mj] = '.';
						origin[ni][nj] = 'o';
						dfs(pins - 1, depth + 1);
						origin[i][j] = 'o';
						origin[mi][mj] = 'o';
						origin[ni][nj] = '.';
					}
				}
			}
		}
		
	}
	
	public static void main(String[] args) throws IOException {		
		int T = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < T; i++) {
			input();
			solve();
			br.readLine();
		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}
