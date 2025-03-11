import java.util.*;
import java.io.*;

public class Main {
    static StringTokenizer st;
    static ArrayList<Integer>[] list;
    static int[] visit;
    static int V, E;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        //이분그래프
        //정점 전체를 두 집합으로 분류할때 같은 그룹에 정점끼리는 연결되지 않는 상태
        
        int K = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < K; tc++) {
            st = new StringTokenizer(br.readLine());
            
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());
            
            list = new ArrayList[V+1];
            visit = new int[V+1];
            
            for (int i = 0; i <= V; i++) {
                list[i] = new ArrayList<Integer>();
            }
            
            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());
                int t1 = Integer.parseInt(st.nextToken());
                int t2 = Integer.parseInt(st.nextToken());
                
                list[t1].add(t2);
                list[t2].add(t1);
            }
            
            boolean isAns = bfs();
            
            if(isAns)
                System.out.println("YES");
            else
                System.out.println("NO");
        }
              
    }
    
    public static boolean bfs() {
        Queue<Integer> q = new ArrayDeque<>();
        
        //모든 정점들 탐색 필수
        for (int i = 1; i <= V; i++) {
            if(visit[i] == 0) { //방문 안했으면?
                q.add(i); //일단 집어넣기
                visit[i] = 1; //일단 기본 색깔
            }
            
            while(!q.isEmpty()) {
                int now = q.poll();
                
                for (int j = 0; j < list[now].size(); j++) {
                    int tmp = list[now].get(j);
                    
                    if(visit[tmp] == 0) { //방문 안했으면
                        q.add(tmp);
                        if(visit[now] >= 1)
                            visit[tmp] = visit[now] == 1 ? 2 : 1;
                    } else { //방문 했으면?
                        if(visit[tmp] == visit[now]) //근데 같으면?
                            return false;
                    }
                }
            }
        }
        return true;
    }
}
