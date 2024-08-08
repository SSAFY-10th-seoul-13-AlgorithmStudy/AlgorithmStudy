import java.io.*;
import java.util.*;

public class Main {
	public static int N,M;
	public static int board[][];
	public static int dp[][][];
	public static int dir[][] = {{0,1},{1,0},{0,-1}};
	public static boolean visited[][];

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		visited = new boolean[N+1][M+1];
		board = new int[N+1][M+1];
		dp = new int[N+1][M+1][3];
		
		for(int i=1;i<=N;i++){
			st = new StringTokenizer(br.readLine());
			for(int j = 1;j <= M; j++){
				board[i][j] = Integer.parseInt(st.nextToken());
				for(int k = 0; k < 3; k++){
					dp[i][j][k] = -1;
				}
			}
		}
		System.out.println(Math.max(dfs(1,1,0),dfs(1,1,1)));
		
	}
    
    public static int dfs(int y, int x, int bang){
		if(y == N && x == M) return board[N][M];
		if(dp[y][x][bang] != -1) 
            return dp[y][x][bang];
            
		visited[y][x] = true;
		int ans = -200000;

		for(int i = 0; i < 3; i++){
			int ny = y + dir[i][0];
			int nx = x + dir[i][1];
            if(ny < 1 || nx < 1 || ny > N || nx > M) continue;
			if(visited[ny][nx]) continue;

			ans = Math.max(ans, dfs(ny,nx,i) + board[y][x]);
		}

		visited[y][x] = false;
		return dp[y][x][bang] = ans;
	}
}
