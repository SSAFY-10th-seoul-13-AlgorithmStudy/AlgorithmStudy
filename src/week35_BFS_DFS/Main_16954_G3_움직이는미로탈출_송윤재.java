package week35_BFS_DFS;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_16954_G3_움직이는미로탈출_송윤재 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static boolean map[][];
	static int dr[] = {-1, -1, -1, 0, 0, 1, 1, 1, 0};
	static int dc[] = {-1, 0, 1, -1, 1, -1, 0, 1, 0}; // 9방향 벡터
	
	static void init() {
		map = new boolean[16][8];
	}
		
	static void input() throws IOException {		
		init();
		for(int i = 8; i < 16; i++) {
			String str = br.readLine();
			for(int j = 0; j < 8; j++) {
				if(str.charAt(j) == '#') map[i][j] = true;
			}
		}
	}

	static void solve() {
		sb.append(bfs());
	}
	
	static int bfs() {
		int time = 0;
		Queue<int[]> que = new ArrayDeque<int[]>();
		que.offer(new int[] {15, 0});
		
		while(!que.isEmpty()) {
			int size = que.size();
			while(size-- > 0) {
				int[] cur = que.poll();
				int cr = cur[0];
				int cc = cur[1];
				if(cr == 8 && cc == 7) return 1;		
				if(map[cr - time][cc]) continue; // 현재 벽에 있다?
				
				for(int i = 0; i < 9; i++) {
					int nr = cr + dr[i];
					int nc = cc + dc[i];
					
					if(nr < 8 || nr >= 16 || nc < 0 || nc >= 8) continue;
					if(map[nr - time][nc]) continue; // 이동할 곳이 벽이다?
					que.offer(new int[] {nr, nc});
				}
			}
			if(time < 8) time++;
			else return 1;
		}
		
		return 0;
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
