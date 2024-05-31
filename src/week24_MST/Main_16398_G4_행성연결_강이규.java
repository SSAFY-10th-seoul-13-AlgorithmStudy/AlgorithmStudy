package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_16398_G4_행성연결_강이규 {

    static int N;
    static int[] parent;
    static List<Edge> edges;


    private static void make() {
        parent = new int[N];
        for (int i = 0; i < N; i++) {
            parent[i] = i;
        }
    }

    private static int find(int a) {
        if (parent[a] == a)
            return a;
        return parent[a] = find(parent[a]);
    }

    private static boolean union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);

        if (aRoot == bRoot)
            return false;

        parent[bRoot] = aRoot;
        return true;
    }

    public static void main(String[] args) throws IOException {
        init();
        Collections.sort(edges);

        int n = N;
        long cost = 0;

        for (Edge e : edges) {
            if (union(e.x, e.y)) {
                cost += e.c;
                if (--n == 1)
                    break;
            }
        }
        System.out.println(cost);
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        edges = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int c = Integer.parseInt(st.nextToken());
                if (j > i)
                    edges.add(new Edge(i, j, c));
            }
        }
        make();
    }
    
    static class Edge implements Comparable<Edge> {
        int x, y, c;
        public Edge(int x, int y, int c) {
            this.x = x;
            this.y = y;
            this.c = c;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.c, o.c);
        }
    }
}

