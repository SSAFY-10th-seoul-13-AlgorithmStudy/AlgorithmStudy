package week27_DynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_1937_G3_욕심쟁이판다_김태수 {
    static int map[][], dp[][],N, ans;
    static int[][] direction = {
        {1,0},
        {0,1},
        {-1,0},
        {0,-1}
    };
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ans = 1;
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        dp = new int[N][N];
        StringTokenizer st = null;
        for(int i = 0 ; i < N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
            Arrays.fill(dp[i],-1);
        }

        for(int i = 0 ; i < N;i++){
            for(int j = 0 ; j < N ;j++){
                if(dp[i][j] == -1) dfs(i,j);
            }
        }
        System.out.println(ans);
        
    }

    public static int dfs(int i, int j){
        if(dp[i][j] != -1) return dp[i][j];
        dp[i][j] = 1;
        for(int[] dir: direction){
            int nx = dir[0] + i;
            int ny = dir[1] + j;
            if(nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
            if(map[i][j] < map[nx][ny]){
                int cnt = dfs(nx,ny) + 1;
                dp[i][j] = Math.max(dp[i][j], cnt);
                ans = Math.max(ans, dp[i][j]);
            }
        }
        return dp[i][j];
    }
}
