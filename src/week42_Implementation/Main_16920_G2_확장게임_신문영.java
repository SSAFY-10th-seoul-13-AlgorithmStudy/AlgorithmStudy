package week42_Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_16920_G2_확장게임_신문영 {
	static final char EMPTY = '.';
	static final char WALL = '#';
	static int[][] direction = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int P = Integer.parseInt(st.nextToken());
		
		int[] expansion = new int[P];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < P; i++) {
			expansion[i] = Integer.parseInt(st.nextToken());
		}
		
		Map<Integer, Queue<Pair>> playerPosition = new HashMap<>();
		for (int i = 1; i <= P; i++) {
			playerPosition.put(i, new ArrayDeque<>());
		}
		
		int[][] map = new int[N][M];
		boolean[][] visited = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			String input = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = input.charAt(j);
				if (playerPosition.containsKey(map[i][j] - '0')) {
					playerPosition.get(map[i][j] - '0').add(new Pair(i, j));
					map[i][j] -= '0';
					visited[i][j] = true;
				}
			}
		}
		
		while (true) {
			boolean isUpdated = false;
			for (int player = 1; player <= P; player++) {
				int expandable = expansion[player - 1];
				while (expandable > 0) {
					Queue<Pair> queue = playerPosition.get(player);
					Queue<Pair> updatedPosition = new ArrayDeque<>();
					boolean isExpandable = false;
					while (!queue.isEmpty()) {
						Pair current = queue.poll();
						for (int i = 0; i < 4; i++) {
							int nextR = current.r + direction[i][0];
							int nextC = current.c + direction[i][1];
							
							if (nextR < 0 || nextC < 0 || nextR >= N || nextC >= M) continue;
							if (visited[nextR][nextC]) continue;
							
							if (map[nextR][nextC] == EMPTY) {
								visited[nextR][nextC] = true;
								map[nextR][nextC] = player;
								updatedPosition.add(new Pair(nextR, nextC));
								isExpandable = true;
							}
						}
					}
					
					playerPosition.put(player, updatedPosition);
					
					if (isExpandable) {
						isUpdated = true;
						expandable--;
					} else {
						expandable = 0;
					}
				}
			}
			
			if (!isUpdated) break;
		}
		
		int[] answer = new int[P];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] != WALL && map[i][j] != EMPTY) answer[map[i][j] - 1]++;
			}
		}
		
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < P; i++) {
			stringBuilder.append(answer[i]).append(" ");
		}
		stringBuilder.delete(stringBuilder.length() - " ".length(), stringBuilder.length());
		
		System.out.println(stringBuilder);
	}
	
	public static class Pair {
		int r;
		int c;
		
		public Pair(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}