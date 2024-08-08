package week31_BFS_DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_16973_G4_직사각형탈출_신문영 {
	static int N;
	static int M;
	static int H;
	static int W;
	static int[][] map;
	static int[][] direction = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
	static boolean[][] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());
		N = Integer.parseInt(stringTokenizer.nextToken());
		M = Integer.parseInt(stringTokenizer.nextToken());
		
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			stringTokenizer = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
			}
		}
		
		stringTokenizer = new StringTokenizer(br.readLine());
		int H = Integer.parseInt(stringTokenizer.nextToken());
		int W = Integer.parseInt(stringTokenizer.nextToken());
		Pair starting = Pair.of(Integer.parseInt(stringTokenizer.nextToken()) - 1, Integer.parseInt(stringTokenizer.nextToken()) - 1);
		Pair destination = Pair.of(Integer.parseInt(stringTokenizer.nextToken()) - 1, Integer.parseInt(stringTokenizer.nextToken()) - 1);
		
		int answer = 0;
		boolean find = false;
		visited = new boolean[N][M]; 
		Queue<Pair> temp = new ArrayDeque<>();
		Queue<Pair> queue = new ArrayDeque<>();
		queue.add(starting);
		visited[starting.r][starting.c] = true;
		while (!queue.isEmpty()) {
			Pair current = queue.poll();
			
			if (destination.equals(current)) {
				find = true;
				break;
			}
			
			for (int i = 0; i < 4; i++) {
				int nextR;
				int nextC;
				boolean isMovable = true;
				// -->
				if (i == 0) {
					for (int j = 0; j < H; j++) {
						nextR = current.r + j;
						nextC = current.c + W;
						if (check(nextR, nextC)) continue;
						else {
							isMovable = false;
							break;
						}
					}
				// <--
				} else if (i == 1) {
					for (int j = 0; j < H; j++) {
						nextR = current.r + j;
						nextC = current.c - 1;
						if (check(nextR, nextC)) continue;
						else {
							isMovable = false;
							break;
						}
					}
				// v
				} else if (i == 2) {
					for (int j = 0; j < W; j++) {
						nextR = current.r + H;
						nextC = current.c + j;
						if (check(nextR, nextC)) continue;
						else {
							isMovable = false;
							break;
						}
					}
				// ^
				} else {
					for (int j = 0; j < W; j++) {
						nextR = current.r - 1;
						nextC = current.c + j;
						if (check(nextR, nextC)) continue;
						else {
							isMovable = false;
							break;
						}
					}
				}
				
				if (isMovable && !visited[current.r + direction[i][0]][current.c + direction[i][1]]) {
					visited[current.r + direction[i][0]][current.c + direction[i][1]] = true;
					temp.add(Pair.of(current.r + direction[i][0], current.c + direction[i][1]));
				}
			}
			
			if (queue.isEmpty()) {
				if (temp.isEmpty()) break;
				queue.addAll(temp);
				temp.clear();
				answer++;
			}
		}

		if (!find) answer = -1;
		
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
	
	static boolean check(int r, int c) {
		return (r >= 0 && c >= 0 && r < N && c < M) && map[r][c] == 0;
	}
}
