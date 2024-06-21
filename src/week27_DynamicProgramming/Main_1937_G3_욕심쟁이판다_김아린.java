import java.util.*;
import java.io.*;

public class Main {
    static int[][] dir = {{1,0}, {0,1}, {-1,0}, {0,-1}};
    static int n;
    static int[][] map;
    static int[][] dp;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st = null;
        
        map = new int[n][n];
        dp = new int[n][n];
        
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        //흠 느낌상 다 확인할 순 없을 것 아 그래서 dp
        //해당 경로.. 도착점이 가지는 최대값을 갱신해주기?
        //비슷한 문제 언젠가 풀었던 것 같은데
        //흠 dfs로 재귀 돌아가야겠다
        
        int ans = 0;
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                ans = Math.max(ans, dfs(i, j));
            }
        }   
        
        System.out.println(ans);
        
    }
    
    public static int dfs(int x, int y) {
        if(dp[x][y] != 0)
            return dp[x][y];
            
       dp[x][y] = 1; //최소 하나는 먹겠지
        
        //dfs 돌기
        for (int i = 0; i < 4; i++) {
            int dx = x + dir[i][0];
            int dy = y + dir[i][1];
            
            if (dx < 0 || dy < 0 || dx >= n || dy >= n) continue;
            
            if(map[x][y] < map[dx][dy]) { //판다가 먹을만한게 있으면?
                dp[x][y] = Math.max(dp[x][y], dfs(dx, dy)+1);
                //그니까 이 지점까지 오게 된 경로 중 가장 최대..니까
            }
        }
        
        return dp[x][y];
    }
}
