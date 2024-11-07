package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_10282_G4_해킹_강이규 {

    static int N, D, START;
    static List<Edge>[] adjList;
    static Set<Integer> visited;
    static int[] dist;

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder output = new StringBuilder();


    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            init();
            solve();
        }
        System.out.print(output);
    }

    private static void solve() {
        dist = new int[N + 1];
        Arrays.fill(dist, 10_000_001);
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        int cnt = 0;
        long time = 0;
        // add start
        dist[START] = 0;
        cnt++;
        for (Edge adj : adjList[START]) {
            dist[adj.node] = adj.cost;
            pq.offer(adj);
        }

        while (!pq.isEmpty()) {
            Edge cur = pq.poll();
            int curDist = dist[cur.node];
            // 나중에 추가한 간선이 더 가까울 수 있다.
            // 시작점의 인접 노드들로 인해 = 조건은 거르지 않는다.
            if (cur.cost > curDist) continue;
            // visited
            time = Math.max(time, curDist);
            cnt++;

            for (Edge adj : adjList[cur.node]) {
                int newDist = curDist + adj.cost;
                if (newDist < dist[adj.node]) { // = 조건은 여기서 거른다. 그럼 시작점의 인접노드들 제외하곤 걸러진다.
                    dist[adj.node] = newDist;
                    pq.offer(new Edge(adj.node, newDist));
                }
            }
        }
        output.append(cnt).append(" ").append(time).append("\n");
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        START = Integer.parseInt(st.nextToken());

        adjList = new ArrayList[N+1];
        for (int i = 1; i <= N; i++) {
            adjList[i] = new ArrayList<>();
        }

        for (int i = 0; i < D; i++) {
            st = new StringTokenizer(br.readLine());

            int to = Integer.parseInt(st.nextToken());
            int from = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            adjList[from].add(new Edge(to, cost));
        }

        visited = new HashSet<>();
    }

    static class Edge implements Comparable<Edge> {
        int node, cost; // cost = max 10,000,000

        public Edge(int node, int cost) {
            this.node = node;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return this.cost - o.cost;
        }
    }
}
