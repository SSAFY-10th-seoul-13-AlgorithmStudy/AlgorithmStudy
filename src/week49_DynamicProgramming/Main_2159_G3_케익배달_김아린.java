import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static int[][] dir = {{1,0}, {0, 1}, {-1, 0}, {0, -1}, {0, 0}};
    static long[][] dp;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dp = new long[N+1][5];
        
        for (int i = 1; i <= N; i++) {
            for (int j = 0; j < 5; j++) {
                dp[i][j] = Long.MAX_VALUE;
            }
        }
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int startX = Integer.parseInt(st.nextToken());
        int startY = Integer.parseInt(st.nextToken());
        
        
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int tmpX = Integer.parseInt(st.nextToken());
            int tmpY = Integer.parseInt(st.nextToken());
            
            findRoute(startX, startY, tmpX, tmpY, i);
            
            startX = tmpX;
            startY = tmpY;
        }
        
        // for (int i = 0; i <= N; i++) {
            // for (int j = 0; j < 5; j++) {
                // System.out.print(dp[i][j] + " ");
            // }
            // System.out.println();
        // }
        // System.out.println();        
        
        long sum = Long.MAX_VALUE;
        // for (int i = 0; i < N; i++) {
            // sum += dp[i];
        // }
        
        for (int i = 0; i < 5; i++) {
            sum = Math.min(sum, dp[N][i]);
        }        
        System.out.println(sum);
    }
    
    public static void findRoute(int sx, int sy, int tx, int ty, int idx) {
        for(int i = 0; i < 5; i++) {
            int msx = sx + dir[i][0];
            int msy = sy + dir[i][1];
            
            if(idx == 1) {
                msx = sx;
                msy = sy;
            }
            
            if(!isBoundary(msx, msy)) continue;
            
            for (int j = 0; j < 5; j++) {
                int mtx = tx + dir[j][0];
                int mty = ty + dir[j][1];
                
                if(!isBoundary(mtx, mty)) continue;
                
                long len = Math.abs(mtx - msx) + Math.abs(mty - msy);
                // System.out.println(msx + " " + msy + " -> " + mtx + " " + mty + " => " + len);
                
                dp[idx][j] = Math.min(dp[idx-1][i] + len, dp[idx][j]);
            }
        }
    }
    
    public static boolean isBoundary(int x, int y) {
        if(x < 0 || x >= 100_000 || y < 0 || y >= 100_000)
            return false;
        return true;
    }
}
