package week47_BFS_DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_16197_G4_두동전_신문영 {
	static final char COIN = 'o';
	static final char EMPTY = '.';
	static final char WALL = '#';
	static int[][] direction = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		char[][] map = new char[N][M];
		boolean[][][][] visited = new boolean[N][M][N][M];	
		Queue<CoinSet> queue = new ArrayDeque<>();
		int r = -1;
		int c = -1;
		for (int i = 0; i < N; i++) {
			String input = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = input.charAt(j);
				
				if (map[i][j] == COIN) {
					if (r == -1) r = i;
					if (c == -1) c = j;
					
					if (r != i || c != j) {
						visited[r][c][i][j] = true;
						queue.add(new CoinSet(r, c, i, j));
					}
				}
			}
		}
		
		boolean isFindAnswer = false;
		Queue<CoinSet> tracker = new ArrayDeque<>();
		int time = 1;
		loop:
		while (!queue.isEmpty()) {
			CoinSet coinSet = queue.poll();
			for (int i = 0; i < 4; i++) {
				int nextR1 = coinSet.r1 + direction[i][0];
				int nextC1 = coinSet.c1 + direction[i][1];
				int nextR2 = coinSet.r2 + direction[i][0];
				int nextC2 = coinSet.c2 + direction[i][1];
				
				int out = 0;
				
				if (nextR1 < 0 || nextC1 < 0 || nextR1 >= N || nextC1 >= M) out++;
				if (nextR2 < 0 || nextC2 < 0 || nextR2 >= N || nextC2 >= M) out++;
				
				if (out == 2) continue;
				if (out == 1) {
					isFindAnswer = true;
					break loop;
				}
				
				if (map[nextR1][nextC1] == WALL) {
					nextR1 = coinSet.r1;
					nextC1 = coinSet.c1;
				}
				
				if (map[nextR2][nextC2] == WALL) {
					nextR2 = coinSet.r2;
					nextC2 = coinSet.c2;
				}
				
				if (visited[nextR1][nextC1][nextR2][nextC2]) continue;
				
				visited[nextR1][nextC1][nextR2][nextC2] = true;
				
				tracker.add(new CoinSet(nextR1, nextC1, nextR2, nextC2));
			}
			
			if (queue.isEmpty()) {
				queue.addAll(tracker);
				tracker.clear();
				time++;
				if (time > 10) break;
			}
		}
		
		System.out.println(!isFindAnswer || time > 10 ? -1 : time);
	}
	
	static class CoinSet {
		int r1;
		int c1;
		int r2;
		int c2;
		
		CoinSet (int r1, int c1, int r2, int c2) {
			this.r1 = r1;
			this.c1 = c1;
			this.r2 = r2;
			this.c2 = c2;
		}
	}
}