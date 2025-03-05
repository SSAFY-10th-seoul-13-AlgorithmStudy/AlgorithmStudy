package week60_DynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_16432_G4_떡장수와호랑이_신문영 {
	static int N;
	static int[] answer;
	static boolean[][] visited;
	static boolean[][] riceCakes;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		riceCakes = new boolean[1000][9];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int M = Integer.parseInt(st.nextToken());
			for (int j = 0; j < M; j++) {
				int riceCake = Integer.parseInt(st.nextToken()) - 1;
				riceCakes[i][riceCake] = true;
			}
		}
		
		answer = new int[N];
		visited = new boolean[1000][9];
		if (backtracking(0, -1)) {
			for (int i : answer) {
				System.out.println(i);
			}
		} else {
			System.out.println(-1);
		}
	}
	
	static boolean backtracking(int day, int beforeRiceCake) {
		if (day == N) return true;
		
		for (int i = 0; i < 9; i++) {
			if (visited[day][i]) continue;
			
			if (riceCakes[day][i] && i != beforeRiceCake) {
				answer[day] = i + 1;
				visited[day][i] = true;
				if (!backtracking(day + 1, i)) continue;
				else return true;
			}
		}
		
		return false;
	}
}
