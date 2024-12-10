import java.util.*;
import java.io.*;

public class Main {
    static class Coin {
        int x1;
        int y1;
        int x2;
        int y2;
        int cnt;
        
        public Coin(int x1, int y1, int x2, int y2, int cnt) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
            this.cnt = cnt;
        }
    }
    static int N, M;
    static Coin start;
    static int[][] map;
    static int[][] dir = {{1,0,0,-1},{0,-1,1,0}};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        map = new int[N][M];
        boolean isFirst = true;
        int tt1 = 0, tt2 = 0;
        // Coin start;
        for (int i = 0; i < N; i++) {
            String ttt = br.readLine();
            for (int j = 0; j < M; j++) {
                if(ttt.charAt(j) == 'o') {
                    if(isFirst) {
                        tt1 = i;
                        tt2 = j;
                        isFirst = false;
                    } else {
                        start = new Coin(tt1, tt2, i, j, 0);
                    }
                } else if (ttt.charAt(j) == '#') {
                    map[i][j] = -1;
                } else {
                    map[i][j] = 0;
                }
            }
        }
        
        // for (int i = 0; i < N; i++) {
            // for (int j = 0; j < M; j++) {
                // System.out.print(map[i][j] + " ");
            // }
            // System.out.println();
        // }
        
        System.out.println(bfs());
        
    }
    
    public static int bfs() {
        Queue<Coin> cq = new ArrayDeque<>();
        
        cq.add(start);
        
        while(!cq.isEmpty()) {
            Coin now = cq.poll();
            if(now.cnt >= 10) break;
            
            for (int i = 0; i < 4; i++) {
                int mx1 = now.x1 + dir[0][i];
                int my1 = now.y1 + dir[1][i];
                int mx2 = now.x2 + dir[0][i];
                int my2 = now.y2 + dir[1][i];
                
                if(inRange(mx1, my1) && map[mx1][my1] == -1) {
                    mx1 = now.x1;
                    my1 = now.y1;
                }
                if(inRange(mx2, my2) && map[mx2][my2] == -1) {
                    mx2 = now.x2;
                    my2 = now.y2;
                }
                
                int notDrop = 0;
                if(inRange(mx1, my1)) notDrop++;
                if(inRange(mx2, my2)) notDrop++;
                
                if(notDrop == 1)
                    return now.cnt + 1;
                else if(notDrop == 2) {
                    cq.add(new Coin(mx1, my1, mx2, my2, now.cnt+1));
                }
                
            }
            
        }
        return -1;
    }
    
    public static boolean inRange(int x, int y) {
        if (x < 0 || x >= N || y < 0 || y >= M) {
            return false;
        } return true;
    }
}
