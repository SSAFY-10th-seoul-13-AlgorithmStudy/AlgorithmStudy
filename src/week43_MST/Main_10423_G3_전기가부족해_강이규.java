package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_10423_G3_전기가부족해_강이규 {

    static int N, M, K;
    static List<Edge>[] adjList;
    static int[] suppliers;
    static Set<Integer> visited;

    public static void main(String[] args) throws IOException {
        init();
        System.out.println(solve());
    }

    private static int solve() {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        // 시작점들 추가
        for (int i = 0; i < K; i++) {
            pq.offer(new Edge(suppliers[i], 0));
        }

        int totalCost = 0;

        while (!pq.isEmpty()) {
            Edge cur = pq.poll();
            if (visited.contains(cur.to)) continue;
            // visited
            visited.add(cur.to);
            totalCost += cur.cost;
            if (visited.size() == N) break;

            for (Edge adj : adjList[cur.to]) {
                pq.offer(adj);
            }
        }
        return totalCost;
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        visited = new HashSet<>();

        // 발전기 정보
        suppliers = new int[K];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            suppliers[i] = Integer.parseInt(st.nextToken());
        }

        // 케이블 정보
        adjList = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            adjList[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            adjList[a].add(new Edge(b, cost));
            adjList[b].add(new Edge(a, cost));
        }
    }

    static class Edge implements Comparable<Edge> {
        int to, cost;

        public Edge (int to, int cost) {
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return this.cost - o.cost; // 오름차순
        }
    }
}
