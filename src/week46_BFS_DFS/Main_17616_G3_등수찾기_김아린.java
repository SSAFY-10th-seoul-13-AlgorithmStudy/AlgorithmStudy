import java.util.*;
import java.io.*;

public class Main {
    static int N, M, X;
    public static void main(String[] args) throws Exception{
            // union find?
            //뭔가 수상하게 예외케이스가 있을 것 같은데..
            //아 둘이 각각 다른 부모를 가지면 구할 수 엄ㅅ음
            // bfs
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        ArrayList<Integer>[] loser = new ArrayList[N+1];
        ArrayList<Integer>[] winner = new ArrayList[N+1];
        
        for (int i = 1; i <= N; i++) {
            loser[i] = new ArrayList<>();
            winner[i] = new ArrayList<>();
        }
        
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());
            
            loser[w].add(l);
            winner[l].add(w);
        }
        int u = bfs(winner);
        int v = N+1 - bfs(loser);
        System.out.print(u + " " + v);
    }
    
    public static int bfs(List<Integer>[] list) {
        boolean[] visited = new boolean[N+1];
        Queue<Integer> q = new ArrayDeque<>();
        q.add(X);
        visited[X] = true;
        
        int cnt = 0;
        
        while(!q.isEmpty()) {
            int now = q.poll();
            cnt++;
            
            for (int next : list[now]) {
                if(visited[next]) continue;
                visited[next] = true;
                q.add(next);
            }
        }
        
        return cnt;
    }
}
