import java.util.*;
import java.io.*;

public class Main {
    static int N, K, END;
    static int[][] cost;
    
    static class Moving implements Comparable<Moving> {
        int c;
        int n;
        int v;
        
        public Moving(int c, int n, int v) {
            this.c = c;
            this.n = n;
            this.v = v;
        }
        
        @Override
        public int compareTo(Moving o) {
            return Integer.compare(this.c, o.c);
        }
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        
        cost = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                cost[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        for (int i = 0; i < N; i++) {
            END |= (1 << i);
        }
        
        //다익스트라로 최단경로 찾기? 근데 이제 전체를 돌아야 하는>
        System.out.println(dk());
    }
    
    public static int dk() {
        //거리 1 << 2 1 >> 2 정점/방문값
        //전체를 다 돌아야 하므로 이렇게 체크함
        int[][] dist = new int[N][1 << N];
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < (1 << N); j++) {
                dist[i][j] = Integer.MAX_VALUE;
            }
        }
        
        dist[K][1<<K] = 0;
                
        PriorityQueue<Moving> pq = new PriorityQueue<>();
        pq.add(new Moving(0, K, 1 << K));
        
        while(!pq.isEmpty()) {
            Moving now = pq.poll();
            
            int cc = now.c;
            int node = now.n;
            int visited = now.v;
            
            if(dist[node][visited] < cc) //더 좋은거 있음 넘어가기
                continue;
                
            if(visited == END) {
                return cc;
            }
            
            for (int i = 0; i < N; i++) { //다음으로 이동
                int nVisit = visited | (1 << i);
                int nCost = cc + cost[node][i];
                
                if(nCost < dist[i][nVisit]) {
                    dist[i][nVisit] = nCost;
                    pq.add(new Moving(nCost, i, nVisit));
                }
            }
        }
        
        return 0;
    }
}
