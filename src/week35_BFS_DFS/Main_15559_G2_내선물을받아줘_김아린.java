import java.util.*;
import java.io.*;

public class Main {
    static int N, M;
    static int[][] map;
    static int[][] visited;
    static int cycle, ans;
    //남 : 0 북 : 1 동 : 2 서 : 3
    static int[][] dir = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        visited = new int[N][M];
        map = new int[N][M];
        cycle = 0;
        ans = 0;
        
        for (int i = 0; i < N; i++) {
            String tmp = br.readLine();
            for (int j = 0; j < M; j++) {
                switch(tmp.charAt(j)) {
                    case 'S':
                        map[i][j] = 0;
                        break;
                    case 'W':
                        map[i][j] = 3;
                        break;
                    case 'E':
                        map[i][j] = 2;
                        break;
                    case 'N':
                        map[i][j] = 1;
                        break;
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(visited[i][j] == 0) {
                    //사이클 개수 세는 문제 아닌가?
                    //아!!!!!!!! 사이클이 아닌 것도 있구나
                    cycle++;
                    dfs(i, j);
                }
            }
        }
        
        System.out.println(ans);
    }
    
    public static void dfs(int x, int y) {
        visited[x][y] = cycle;
        
        int cx = x + dir[map[x][y]][0];
        int cy = y + dir[map[x][y]][1];
        
        if(visited[cx][cy] == 0) {
            dfs(cx, cy);
        } else {
            if(visited[cx][cy] == cycle)
                ans++;
        }
    }
}
