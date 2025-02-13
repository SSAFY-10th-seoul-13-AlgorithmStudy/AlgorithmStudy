package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_1939_G3_중량제한_강이규 {

    static class Edge implements Comparable<Edge> {
        int to, limit;

        public Edge(int to, int limit) {
            this.to = to;
            this.limit = limit;
        }

        @Override
        public int compareTo(Edge o) {
            return o.limit - limit;
        }
    }
    static int N, M;
    static List<Edge>[] adjList;
    static int[] maxLimit;
    static int S, E;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        init();
        dijkstra();
        System.out.println(maxLimit[E]);
    }

    private static void dijkstra() {
        PriorityQueue<Edge> q = new PriorityQueue<>();
        maxLimit[S] = Integer.MAX_VALUE;
        q.offer(new Edge(S, Integer.MAX_VALUE));

        while (!q.isEmpty()) {
            Edge cur = q.poll();

            if (visited[cur.to]) continue;
            visited[cur.to] = true;

            for (Edge e : adjList[cur.to]) {
                int curLimit = Math.min(cur.limit, e.limit);
                if (curLimit > maxLimit[e.to]) {
                    maxLimit[e.to] = curLimit;
                    q.offer(new Edge(e.to, curLimit));
                }
            }
        }
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        adjList = new ArrayList[N+1];
        for (int i = 1; i <= N; i++) {
            adjList[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int limit = Integer.parseInt(st.nextToken());

            adjList[a].add(new Edge(b, limit));
            adjList[b].add(new Edge(a, limit));
        }

        st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        maxLimit = new int[N+1];
        visited = new boolean[N+1];
    }
}
