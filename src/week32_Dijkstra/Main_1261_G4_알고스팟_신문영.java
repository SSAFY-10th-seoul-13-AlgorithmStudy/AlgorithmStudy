package week32_Dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1261_G4_알고스팟_신문영 {
	static int N;
	static int M;
	static int[][] map;
	static int[][] direction = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
	static boolean[][] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());
		M = Integer.parseInt(stringTokenizer.nextToken());
		N = Integer.parseInt(stringTokenizer.nextToken());
		
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = line.charAt(j) - '0';
			}
		}
		
		Pair starting = Pair.of(0, 0);
		Pair destination = Pair.of(N - 1, M - 1);
		
		int answer = 0;
		visited = new boolean[N][M]; 
		Queue<Pair> queue = new ArrayDeque<>();
		Queue<Pair> wallQueue = new ArrayDeque<>();
		queue.add(starting);
		visited[starting.r][starting.c] = true;
		while (!queue.isEmpty()) {
			Pair current = queue.poll();
			
			if (destination.equals(current)) break;
			
			for (int i = 0; i < 4; i++) {
				int nextR = current.r + direction[i][0];
				int nextC = current.c + direction[i][1];
				
				if (!isValid(nextR, nextC)) continue;
				
				visited[nextR][nextC] = true;
				
				if (isWall(nextR, nextC)) {
					wallQueue.add(Pair.of(nextR, nextC));
				} else {
					queue.add(Pair.of(nextR, nextC));
				}
			}
			
			if (queue.isEmpty()) {
				if (wallQueue.isEmpty()) break;
				
				for (Pair wall : wallQueue) {
					visited[wall.r][wall.c] = true; 
					map[wall.r][wall.c] = 0; 
				}
				
				queue.addAll(wallQueue);
				wallQueue.clear();
				answer++;
			}
		}
		
		System.out.println(answer);
	}
	
	static class Pair {	
		int r;
		int c;
		
		Pair (int r, int c) {
			this.r = r;
			this.c = c;
		}
		
		static Pair of(int r, int c) {
			return new Pair(r, c);
		}
		
		boolean equals(Pair pair) {
			return this.r == pair.r && this.c == pair.c;
		}
	}
	
	static boolean isValid(int r, int c) {
		return (r >= 0 && c >= 0 && r < N && c < M) && !visited[r][c];
	}
	
	static boolean isWall(int r, int c) {
		return map[r][c] == 1;
	}
}