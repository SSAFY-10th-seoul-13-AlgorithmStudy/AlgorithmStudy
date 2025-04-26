package week63_Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main_17837_G2_새로운게임2_신문영 {
	static final int WHITE = 0;
	static final int RED = 1;
	static final int BLUE = 2;
	static int[][] direction = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		List<Horse>[][] horsePositions = new List[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				horsePositions[i][j] = new ArrayList<>(K);
			}
		}
		
		Horse[] horses = new Horse[K];
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			horses[i] = new Horse(i, Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1);
			horsePositions[horses[i].x][horses[i].y].add(horses[i]);
		}
		
		int round = 0;
		loop:
		while (round <= 1000) {
			for (int i = 0; i < K; i++) {
				Horse current = horses[i];
				
				List<Horse> movableHorses = horsePositions[current.x][current.y].subList(horsePositions[current.x][current.y].indexOf(current), horsePositions[current.x][current.y].size());
				
				int nextX = current.x + direction[current.direction][0];
				int nextY = current.y + direction[current.direction][1];
				
				if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= N || map[nextX][nextY] == BLUE) {
					current.direction = reverseDirection(current.direction);
					nextX = current.x + direction[current.direction][0];
					nextY = current.y + direction[current.direction][1];
				}
				
				if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= N || map[nextX][nextY] == BLUE) continue;
				
				if (map[nextX][nextY] == RED) Collections.reverse(movableHorses);
				
				horsePositions[nextX][nextY].addAll(movableHorses);
				
				horsePositions[current.x][current.y] = horsePositions[current.x][current.y].subList(0, horsePositions[current.x][current.y].size() - movableHorses.size());

				for (Horse horse : movableHorses) {
					horse.x = nextX;
					horse.y = nextY;
				}
				
				if (horsePositions[nextX][nextY].size() >= 4) {
					round++;
					break loop;
				}
			}
			
			round++;
		}
		
		System.out.println(round <= 1000 ? round : -1);
	}
	
	public static int reverseDirection(int direction) {
		if (direction == 0) return 1;
		if (direction == 1) return 0;
		if (direction == 2) return 3;
		if (direction == 3) return 2;
		return -1;
	}
	
	static class Horse {
		int number;
		int x;
		int y;
		int direction;
		
		Horse (int number, int x, int y, int direction) {
			this.number = number;
			this.x = x;
			this.y = y;
			this.direction = direction;
		}
		
		@Override
		public boolean equals(Object o) {
			if (o instanceof Horse) {
				Horse horse = (Horse) o;
				return this.number == horse.number;
			}
			
			return false;
		}
		
		public String toString() {
			return "(" + number + ", " + x + ", " + y + ", " + directionToString(direction) + ")";
		}
		
		private String directionToString(int direction) {
			switch (direction) {
				case 0:
					return ">";
				case 1:
					return "<";
				case 2:
					return "^";
				case 3:
					return "v";
				default:
					return "";
			}
		}
	}
}
