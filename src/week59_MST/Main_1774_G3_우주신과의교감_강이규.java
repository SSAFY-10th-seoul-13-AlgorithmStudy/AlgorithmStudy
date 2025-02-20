package BOJ;

import java.io.*;
import java.util.*;

public class Main_1774_G3_우주신과의교감_강이규 {
    static class Pair {
        long x, y;

        Pair(long x, long y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Edge implements Comparable<Edge> {
        int a, b;
        double cost;

        Edge(int a, int b, double cost) {
            this.a = a;
            this.b = b;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return Double.compare(this.cost, o.cost);
        }
    }

    static int N, M;
    static Pair[] arr;
    static double[][] adjMatrix;
    static int[] parent;
    static int[][] linked;

    static void make() {
        parent = new int[N+1];
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }
    }

    static int find(int a) {
        if (parent[a] == a) return a;
        return parent[a] = find(parent[a]);
    }

    static boolean union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);

        if (aRoot == bRoot) return false;

        parent[aRoot] = bRoot;
        return true;
    }

    public static void main(String[] args) throws IOException {
        init();
        System.out.println(String.format("%.2f", solve()));
    }

    private static double solve() {
        int remainEdges = N - 1;
        // 이미 연결된 애들
        for (int i = 0; i < M; i++) {
            if (union(linked[i][0], linked[i][1])) {
                remainEdges--;
            }
        }

        // 나머지
        // PQ 생성 및 간선 넣기
        PriorityQueue<Edge> pq = new PriorityQueue();
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j < i; j++) {
                pq.offer(new Edge(i, j, adjMatrix[i][j]));
            }
        }

        // 최소 결과값 구하기
        double result = 0d;

        while (!pq.isEmpty()) {
            Edge cur = pq.poll();

            if (union(cur.a, cur.b)) {
                result += cur.cost;
                if (--remainEdges == 0) break;
            }
        }

        return result;
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new Pair[N+1];
        adjMatrix = new double[N+1][N+1];

        // arr
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            long x = Long.parseLong(st.nextToken());
            long y = Long.parseLong(st.nextToken());

            arr[i] = new Pair(x, y);
        }

        // adjMatrix
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= i; j++) {
                long xDist = Math.abs(arr[i].x - arr[j].x);
                long yDist = Math.abs(arr[i].y - arr[j].y);
                double distance = Math.sqrt(xDist * xDist + yDist * yDist);
                adjMatrix[i][j] = adjMatrix[j][i] = distance;
            }
        }

        // 이미 연결된 애들
        linked = new int[M][2];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            linked[i][0] = Integer.parseInt(st.nextToken());
            linked[i][1] = Integer.parseInt(st.nextToken());
        }

        make();
    }
}
