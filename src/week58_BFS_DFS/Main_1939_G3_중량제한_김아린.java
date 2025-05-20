import java.util.*;
import java.io.*;

public class Main {
    static class Bridge implements Comparable<Bridge> {
        int idx, kg;
        
        public Bridge(int idx, int kg) {
            this.idx = idx;
            this.kg = kg;
        }
        
        @Override
        public int compareTo(Bridge o) {
            return Integer.compare(this.kg, o.kg);
        }
    }
    
    static int N, M, start, end;
    static StringTokenizer st;
    static boolean[] visited;
    static ArrayList<Bridge>[] list;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int max = 0;
        
        list = new ArrayList[N+1];
        for (int i = 0; i <= N; i++) {
            list[i] = new ArrayList<Bridge>();
        }
        
        
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken())-1;
            int b = Integer.parseInt(st.nextToken())-1;
            int c = Integer.parseInt(st.nextToken());
            
            list[a].add(new Bridge(b, c));
            list[b].add(new Bridge(a, c));
            max = Math.max(max, c);
        }
        
        //bfs에 또 dp 적용?
        //dist[] 에 max값으로 초기화 한 다음..
        //큐를 돌면서 dist[] 값이 큐 값보다 크면? 갱신
        //dist 값을 기반으로 최대 값을 찾는거
        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken())-1;
        end = Integer.parseInt(st.nextToken())-1;
        
        long low = 0, high = max, mid;
        
        while(low <= high) {
            mid = (low + high) / 2;
            visited = new boolean[N+1];
            
            if(bfs(mid)) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        
        System.out.println(high);
    }
    
    public static boolean bfs(long weight) {
        Queue<Integer> q = new ArrayDeque<>();
        
        // boolean[] visited = new boolean[N+1];
        
        q.add(start);
        visited[start] = true;
        
        while(!q.isEmpty()) {
            int now = q.poll();
            
            if(now == end) {
                return true;
            }
            
            for (Bridge b : list[now]) {
                int next = b.idx;
                int cost = b.kg;
                
                if(!visited[next] && weight <= cost) {
                    visited[next] = true;
                    q.add(next);
                }
            }
        }
        
        return false;
    }
}
