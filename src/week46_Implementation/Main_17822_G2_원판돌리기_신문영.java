package week46_Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_17822_G2_원판돌리기_신문영 {
	static final int EMPTY = Integer.MIN_VALUE;
	static final int CLOCK = 0;
	static final int COUNTERCLOCK = 1;
	static int[][] direction = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 0; i < T; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			
			int initX = x;
			while (x <= N) {
				int idx = x - 1;
				int[] rotated = new int[M];
				if (d == CLOCK) {
					for (int m = 0; m < M; m++) {
						int target = m - k;
						if (target < 0) target += M;
						rotated[m] = map[idx][target];
					}
				} else {
					for (int m = 0; m < M; m++) {
						int target = m + k;
						if (target >= M) target -= M;
						rotated[m] = map[idx][target];
					}
				}
				map[idx] = rotated;
				
				x += initX;
			}
			
			boolean isAdjFound = false;
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < M; c++) {
					if (map[r][c] != EMPTY) {
						int number = map[r][c];
						
						Queue<Pair> queue = new ArrayDeque<>();
						queue.add(new Pair(r, c));
						
						Queue<Pair> tracker = new ArrayDeque<>();
						tracker.add(new Pair(r, c));
						
						boolean[][] visited = new boolean[N][M];
						visited[r][c] = true;
						
						while (!queue.isEmpty()) {
							Pair current = queue.poll();
							for (int j = 0; j < 4; j++) {
								int nextR = current.r + direction[j][0];
								int nextC = current.c + direction[j][1];
								
								if (nextR < 0 || nextR >= N) continue;
								
								if (nextC < 0) nextC += M;
								if (nextC >= M) nextC -= M;
								
								if (visited[nextR][nextC]) continue;
								
								if (map[nextR][nextC] != number) continue;
								
								visited[nextR][nextC] = true;
								queue.add(new Pair(nextR, nextC));
								tracker.add(new Pair(nextR, nextC));
							}
						}
						
						if (tracker.size() >= 2) {
							isAdjFound = true;
							while (!tracker.isEmpty()) {
								Pair pair = tracker.poll();
								map[pair.r][pair.c] = EMPTY;
							}
						}
					}
				}
			}
			
			if (!isAdjFound) {
				int sum = 0;
				int cnt = 0;
				for (int row = 0; row < N; row++) {
					for (int col = 0; col < M; col++) {
						if (map[row][col] == EMPTY) continue;
						sum += map[row][col];
						cnt++;
					}
				}
				
				double avg = (double) sum / (double) cnt;
				for (int row = 0; row < N; row++) {
					for (int col = 0; col < M; col++) {
						if (map[row][col] == EMPTY) continue;
						if (map[row][col] > avg) map[row][col]--;
						else if (map[row][col] < avg) map[row][col]++;
					}
				}
			}
		}
		
		int sum = 0;
		for (int row = 0; row < N; row++) {
			for (int col = 0; col < M; col++) {
				sum += map[row][col] != EMPTY ? map[row][col] : 0;
			}
		}
		System.out.println(sum);
	}
	
	static class Pair {
		int r;
		int c;
		Pair (int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}
