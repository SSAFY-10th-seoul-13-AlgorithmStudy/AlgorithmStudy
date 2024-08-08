import java.util.*;
import java.io.*;

class Point {
    int x;
    int y;
    
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {
    static int N, M;
    static int[][] map;
    static Deque<Point> q;
    static int[][] dir = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    public static void main(String[] args) throws Exception{
        //bfs를 많이 돌린다?
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        map = new int[N][M];
        q = new ArrayDeque<>();
        
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 1)
                    q.add(new Point(i, j));
            }
        }
        
        int cnt = 0;
        while(!q.isEmpty()) {
            melt();   
            cnt++;
        }
        
        System.out.println(cnt);
    }
    
    public static void melt() {
        //가장자리만 판단이 되나? 안되는것 같음
        //외부 공기 찾고
        findOutside();
        
        // for (int i = 0; i < N; i++) {
            // for (int j = 0; j < M; j++) {
                // System.out.print(map[i][j] + " ");
            // }
            // System.out.println();
        // }
        // System.out.println();
        
        //치즈 녹이고        
        int size = q.size();
        for (int a = 0; a < size; a++) {
            Point now = q.poll();
            int cnt = 0;
            
            for (int i = 0; i < 4; i++) {
                int cx = now.x + dir[i][0];
                int cy = now.y + dir[i][1];
                
                if (cx < 0 || cy < 0 || cx >= N || cy >= M) continue;
                if (map[cx][cy] == -1)
                    cnt++;
            }
            
            if (cnt >= 2) {
               map[now.x][now.y] = 0; 
            }
            else {
                q.addLast(now);
            }
        }            
    }
    
    public static void findOutside() {
        Queue<Point> qq = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][M];
        qq.add(new Point(0, 0));
        visited[0][0] = true;
        
        while(!qq.isEmpty()) {
            Point now = qq.poll();
            
            for (int i = 0; i < 4; i++) {
                int cx = now.x + dir[i][0];
                int cy = now.y + dir[i][1];
                
                if (cx < 0 || cy < 0 || cx >= N || cy >= M) continue;
                if (visited[cx][cy]) continue;
                if (map[cx][cy] == 1) continue; //치즈라면?! 혹은 치즈 안이라면?
                
                map[cx][cy] = -1; // 공기임
                visited[cx][cy] = true;
                qq.add(new Point(cx, cy));   
            }
        }
    }
}
