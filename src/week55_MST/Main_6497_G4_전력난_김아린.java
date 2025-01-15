import java.io.*;
import java.util.*;

public class Main {
    static StringTokenizer st;
    static ArrayList<Node>[] list;
    static int M, N;
    
    static class Node implements Comparable<Node> {
        int x;
        int cost;
        
        public Node(int x, int cost) {
            this.x = x;
            this.cost = cost;
        }
        
        @Override
        public int compareTo(Node o) {
            return Integer.compare(cost, o.cost);
        }
    }
    
    
    public static void main(String[] args) throws Exception {
        //MST 찾고 비용 빼기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        while(true) {
            st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            
            if(M == 0 && N == 0)
                break;
            
            list = new ArrayList[M];
            for (int i = 0; i < M; i++) {
                list[i] = new ArrayList<>();
            }
            
            int total = 0;
            
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int z = Integer.parseInt(st.nextToken());
                
                list[x].add(new Node(y, z));
                list[y].add(new Node(x, z));
                total += z;
            }
            
            int min = findMST();
            System.out.println(total-min);
        }
    }
    
    public static int findMST() {
        boolean[] visited = new boolean[M];
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(0, 0));
        
        int sum = 0;
        while(!pq.isEmpty()) {
            Node now = pq.poll();
            
            if(visited[now.x]) continue;
            visited[now.x] = true;
            
            sum += now.cost;
            for (Node next : list[now.x]) {
                if(visited[next.x]) continue;
                pq.add(next);
            }
        }
        return sum;
    }
}
