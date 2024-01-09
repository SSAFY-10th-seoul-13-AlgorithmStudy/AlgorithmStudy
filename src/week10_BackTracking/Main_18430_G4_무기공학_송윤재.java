package week10_BackTracking;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main_18430_G4_무기공학_송윤재 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int N, M, map[][], max;
	static boolean visited[][];
	
	static void init() {
		map = new int[N][M];
		visited = new boolean[N][M];
	}

	static void input() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		init();
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}

	static void solve() {
		if(N < 2 || M < 2) sb.append(0);
		else {
			dfs(0, 0, 0);
			sb.append(max);
		}
	}
	
	static void dfs(int r, int c, int sum) {
		if(c >= M) { r++; c = 0;} // 인덱스 이동
		
		if(r >= N) {
			max = Math.max(max, sum);
			return;
		}
		
		if(!visited[r][c]) { 
			visited[r][c] = true;
			int core = sum + 2 * map[r][c];
			if (isInside(r, c + 1)) { // 우측 포함
				visited[r][c + 1] = true;
				int addSide = core + map[r][c + 1];
				if (isInside(r - 1, c)) { // 위쪽도 포함
					visited[r - 1][c] = true;
					dfs(r, c + 2, addSide + map[r - 1][c]);
					visited[r - 1][c] = false;
				}
				if (isInside(r + 1, c)) { // 아래쪽도 포함
					visited[r + 1][c] = true;
					dfs(r, c + 2, addSide + map[r + 1][c]);
					visited[r + 1][c] = false;
				}
				visited[r][c + 1] = false;
			}

			if (isInside(r, c - 1)) { // 좌측 포함
				visited[r][c - 1] = true;
				int addSide = core + map[r][c - 1];
				if (isInside(r - 1, c)) { // 위쪽도 포함
					visited[r - 1][c] = true;
					dfs(r, c + 1, addSide + map[r - 1][c]);
					visited[r - 1][c] = false;
				}
				if (isInside(r + 1, c)) { // 아래쪽도 포함
					visited[r + 1][c] = true;
					dfs(r, c + 1, addSide + map[r + 1][c]);
					visited[r + 1][c] = false;
				}
				visited[r][c - 1] = false;
			}

			visited[r][c] = false;
		}
		dfs(r, c + 1, sum);
	}
	
	static boolean isInside(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < M && !visited[r][c];
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
