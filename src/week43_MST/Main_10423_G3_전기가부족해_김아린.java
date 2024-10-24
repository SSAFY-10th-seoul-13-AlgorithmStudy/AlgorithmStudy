import java.util.*;
import java.io.*;

public class Main {
    static int N, M, K;
    static StringTokenizer st;
    static List<int[]> edges = new ArrayList<>();
    static int[] parent;
    static int[] rank;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        parent = new int[N + 1];
        rank = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            parent[i] = i;
            rank[i] = 1;
        }

        // 발전소 입력
        st = new StringTokenizer(br.readLine());
        List<Integer> powerPlants = new ArrayList<>();
        for (int i = 0; i < K; i++) {
            int tmp = Integer.parseInt(st.nextToken());
            powerPlants.add(tmp);
        }

        // 가상 노드 0과 모든 발전소를 연결 (비용 0)
        for (int p : powerPlants) {
            edges.add(new int[]{0, p, 0});
        }

        // 간선 입력
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            edges.add(new int[]{a, b, c});
        }

        // 비용 기준 정렬
        Collections.sort(edges, (o1, o2) -> Integer.compare(o1[2], o2[2]));

        long sum = 0;

        for (int[] edge : edges) {
            int a = edge[0];
            int b = edge[1];
            int cost = edge[2];

            if (find(a) != find(b)) {
                union(a, b);
                sum += cost;
            }
        }

        System.out.println(sum);
    }

    public static int find(int x) {
        if (parent[x] != x)
            parent[x] = find(parent[x]);
        return parent[x];
    }

    public static void union(int x, int y) {
        int rootx = find(x);
        int rooty = find(y);

        if (rootx == rooty)
            return;

        // 랭크 기준
        if (rank[rootx] < rank[rooty]) {
            parent[rootx] = rooty;
        } else {
            parent[rooty] = rootx;
            if (rank[rootx] == rank[rooty]) {
                rank[rootx]++;
            }
        }
    }
}
