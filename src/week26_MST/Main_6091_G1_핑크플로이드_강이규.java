package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_6091_G1_핑크플로이드_강이규 {

    static int N;
    static int[] parent;
    static List<Edge> edges;
    static List<Integer>[] adjList;

    private static void make() {
        parent = new int[N+1];
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }
    }

    private static int find(int a) {
        if (parent[a] == a) {
            return a;
        }
        return parent[a] = find(parent[a]);
    }

    private static boolean union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);

        if (aRoot == bRoot) {
            return false;
        }
        parent[bRoot] = aRoot;
        return true;
    }


    public static void main(String[] args) throws IOException {
        init();
        makeAdjList();
        print();
    }

    private static void print() {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            List<Integer> adjs = adjList[i];
            sb.append(adjs.size()).append(" ");
            Collections.sort(adjs);
            for (int adj : adjs) {
                sb.append(adj).append(" ");
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }

    private static void makeAdjList() {
        int cnt = N;

        for (Edge e : edges) {
            if (union(e.a, e.b)) {
                adjList[e.a].add(e.b);
                adjList[e.b].add(e.a);
                if (--cnt == 1) {
                    return;
                }
            }
        }
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        adjList = new ArrayList[N+1];
        for (int i = 1; i <= N; i++) {
            adjList[i] = new ArrayList<>();
        }

        edges = new ArrayList<>();
        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = i+1; j <= N; j++) {
                int cost = Integer.parseInt(st.nextToken());
                edges.add(new Edge(i, j, cost));
            }
        }
        Collections.sort(edges);

        make();
    }

    static class Edge implements Comparable<Edge> {
        int a, b, c;
        public Edge(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.c, o.c);
        }
    }
}
