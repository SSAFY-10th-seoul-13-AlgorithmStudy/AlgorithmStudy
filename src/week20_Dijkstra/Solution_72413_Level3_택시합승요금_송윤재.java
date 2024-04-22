import java.util.*;
class Solution {
    static int s_arr[], a_arr[], b_arr[];
    static List<Edge>[] graph;
    static final int INF = Integer.MAX_VALUE;
    
    static class Edge implements Comparable<Edge> {
        int node;
        int dist;
        
        Edge(int node, int dist){
            this.node = node;
            this.dist = dist;
        }
        
        @Override
	    public int compareTo(Edge o) {
            return this.dist - o.dist;
        }        
    }
    
    public void init(int n){
        graph = new List[n + 1];
        for(int i = 1; i <= n; i++){
            graph[i] = new ArrayList<>();
        }
        s_arr = new int[n + 1];
        a_arr = new int[n + 1];
        b_arr = new int[n + 1];
        Arrays.fill(s_arr, INF);
        Arrays.fill(a_arr, INF);
        Arrays.fill(b_arr, INF);
    }
    
    public int solution(int n, int s, int a, int b, int[][] fares) {
        init(n);
        for(int[] fare : fares){
            graph[fare[0]].add(new Edge(fare[1], fare[2]));
            graph[fare[1]].add(new Edge(fare[0], fare[2]));
        }
        // for(int i : a_arr){
        //     System.out.print(i + " ");
        // }
        djikstra(s, s_arr);
        djikstra(a, a_arr);
        djikstra(b, b_arr);
        // System.out.println();
        // for(int i : a_arr){
        //     System.out.print(i + " ");
        // }
        
        int min = INF;
        
        for(int i = 1; i <= n; i++){
            min = Math.min(min, s_arr[i] + a_arr[i] + b_arr[i]);               
        }        
        
        return min;
    }
    
    public void djikstra(int start, int[] arr){
        arr[start] = 0;
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        
        for(Edge edge : graph[start]){
            pq.offer(edge);
            arr[edge.node] = edge.dist;
        }
        
        while(!pq.isEmpty()){
            Edge cur = pq.poll();
            for(Edge edge : graph[cur.node]){
                if(arr[edge.node] > arr[cur.node] + edge.dist){
                    pq.offer(edge);
                    arr[edge.node] = arr[cur.node] + edge.dist;
                }
            }
        }
    }
}