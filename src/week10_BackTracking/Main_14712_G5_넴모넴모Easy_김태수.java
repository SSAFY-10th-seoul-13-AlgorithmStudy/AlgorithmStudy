package week10_BackTracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_14712_G5_넴모넴모Easy_김태수 {
	static int N,M,answer;
	static boolean[][] map;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new boolean[N+1][M+1];
		answer = 0;
		dfs(0);
		System.out.println(answer);
	}
	
	public static void dfs(int count) {
		if (count == N *M) {
			answer++;
			return;
		}
		int x = count / M + 1;
		int y = count % M + 1;
		if(map[x-1][y] && map[x][y-1] && map[x-1][y-1]) {
			dfs(count+1);
		} 
		else {
			dfs(count+1);
			map[x][y] = true;
			dfs(count+1);
			map[x][y] = false;
		}
		
		
	}
}
