import java.util.*;
import java.io.*;

public class Main {
    static int N, M, hx, hy, ex, ey;
    static StringTokenizer st;
    static int[][] map;
    static int ans;
    static int[][] dir = {{1,0}, {0, 1}, {-1,0}, {0, -1}};
    
    static class Pair {
        int x, y;
        boolean isElim;
        
        public Pair(int x, int y, boolean isElim) {
            this.x = x;
            this.y = y;
            this.isElim = isElim;
        }
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        st = new StringTokenizer(br.readLine());
        hx = Integer.parseInt(st.nextToken()) - 1;
        hy = Integer.parseInt(st.nextToken()) - 1;
        
        st = new StringTokenizer(br.readLine());
        ex = Integer.parseInt(st.nextToken()) - 1;
        ey = Integer.parseInt(st.nextToken()) - 1;
        
        map = new int[N][M];
        ans = -1;
        
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        bfs();
        
        System.out.println(ans);
    }
    
    public static void bfs() {
        Queue<Pair> q = new ArrayDeque<>();
        boolean[][][] visited = new boolean[N][M][2]; 
        // 0 : 장애물 있, 1 : 장애물 없
        
        q.add(new Pair(hx, hy, true));
        visited[hx][hy][0] = true;
        
        int cnt = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            
            for (int i = 0; i < size; i++) {
                Pair now = q.poll();
               
                if (now.x == ex && now.y == ey) {
                    ans = cnt;
                    return;
                }
                
                for (int j = 0; j < 4; j++) {
                    int mx = now.x + dir[j][0];
                    int my = now.y + dir[j][1];
                    
                    if (mx < 0 || my < 0 || mx >= N || my >= M) continue;
                    
                    // 장애물 부순 상태에서만 이동
                    if (map[mx][my] == 1) {  // 장애물
                        if (now.isElim && !visited[mx][my][1]) {
                            q.add(new Pair(mx, my, false));
                            visited[mx][my][1] = true;
                        }
                    } else {  // 빈 칸인 경우
                        if (now.isElim && !visited[mx][my][1]) {
                            q.add(new Pair(mx, my, true));
                            visited[mx][my][1] = true;
                        } else if (!now.isElim && !visited[mx][my][0]) {
                            q.add(new Pair(mx, my, false));
                            visited[mx][my][0] = true;
                        }
                    }
                }
            }
            cnt++;
        }
        
        ans = -1;
    }
}
