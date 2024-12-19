import java.util.*;
import java.io.*;

public class Main {
    static int N, M;
    static StringTokenizer st;
    static BufferedReader br;
    static ArrayList<Cross>[] area;
    static long[] dist;
    
    static class Cross implements Comparable<Cross>{
        int node;
        long idx;
        
        public Cross(int node, long idx) {
            this.node = node;
            this.idx = idx;
        }
        
        @Override
        public int compareTo(Cross o) {
            return Long.compare(this.idx, o.idx);
        }   
    }
    
    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        area = new ArrayList[N+1];
        for (int i = 0; i <= N; i++) {
            area[i] = new ArrayList<>();
        }
        
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            
            //주기에 따라 가중치가 동적으로 변경되므로 향후 판단할 수 있는 i를 cost 자리에 넣음
            area[a].add(new Cross(b, i));
            area[b].add(new Cross(a, i));
        }
        
        System.out.println(dijkstra());
    }
    
    public static long dijkstra() {
        PriorityQueue<Cross> pq = new PriorityQueue<>();
        dist = new long[N+1];
        
        Arrays.fill(dist, Long.MAX_VALUE);
        
        pq.add(new Cross(1, 0));
        dist[1] = 0;
        
        while(!pq.isEmpty()) {
            Cross now = pq.poll();
            
            if(now.node == N) break;
            if(dist[now.node] < now.idx) continue;
            
            for (Cross next : area[now.node]) {
                //주기에 따라 달라짐
                long time = 0L;
                //현재 주기 내에서 해결 가능하면? 다음 idx + 1일때 도착함
                if (now.idx <= next.idx)
                    time = next.idx + 1;
                else { //다음 주기까지 가야한다면? 
                    //주기를 몇번을 돌아야 도달 가능한지 찾아야 하는데
                    //나머지 0이면 해당 주기까지 가야 가능
                    //      아니면 해당 주기+1까지 가야 가능
                    time = (now.idx - next.idx) % M == 0 ? ((now.idx - next.idx) / M) * M : ((now.idx - next.idx) / M + 1) * M;
                    time += next.idx + 1;                  
                }
                
                if(dist[next.node] > time) {
                    dist[next.node] = time;
                    pq.add(new Cross(next.node, dist[next.node]));
                }
            }
        }
        
        return dist[N];
    }
}
