package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_2887_P5_행성터널_강이규 {

    static int N;
    static Planet[] arr;
    static Pair[] sortByX;
    static Pair[] sortByY;
    static Pair[] sortByZ;
    static int[] parent;

    private static void make() {
        parent = new int[N];
        for (int i = 0; i < N; i++) {
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
        System.out.println(solve());

    }

    private static long solve() {
        long res = 0L;
        int cnt = N;

        PriorityQueue<Edge> pq = makeEdges();
        while (!pq.isEmpty()) {
            Edge cur = pq.poll();
            if (union(cur.a, cur.b)) {
                res += cur.cost;
                if (--cnt == 0) break;
            }
        }
        return res;
    }


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new Planet[N];

        sortByX = new Pair[N];
        sortByY = new Pair[N];
        sortByZ = new Pair[N];

        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
            arr[i] = new Planet(i, x, y, z);
            sortByX[i] = new Pair(i, x);
            sortByY[i] = new Pair(i, y);
            sortByZ[i] = new Pair(i, z);
        }
        Arrays.sort(sortByX);
        Arrays.sort(sortByY);
        Arrays.sort(sortByZ);

        make();
    }

    private static PriorityQueue<Edge> makeEdges() {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        for (int i = 1; i < N; i++) {
            Pair x1 = sortByX[i];
            Pair y1 = sortByY[i];
            Pair z1 = sortByZ[i];
            // x
            Pair x2 = sortByX[i-1];
            pq.offer(new Edge(x1.v, x2.v, Math.abs(x1.pos - x2.pos)));
            // y
            Pair y2 = sortByY[i-1];
            pq.offer(new Edge(y1.v, y2.v, Math.abs(y1.pos - y2.pos)));
            // z
            Pair z2 = sortByZ[i-1];
            pq.offer(new Edge(z1.v, z2.v, Math.abs(z1.pos - z2.pos)));
        }
        return pq;
    }

    static class Edge implements Comparable<Edge> {
        int a, b, cost;

        public Edge(int a, int b, int cost) {
            this.a = a;
            this.b = b;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.cost, o.cost);
        }
    }

    static class Pair implements Comparable<Pair> {
        int v, pos;
        Pair(int v, int pos) {
            this.v = v;
            this.pos = pos;
        }

        @Override
        public int compareTo(Pair o) {
            return Integer.compare(this.pos, o.pos);
        }
    }

    static class Planet {
        int v, x, y, z;

        public Planet(int v, int x, int y, int z) {
            this.v = v;
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }
}
