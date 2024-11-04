import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int N, T, map[][][];
    static Signal[] sigs;
    // 서남동북
    static int dr[] = {0, 1, 0, -1};
    static int dc[] = {-1, 0, 1, 0};
    
    static class Car{
        int r, c, in;
        public Car(int r, int c, int in){
            this.r = r;
            this.c = c;
            this.in = in;
        }
    }

    static class Signal{
        int in, ban;
        public Signal(int in, int ban){
            this.in = in;
            this.ban = ban;
        }
    }
    
    static void init(){
        map = new int[N][N][4];
            
        sigs = new Signal[13];
        sigs[1] = new Signal(0, -1);
        sigs[2] = new Signal(1, -1);
        sigs[3] = new Signal(2, -1);
        sigs[4] = new Signal(3, -1);
        sigs[5] = new Signal(0, 1);
        sigs[6] = new Signal(1, 2);
        sigs[7] = new Signal(2, 3);
        sigs[8] = new Signal(3, 0);
        sigs[9] = new Signal(0, 3);
        sigs[10] = new Signal(1, 0);
        sigs[11] = new Signal(2, 1);
        sigs[12] = new Signal(3, 2);
    }

    static void input() throws IOException{
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        init();
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                st = new StringTokenizer(br.readLine());
                for(int k = 0; k < 4; k++){
                    map[i][j][k] = Integer.parseInt(st.nextToken());
                }
            }
        }
    }

    static void solve(){
        Queue<Car> que = new ArrayDeque<>();
        que.offer(new Car(0, 0, 1));
        int time = 0;
        boolean visited[][] = new boolean[N][N];
        visited[0][0] = true;
        
        while(T > time){
            for(int q = 0, size = que.size(); q < size; q++){
                Car cur = que.poll();
                int time_idx = time % 4;
                Signal sig = sigs[map[cur.r][cur.c][time_idx]];
                // System.out.println("현재 위치 : " + cur.r + ", " + cur.c);
                
                if(sig.in != cur.in) { // 신호가 안 맞는 경우
                    continue;
                }

                for(int d = 0; d < 4; d++){
                    if(d == sig.in || d == sig.ban) continue;
                    int nr = cur.r + dr[d];
                    int nc = cur.c + dc[d];

                    if(nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
                    
                    que.offer(new Car(nr, nc, (d + 2) % 4));
                    visited[nr][nc] = true;
                    // System.out.println("다음 위치 : " + nr + ", " + nc);
                }                
            }
            time++;
        }
        
        int cnt = 0;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){                
                if(visited[i][j])
                    cnt++;
            }
        }
        sb.append(cnt);
    }
    
    public static void main(String[] args) throws IOException{
        input();
        solve();

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
