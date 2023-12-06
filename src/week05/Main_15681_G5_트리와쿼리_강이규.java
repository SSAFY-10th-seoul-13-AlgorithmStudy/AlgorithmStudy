package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_15681_G5_트리와쿼리_강이규 {

    static int N, R, Q;
    static List<Integer>[] adjList;
    static int[] subCnts;
    static boolean[] visited;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        init();
        dfs(R); // set subCnts

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < Q; i++) {
            int U = Integer.parseInt(br.readLine());
            sb.append(subCnts[U]).append("\n");
        }

        System.out.println(sb);
    }

    private static void init() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        adjList = new ArrayList[N+1];

        for (int i = 1; i <= N; i++) {
            adjList[i] = new ArrayList<>();
        }

        visited = new boolean[N+1];
        subCnts = new int[N+1];

        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            adjList[a].add(b);
            adjList[b].add(a);
        }
    }

    private static int dfs(int cur) {
        int cnt = 1;
        visited[cur] = true;

        for (int adj : adjList[cur]) {
            if (visited[adj]) continue;
            cnt += dfs(adj);
        }

        return subCnts[cur] = cnt;
    }
}
