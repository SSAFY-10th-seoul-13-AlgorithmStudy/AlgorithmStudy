package week10_BackTracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_18430_G4_무기공학_김태수 {
	static int N,M,answer,map[][];
	static boolean[][] v;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		v = new boolean[N][M];
		for(int i = 0; i < N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < M;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dfs(0,0);
		System.out.println(answer);
	}
	
	public static void dfs(int count, int sum) {
		if(count == M * N) {
			answer = Math.max(answer, sum);
			return;
		}
//		if(x == N) {
//			answer = Math.max(answer, sum);
//			return;
//		}
//		int nx = 0;
//		int ny = 0;
//		if(y == M-1) {
//			nx = x+1;
//			ny = 1;
//		}
//		else {
//			nx = x;
//			ny = y+1;
//		}
		int x = count / M;
		int y = count % M;
		// ㅢ 형태
		if(x-1 >= 0 && y-1>= 0 && !v[x-1][y] && !v[x][y] && !v[x][y-1]) {
			v[x-1][y] = true;
			v[x][y] = true;
			v[x][y-1] = true;
			int now = map[x-1][y] + 2*map[x][y] + map[x][y-1];
			dfs(count+1,sum + now);
			v[x-1][y] = false;
			v[x][y] = false;
			v[x][y-1] = false;
		}
		//ㄴ 형태
		if(x+1 < N && y-1>= 0 && !v[x+1][y] && !v[x][y] && !v[x][y-1]) {
			v[x+1][y] = true;
			v[x][y] = true;
			v[x][y-1] = true;
			int now = map[x+1][y] + 2*map[x][y] + map[x][y-1];
			dfs(count+1,sum + now);
			v[x+1][y] = false;
			v[x][y] = false;
			v[x][y-1] = false;
		}
		//ㄱ 형태
		if(x-1 >= 0 && y+1<M && !v[x-1][y] && !v[x][y] && !v[x][y+1]) {
			v[x-1][y] = true;
			v[x][y] = true;
			v[x][y+1] = true;
			int now = map[x-1][y] + 2*map[x][y] + map[x][y+1];
			dfs(count+1,sum + now);
			v[x-1][y] = false;
			v[x][y] = false;
			v[x][y+1] = false;
		}
		//나머지
		if(x+1 <N && y+1 <M && !v[x+1][y] && !v[x][y] && !v[x][y+1]) {
			v[x+1][y] = true;
			v[x][y] = true;
			v[x][y+1] = true;
			int now = map[x+1][y] + 2*map[x][y] + map[x][y+1];
			dfs(count+1,sum + now);
			v[x+1][y] = false;
			v[x][y] = false;
			v[x][y+1] = false;
		}
		//그냥 넘기기
		dfs(count+1,sum);
	}
}
