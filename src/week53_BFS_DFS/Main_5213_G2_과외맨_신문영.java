package week53_BFS_DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_5213_G2_과외맨_신문영 {
	static int[][] direction = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		
		int number = 1;
		int[][][] map = new int[N][N * 2][2];
		for (int r = 0; r < N; r++) {
			for (int c = r % 2; c < N * 2 - r % 2; c += 2) {
				st = new StringTokenizer(br.readLine());
				int left = Integer.parseInt(st.nextToken());
				int right = Integer.parseInt(st.nextToken());
				map[r][c][0]= left;
				map[r][c + 1][0] = right;
				map[r][c][1] = map[r][c + 1][1] = number;
				number++;
			}
		}
		
		int[] before = new int[N * N - N / 2 + 1];
		
		boolean[][] visited = new boolean[N][N * 2];
		visited[0][0] = true;
		
		Queue<Pair> queue = new ArrayDeque<>();
		queue.add(new Pair(0, 0));
		
		int maxTile = 0;
		while (!queue.isEmpty()) {
			Pair current = queue.poll();
			for (int i = 0; i < direction.length; i++) {
				int nextR = current.r + direction[i][0];
				int nextC = current.c + direction[i][1];
				
				if (nextR < 0 || nextC < 0 || nextR >= N || nextC >= N * 2) continue;
				
				if (map[nextR][nextC][0] == 0) continue;

				if (visited[nextR][nextC]) continue;
				
				if (map[current.r][current.c][0] != map[nextR][nextC][0] && 
						map[current.r][current.c][1] != map[nextR][nextC][1]) continue;
				
				visited[nextR][nextC] = true;
				queue.add(new Pair(nextR, nextC));
				
				if (nextC >= 1 && map[nextR][nextC][1] == map[nextR][nextC - 1][1]) {
					visited[nextR][nextC - 1] = true;
					queue.add(new Pair(nextR, nextC - 1));
				}
				
				if (nextC < N * 2 - 1 && map[nextR][nextC][1] == map[nextR][nextC + 1][1]) {
					visited[nextR][nextC + 1] = true;
					queue.add(new Pair(nextR, nextC + 1));
				}
				
				if (map[nextR][nextC][1] != map[current.r][current.c][1]) {
					before[map[nextR][nextC][1]] = map[current.r][current.c][1];
				}

				maxTile = Math.max(maxTile, map[nextR][nextC][1]);
			}
		}
		
		List<Integer> answer = new ArrayList<>();
		answer.add(maxTile);
		while (before[maxTile] != 0) {
			maxTile = before[maxTile];
			answer.add(maxTile);
		}
		
		Collections.reverse(answer);
		
		System.out.println(answer.size());
		for (int i : answer) {
			System.out.print(i + " ");
		}
	}
	
	static class Pair {
		int r;
		int c;
		
		Pair(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}
