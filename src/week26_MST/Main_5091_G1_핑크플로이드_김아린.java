import java.io.*;
import java.util.*;

public class Main {
    static class Edge implements Comparable<Edge> {
        int cost;
        int u;
        int v;
        
        Edge(int cost, int u, int v) {
            this.cost = cost;
            this.u = u;
            this.v = v;
        }
        
        public int compareTo(Edge other) {
            return Integer.compare(this.cost, other.cost);
        }
    }
    
    static int[] parent;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());
        
        parent = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }
        StringTokenizer st = null;
        List<Edge> edges = new ArrayList<>();
        for(int i = 1; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = i + 1; j <= n; j++) {
				int cost = Integer.parseInt(st.nextToken());
				edges.add(new Edge(cost, i, j));
			}
		}
        
        Collections.sort(edges);
        
        List<Integer>[] adjList = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            adjList[i] = new ArrayList<>();
        }
        
        for (Edge edge : edges) {
            if (find(edge.u) != find(edge.v)) {
                adjList[edge.u].add(edge.v);
                adjList[edge.v].add(edge.u);
                union(edge.u, edge.v);
            }
        }
        
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            Collections.sort(adjList[i]);
            sb.append(adjList[i].size());
            for (int neighbor : adjList[i]) {
                sb.append(" ").append(neighbor);
            }
            sb.append("\n");
        }
        System.out.print(sb.toString());
    }
    
    static int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }
    
    static void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if (rootX != rootY) {
            parent[rootX] = rootY;
        }
    }
}
