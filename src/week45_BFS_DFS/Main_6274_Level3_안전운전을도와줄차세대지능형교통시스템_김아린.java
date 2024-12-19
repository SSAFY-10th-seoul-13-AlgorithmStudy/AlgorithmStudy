import java.io.*;
import java.util.*;

public class Main {
    static StringTokenizer st;
    // 좌 하 우 상
    static int[][] dir = {{0,-1}, {1,0}, {0,1}, {-1,0}};
    static int N, T;
    static int[][][] map;
    static class Signal {
        int in;
        int no;
        Signal(int in, int no) {
            this.in = in;
            this.no = no;
        }
    }
    static Signal[] signals; 
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        map = new int[N][N][4];
    
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < 4; k++) {
                    map[i][j][k] = Integer.parseInt(st.nextToken());
                }   
            }
        }

        // signals 초기화
        signals = new Signal[13];
        signals[1] = new Signal(0, -1);
        signals[2] = new Signal(1, -1);
        signals[3] = new Signal(2, -1);
        signals[4] = new Signal(3, -1);
        signals[5] = new Signal(0, 1);
        signals[6] = new Signal(1, 2);
        signals[7] = new Signal(2, 3);
        signals[8] = new Signal(3, 0);
        signals[9] = new Signal(0, 3);
        signals[10] = new Signal(1, 0);
        signals[11] = new Signal(2, 1);
        signals[12] = new Signal(3, 2);

        bfs();
    }

    public static void bfs() {
        // (r, c, in): in 방향으로 (r,c)에 진입한 상태
        Queue<int[]> q = new ArrayDeque<>();
        // 시작점 (0,0)에 남쪽(1) 방향으로 들어왔다고 가정
        q.add(new int[]{0, 0, 1}); 
        boolean[][][] visited = new boolean[N][N][4];
        visited[0][0][1] = true;

        int time = 0;

        while(time < T) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int[] now = q.poll();
                int r = now[0], c = now[1], inDir = now[2];

                Signal nowSig = signals[map[r][c][time % 4]];

                // 현재 칸 신호가 들어온 방향과 맞지 않으면 통과 불가
                if(nowSig.in != inDir) continue;

                // 신호에 따라 이동 가능한 방향 탐색
                for (int j = 0; j < 4; j++) {
                    // in 또는 no 방향은 제외
                    if(j == nowSig.in || j == nowSig.no) continue;
                    int nr = r + dir[j][0];
                    int nc = c + dir[j][1];
                    if(nr < 0 || nc < 0 || nr >= N || nc >= N) continue;

                    int nextIn = (j + 2) % 4; // 반대 방향으로 진입한 것으로 기록
                    // if(!visited[nr][nc][nextIn]) {
                        visited[nr][nc][nextIn] = true;
                        q.add(new int[]{nr, nc, nextIn});
                    // }
                }
            }
            time++;
        }

        int sum = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                // 이 칸에 어떤 방향으로든 한 번 방문했으면 카운트
                for (int d = 0; d < 4; d++) {
                    if(visited[i][j][d]) {
                        sum++;
                        break;
                    }
                }
            }
        }

        System.out.println(sum);
    }
}
