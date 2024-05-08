package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main_16562_G4_친구비_강이규 {

    private static int N, M, k;
    private static int[] cost;
    private static int[] parent;
    private static int total = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        cost = new int[N+1];
        parent = new int[N+1];
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }
        // 비용
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            cost[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            union(a, b);
        }
        Set<Integer> visited = new HashSet<>();
        for (int i = 1; i <= N; i++) {
            int cur = find(i);
            total += visited.contains(cur) ? 0 : cost[cur];
            visited.add(cur);

        }
        System.out.println(total <= k ? total : "Oh no");
    }

    private static boolean union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);
        if (cost[bRoot] <= cost[aRoot]) {
            parent[aRoot] = bRoot;
        } else {
            parent[bRoot] = aRoot;
        }
        return true;
    }

    private static int find(int a) {
        if (parent[a] == a) {
            return a;
        }
        return parent[a] = find(parent[a]);
    }
}
