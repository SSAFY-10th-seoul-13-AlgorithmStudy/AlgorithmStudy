package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_6497_G4_전력난_강이규 {

    static class Edge implements Comparable<Edge> {
        int a, b, cost;

        public Edge(int a, int b, int cost) {
            this.a = a;
            this.b = b;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return this.cost - o.cost;
        }
    }

    static void make() {
        parent = new int[M];
        for (int i = 0; i < M; i++) {
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

    static int N, M;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        while (true) {
            st = new StringTokenizer(br.readLine());

            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            int TOTAL_COST = 0;

            // 입력 끝일 때
            if (M < 1) break;

            PriorityQueue<Edge> pq = new PriorityQueue<>(N);

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());

                pq.offer(new Edge(a, b, cost));
                TOTAL_COST += cost;
            }

            // parent 초기화
            make();

            int selectedCnt = 0;
            int sumOfCost = 0;

            while (!pq.isEmpty()) {
                Edge cur = pq.poll();
                if (union(cur.a, cur.b)) {
                    sumOfCost += cur.cost;
                    if (++selectedCnt == M) break;
                }
            }
            sb.append(TOTAL_COST - sumOfCost).append("\n");
        }
        System.out.print(sb);
    }
}
