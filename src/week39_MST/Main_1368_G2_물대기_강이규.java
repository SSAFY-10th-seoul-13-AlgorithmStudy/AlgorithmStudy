package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main_1368_G2_물대기_강이규 {
    /*
    0번 노드 = 우물
    적어도 1개의 논에는 우물을 파야 하므로, 0번까지 이어진 MST를 만들면 된다.
     */
    static int N;
    static int[] parent;
    static List<Edge> edges;

    private static void make() {
        parent = new int[N+1];
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }
    }

    private static int find(int a) {
        if (parent[a] == a) return a;
        return parent[a] = find(parent[a]);
    }

    private static boolean union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);
        if (aRoot == bRoot) return false;
        parent[bRoot] = aRoot;
        return true;
    }

    public static void main(String[] args) throws IOException {
        init();
        solve();
    }

    private static void solve() {
        int cnt = 0;
        int res = 0;
        for (Edge e : edges) {
            if (union(e.a, e.b)) {
                res += e.cost;
                if (++cnt == N) break;
            }
        }
        System.out.println(res);
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        edges = new ArrayList<>(N*N);

        // 우물 파는 비용
        for (int i = 1; i <= N; i++) {
            edges.add(new Edge(0, i, Integer.parseInt(br.readLine())));
        }
        // 연결비용
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                if (i == j) {
                    st.nextToken();
                } else {
                    edges.add(new Edge(i, j, Integer.parseInt(st.nextToken())));
                }
            }
        }
        Collections.sort(edges);
        make();
    }

    static class Edge implements Comparable<Edge> {
        int a, b, cost;
        Edge(int a, int b, int cost) {
            this.a = a;
            this.b = b;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return this.cost - o.cost;
        }
    }
}
