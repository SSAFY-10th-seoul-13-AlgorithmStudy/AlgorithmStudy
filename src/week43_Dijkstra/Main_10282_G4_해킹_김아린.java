import java.io.*;
import java.util.*;

public class Main {
    
    static class Com implements Comparable<Com>{
        int idx;
        int time;
        
        public Com(int idx, int time) {
            this.idx = idx;
            this.time = time;
        }
        
        @Override
        public int compareTo(Com o) {
            return this.time - o.time;
        }
    }
    
    static StringTokenizer st;
    static int N, D, C, cnt;
    static ArrayList<ArrayList<Com>> list;
    static boolean[] visited;
    static int[] dist;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int TC = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= TC; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            D = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            
            cnt = 0;
            visited = new boolean[N+1];
            dist = new int[N+1];
            list = new ArrayList<>();
            for (int i = 0; i <= N; i++) {
                dist[i] = Integer.MAX_VALUE;
                list.add(new ArrayList<>());
            }
            
            for (int i = 0; i < D; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int s = Integer.parseInt(st.nextToken());
                
                list.get(b).add(new Com(a, s));
            }
            
            dij(C);
            
            int ans = 0;
            for (int i = 1; i <= N; i++) {
                if(dist[i] != Integer.MAX_VALUE) {
                    ans = Math.max(ans, dist[i]);
                    cnt++;
                }
            }
            
            System.out.println(cnt + " " + ans);
        }
    }
    
    static void dij(int s) {
        PriorityQueue<Com> q = new PriorityQueue<>();
        //시작
        dist[s] = 0;
        q.add(new Com(s, 0));
        
        while(!q.isEmpty()) {
            int now = q.poll().idx;
            
            if(!visited[now]) {
                visited[now] = true;
                // cnt++;
                
                for (Com next : list.get(now)) {
                    if(dist[next.idx] > dist[now] + next.time) {
                        dist[next.idx] = dist[now] + next.time;
                        q.add(new Com(next.idx, dist[next.idx]));
                    }
                }
            }
        }
        
    }
}
