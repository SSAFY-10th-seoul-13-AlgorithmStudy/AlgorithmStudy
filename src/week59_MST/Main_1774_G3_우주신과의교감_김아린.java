import java.util.*;
import java.io.*;

public class Main {
    static class Edge implements Comparable<Edge> {
        int x;
        int y;
        double edge;
        
        public Edge(int x, int y, double edge) {
            this.x = x;
            this.y = y;
            this.edge = edge;
        }
        
        @Override
        public int compareTo(Edge o) {
            return Double.compare(this.edge, o.edge);
        }
    }
    static class Node {
        int x;
        int y;
        
        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    
    static StringTokenizer st;
    static int N, M;
    static double ans;
    static int[] parent;
    static Node[] nodes;
    public static void main(String[] args) throws Exception {
        //최소가 되게 = 다익스트라? + 우주신들 거쳐서 교감 = 합집합..?
        //MST
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        nodes = new Node[N+1];
        ans = 0;
        parent = new int[N+1];
        
        //간선은 뭘 기준으로 했더라? 아 전체
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            
            nodes[i] = new Node(a, b);
            parent[i] = i;
        }
        
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            
            //mst...
            union(a, b);
        }
        
        for (int i = 1; i <= N; i++) {
            Node a = nodes[i];
            for (int j = i+1; j <= N; j++) {
                Node b = nodes[j];
                double tmp = Math.sqrt(Math.pow((a.x - b.x), 2) + Math.pow((a.y - b.y), 2));
                pq.add(new Edge(i, j, tmp));
            }
        }
        
        while(!pq.isEmpty()) {
            Edge o = pq.poll();
            if(find(o.x) != find(o.y)) { //부모 다름
                union(o.x, o.y);
                ans += o.edge;
            }
        }
        
        System.out.printf("%.2f", ans);
        
    }
    
    public static void union(int x, int y) {
        x = find(x);
        y = find(y);
        
        if(x != y) {
            if(x > y)
                parent[x] = y;
            else
                parent[y] = x;
        } else
            return;
    }
    
    public static int find(int x) {
        if(x == parent[x])
            return parent[x];
        return parent[x] = find(parent[x]);
    }
}
