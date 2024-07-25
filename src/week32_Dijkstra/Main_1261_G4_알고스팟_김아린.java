import java.util.*;
import java.io.*;

public class Main {
    static int M, N, ans;
    static int[][] map;
    static int[][] dir = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    static boolean[][] visited;
    static Deque<int[]> q;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        
        map = new int[N][M];
        
        for (int i = 0; i < N; i++) {
            String tmp = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = tmp.charAt(j) == '0' ? 0 : 1;
            }
        }
        
        ans = Integer.MAX_VALUE;
        bfs(0, 0);
        
        System.out.println(ans);
    }
    
    public static void bfs(int x, int y) {
        q = new ArrayDeque<>();
        visited = new boolean[N][M];
        
        q.add(new int[]{x, y, 0});
        visited[x][y] = true;
        //벽을 부순게 영향을 안 끼침 = 실제 맵 변경 X
        
        while(!q.isEmpty()) {
            int[] now = q.poll();
            int nx = now[0];
            int ny = now[1];
            int cnt = now[2];
            
            if(nx == N - 1 && ny == M - 1) {
                // System.out.println(cnt);
                ans = Math.min(ans, cnt);
            }
            
            for (int i = 0; i < 4; i++) {
                int cx = nx + dir[i][0];
                int cy = ny + dir[i][1];
                
                if(cx < 0 || cy < 0 || cx >= N || cy >= M) continue;
                if(visited[cx][cy]) continue;
                
                visited[cx][cy] = true;
                if(map[cx][cy] == 0) {
                    q.addFirst(new int[]{cx, cy, cnt});   
                }
                else {
                    q.addLast(new int[]{cx, cy, cnt+1});
                }
                
                // //이미 도달한 경로가 있다면?
                // if(visited[cx][cy] != 0) {
                    // //벽이 있으면 이미 방문 or 이전 경로 + 1의 min값
                    // //벽이 없으면 이미 방문 or 이전 경로 의 min값
                    
                    // visited[cx][cy] = map[cx][cy] == 1 ? Math.min(visited[cx][cy], visited[nx][ny] + 1) : Math.min(visited[cx][cy], visited[nx][ny]);
                // } //첫 도달이라면?
                // else {
                    // //벽이 있으면 이전 경로 + 1
                    // //벽이 없으면 이전 경로
                    // visited[cx][cy] = map[cx][cy] == 1 ? visited[nx][ny] + 1 : visited[nx][ny];
                // }
            }
        }   
    }
}
