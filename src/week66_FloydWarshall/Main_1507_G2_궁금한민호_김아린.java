import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static StringTokenizer st;
    static int[][] map, dist;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        dist = new int[N][N];
        
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j= 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                dist[i][j] = map[i][j];
            }
        }
        
        //dist[i][j] 값과 어떤 k 를 거칠 때 의 dist[i][k]+dist[k][j] 값이 같다면  i->j 와 i->k->j 이므로 i->j 간선이 필요 없어지는 것을 이용합니다.
        for(int i = 0; i < N; i++) {
            for (int a = 0; a < N; a++) {
                for(int b = 0; b < N; b++) {
                    if(a == b || b == i || a == i) continue;
                    if(dist[a][b] == dist[a][i] + dist[i][b]) {
                        map[a][b] = 0;
                        // System.out.println("?");
                    }
                    if(dist[a][b] > dist[a][i] + dist[i][b]) {
                        System.out.println("-1");
                        return;
                    }
                }
            }
        }
        
        int sum = 0;
        for (int i = 0; i < N; i++) {
            for(int j = i+1; j < N; j++) {
                sum += map[i][j];
            }
        }
        
        System.out.println(sum);
        
    }
}
