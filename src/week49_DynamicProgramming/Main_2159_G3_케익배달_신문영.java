package week49_DynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main_2159_G3_케익배달_신문영 {
	static int[][] direction = {{0, 0}, {0, 1}, {0, -1}, {1, 0}, {-1, 0}};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		Set<Pair> previous = new HashSet<>();
		previous.add(new Pair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), 0));
		
		Queue<Pair> queue = new ArrayDeque<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			queue.add(new Pair(x, y, 0));
		}
		
		long answer = 0;
		long minDistance = Long.MAX_VALUE;
		Set<Pair> temp = new HashSet<>();
		while (!queue.isEmpty()) {
			Pair current = queue.poll();
			minDistance = Long.MAX_VALUE;
			for (int i = 0; i < 5; i++) {
				int clientX = current.x + direction[i][0];
				int clientY = current.y + direction[i][1];
				
				if (clientX < 0 || clientY < 0 || clientX >= 100_001 || clientY >= 100_001) continue;
				
				for (Pair pair : previous) {
					int dist = getDistance(pair, clientX, clientY);
					
					if (minDistance < dist) continue;
					
					if (minDistance > dist) {
						temp.clear();
						minDistance = dist;
					}
					
					temp.add(new Pair(clientX, clientY, pair.totalDistance + minDistance));
				}
			}
			
			previous.clear();
			previous.addAll(temp);
			temp.clear();
			
			answer += minDistance;
		}
		
		System.out.println(answer);
	}
	
	public static int getDistance(Pair a, Pair b) {
		return Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
	}
	
	public static int getDistance(Pair a, int x, int y) {
		return Math.abs(a.x - x) + Math.abs(a.y - y);
	}
	
	public static class Pair {
		int x;
		int y;
		long totalDistance;
		
		Pair (int x, int y, long totalDistance) {
			this.x = x;
			this.y = y;
			this.totalDistance = totalDistance;
		}
		
		@Override
		public String toString() {
			return "[" + x + ", " + y + ", " + totalDistance + "]";
		}
		
		@Override
		public boolean equals(Object o) {
			if (o instanceof Pair) {
				Pair p = (Pair) o;
				return this.x == p.x && this.y == p.y && this.totalDistance == p.totalDistance;
			} 
			return false;
		}
		
		@Override
		public int hashCode() {
			return this.x * 3203 + this.y + (int) this.totalDistance % (Integer.MAX_VALUE - (3203 + 100_000));
		}
	}
}
