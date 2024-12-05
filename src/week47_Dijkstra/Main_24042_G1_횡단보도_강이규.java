package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_24042_G1_횡단보도_강이규 {

    static int N, M;
    static List<Edge>[] edges;
    static long[] dists;

    static class Edge implements Comparable<Edge> {
        int to;
        long when;

        public Edge(int to, long when) {
            this.to = to;
            this.when = when;
        }

        @Override
        public int compareTo(Edge o) {
            return Long.compare(this.when, o.when);
        }
    }

    public static void main(String[] args) throws IOException {
        init();
        dijkstra(1);
        System.out.println(dists[N]);
    }

    private static void dijkstra(int s) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(s, -1));
        dists[s] = 0;

        while (!pq.isEmpty()) {
            Edge cur = pq.poll();
            int curNode = cur.to;

            for (Edge e : edges[curNode]) {
                long newDist = (dists[curNode] <= e.when ? e.when
                        : getMinBiggerOrEq(e.when, dists[curNode])) + 1; // 이동시간 1분

                if (newDist < dists[e.to]) {
                    dists[e.to] = newDist;
                    pq.offer(new Edge(e.to, newDist));
                }
            }
        }
    }

    private static long getMinBiggerOrEq(long shouldBigger, long than) {
        long result = shouldBigger + M * (than / M);
        return result >= than ? result : result + M;
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        dists = new long[N+1];
        Arrays.fill(dists, Long.MAX_VALUE);
        edges = new ArrayList[N+1];
        for (int i = 1; i <= N; i++) {
            edges[i] = new ArrayList<>();
        }

        for (int time = 0; time < M; time++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            edges[a].add(new Edge(b, time));
            edges[b].add(new Edge(a, time));
        }
    }
}
