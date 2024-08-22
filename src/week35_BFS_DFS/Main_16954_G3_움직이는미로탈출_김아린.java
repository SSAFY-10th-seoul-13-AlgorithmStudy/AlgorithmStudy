import java.util.*;
import java.io.*;

public class Main {
    static int N = 8, M = 8;
    static ArrayDeque<int[]> wallQ;
    static int[][] map;
    static int[][] dir = {
        {1, 0}, {1, 1}, {1, -1}, 
        {0, 1}, {0, 0}, {0, -1}, 
        {-1, 0}, {-1, -1}, {-1, 1}};
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        map = new int[N][M];
        wallQ = new ArrayDeque<>();
        //무지성 bfs/dfs 문제 맞는 것 같은데..>?
        
        for (int i = 0; i < N; i++) {
            String tmp = br.readLine();
            for (int j = 0; j < M; j++) {
                if(tmp.charAt(j) == '.')
                    map[i][j] = 0;
                else {
                    map[i][j] = 1;
                    wallQ.add(new int[]{i, j});
                }
                // map[i][j] = tmp.charAt(j) == '.' ? 0 : 1;
                // if(map[i][j] == 1)
                    // wallQ.add(new int[]{i, j});
            }
        }
        
        // int ans = bfs(N-1, 0) ? 1 : 0;
        System.out.println(bfs(N-1, 0) ? 1 : 0);
    }
    
    public static boolean bfs(int x, int y) {
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] visited;
        
        q.add(new int[]{7, 0});
        
        //예전에 했던 그.. 캐슬디펜스랑 비슷한듯
        while(!q.isEmpty()) {
            int size = q.size();
            visited = new boolean[N][M];

            for (int i = 0; i < size; i++) {
                int[] now = q.poll();
                int nowX = now[0];
                int nowY = now[1];
                                
                if(map[nowX][nowY] == 1) continue;
                if(nowX == 0 && nowY == 7)
                    return true;
                if(wallQ.isEmpty()) {
                    return true;
                }
                
                for (int j = 0; j < 9; j++) {
                    int cx = nowX + dir[j][0];
                    int cy = nowY + dir[j][1];
                    
                    if(cx < 0 || cy < 0 || cx >= 8 || cy >= 8) continue;
                    if(visited[cx][cy] || map[cx][cy] == 1) continue;
                    q.add(new int[]{cx, cy});
                    // System.out.println(cx + " " + cy);
                    visited[cx][cy] = true;
                }
                // System.out.println();
            }
            
            // for (int i = 0; i < 8; i++) {
                // for (int j = 0; j < 8; j++) {
                    // System.out.print(map[i][j] + " ");
                // }
                // System.out.println();
            // }
            
            move();
            
        }
        return false;
    }
    
    public static void move() {
        int size = wallQ.size();
        for(int i = 0; i < size; i++) {
            int[] now = wallQ.pollLast();
            int nx = now[0];
            int ny = now[1];
            
            map[nx][ny] = 0;
            
            if(nx + 1 != N) {
                map[nx+1][ny] = 1;
                wallQ.addFirst(new int[]{nx+1, ny});
           }
        }
        
        // for (int i = 0; i < 8; i++) {
            // for (int j = 0; j < 8; j++) {
                // if(map[i][j] == 1) {
                    // map[i][j] = 0;
                    
                    // if(i == 7) continue;
                    
                    // map[i+1][j] = 1;
                // }
            // }
        // }
        
        // System.out.println("띠용?");
        // for (int i = 0; i < 8; i++) {
                // for (int j = 0; j < 8; j++) {
                    // System.out.print(map[i][j] + " ");
                // }
                // System.out.println();
        // }
    }
}
