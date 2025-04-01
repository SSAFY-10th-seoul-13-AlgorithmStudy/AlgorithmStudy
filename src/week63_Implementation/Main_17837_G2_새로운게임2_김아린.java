import java.util.*;
import java.io.*;

public class Main {
    static class Horse {
        int i, r, c, d;
        
        public Horse(int i, int r, int c, int d) {
            this.i = i;
            this.r = r;
            this.c = c;
            this.d = d;
        }
    }
    
    static int N, K;
    static int[][] map;
    static ArrayList<Integer>[][] list;
    static Horse[] hs;
    static int[][] dir = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}}; // 우좌상하
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        
        hs = new Horse[K];
        map = new int[N+2][N+2];
        list = new ArrayList[N+2][N+2];
        
        for (int i = 0; i <= N+1; i++) {
            for (int j = 0; j <= N+1; j++) {
                list[i][j] = new ArrayList<>();
                // 일단 2(파랑)로 초기화
                map[i][j] = 2;
            }
        }

        //입력
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken()) - 1;
            
            list[r][c].add(i);
            hs[i] = new Horse(i, r, c, d);
        }
        
        for (int round = 1; round <= 1000; round++) {
            
            //전체 말 한번씩
            for (int i = 0; i < K; i++) {
                Horse now = hs[i]; // 현재 말
                
                // 다음 칸
                int nr = now.r + dir[now.d][0];
                int nc = now.c + dir[now.d][1];

                // 파란칸
                if (!inRange(nr, nc) || map[nr][nc] == 2) {
                    now.d = reverseDir(now.d); // 뒤집기

                    nr = now.r + dir[now.d][0];
                    nc = now.c + dir[now.d][1];
    
                    // 그래도 똑같으면? 넘어감
                    if (!inRange(nr, nc) || map[nr][nc] == 2) {
                        hs[i] = now; // 말 방향만
                        continue;
                    }
                }
              
                ArrayList<Integer> tmp = list[now.r][now.c];
                int index = tmp.indexOf(now.i);
                // 현재 말 ~ 그 위 전부
                List<Integer> sub = tmp.subList(index, tmp.size());

                // 1(빨강) 이면 뒤집기
                if (map[nr][nc] == 1) {
                    Collections.reverse(sub);
                }
              
                list[nr][nc].addAll(sub); // 옮기고
                sub.clear(); // 원래칸 지우기

                for (int horseId : list[nr][nc]) {
                    hs[horseId].r = nr;
                    hs[horseId].c = nc;
                }

                // 종료조건
                if (list[nr][nc].size() >= 4) {
                    System.out.println(round);
                    return;
                }

                hs[i] = now;
            }
        }
        
        System.out.println(-1);
    }
    
    static boolean inRange(int x, int y) {
        return x >= 1 && x <= N && y >= 1 && y <= N;
    }
    
    static int reverseDir(int d) {
        if (d == 0) return 1;
        else if (d == 1) return 0;
        else if (d == 2) return 3;
        else return 2;
    }
}
