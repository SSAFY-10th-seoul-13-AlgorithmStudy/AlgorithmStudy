import java.util.*;
import java.io.*;

class Point implements Comparable<Point> {
    int x;
    int y;
    
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    @Override
    public int compareTo(Point p) {
        if(p.y == this.y)
            return Integer.compare(p.x, this.x);
        return Integer.compare(p.y, this.y);
    }
}

public class Main {
    static int R, C;
    static char[][] map;
    static ArrayList<Point> q;
    static int[][] dir = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    static ArrayList<Point> floatingCluster;
    
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        
        map = new char[R][C];
        q = new ArrayList<>();       
         
        for (int i = 0; i < R; i++) {
            String tmp = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = tmp.charAt(j);
                if(map[i][j] == 'x')
                    q.add(new Point(i, j));
            }
        }
        
        int N = Integer.parseInt(br.readLine());
        
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int targetLine = R - Integer.parseInt(st.nextToken());
            //막대 던져서 지우기
            remove(i % 2 == 0, targetLine);
            
            //공중에 떠있는 클러스터 판단 -> 하나만 찾기(BFS)
            floatingCluster = new ArrayList<>();
            isCluster();
            
            //떠있는 클러스터 내리기 (해당 열의 미네랄.. 따로 모아서 계산)
            dropMineral();
        }
        
        //최종 결과 출력
        for (int a = 0; a < R; a++) {
            for (int b = 0; b < C; b++) {
                System.out.print(map[a][b]);
            }
            System.out.println();
        }
    }
    
    public static void remove(boolean leftToRight, int t) {
        
        int step = leftToRight ? 1 : -1; // 증가 방향 결정
        int start = leftToRight ? 0 : C - 1; // 시작 인덱스 결정
        int end = leftToRight ? C : -1; // 종료 조건 결정

        for (int i = start; i != end; i += step) {
            if (map[t][i] == 'x') {
                map[t][i] = '.';
                q.remove(new Point(t, i));
                return;
            }
        }
    }
    
    public static void isCluster() {
        boolean[][] visited = new boolean[R][C];
        Queue<Point> bfsq = new LinkedList<>();
    
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] == 'x' && !visited[i][j]) {
                    ArrayList<Point> cluster = new ArrayList<>();
                    boolean isFloating = true;
                
                    bfsq.add(new Point(i, j));
                    visited[i][j] = true;
                
                    while (!bfsq.isEmpty()) {
                        Point now = bfsq.poll();
                        cluster.add(now);
                    
                        if (now.x == R - 1) {
                            isFloating = false;
                        }
                    
                        for (int d = 0; d < 4; d++) {
                            int mx = now.x + dir[d][0];
                            int my = now.y + dir[d][1];
                        
                            if (mx < 0 || my < 0 || mx >= R || my >= C) continue;
                            if (visited[mx][my] || map[mx][my] == '.') continue;
                        
                            bfsq.add(new Point(mx, my));
                            visited[mx][my] = true;
                        }
                    }
                
                    if (isFloating) {
                        floatingCluster = cluster;
                        // for(Point c : floatingCluster) {
                            // System.out.println(c.x + " " + c.y);
                        // }
                        return;
                    }
                }
            }
        }
    
        return;
    }
    
    public static void dropMineral() {
        int maxCnt = Integer.MAX_VALUE;
        
        //맵 초기화
        for (Point c : floatingCluster) {
            map[c.x][c.y] = '.';
        }
        
        for (Point c : floatingCluster) {
            int cnt = 0;
            
            while(c.x + cnt + 1 < R && map[c.x + cnt + 1][c.y] != 'x')
                cnt++;
            
            maxCnt = Math.min(maxCnt, cnt);
        }
        
        for (Point c : floatingCluster) {
            map[c.x+maxCnt][c.y] = 'x';
        }
    }
}
