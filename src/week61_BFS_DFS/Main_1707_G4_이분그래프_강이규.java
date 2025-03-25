package BOJ;

import java.io.*;
import java.util.*;

public class Main_1707_G4_이분그래프_강이규 {
    static int V, E;
    static List<Integer>[] adjList;
    static int[] teamNo;

    public static void main(String[] args) throws IOException {
        // init
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int K = Integer.parseInt(br.readLine());

        while (K-- > 0) {
            st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());

            teamNo = new int[V+1];
            adjList = new ArrayList[V+1];
            for (int i = 1; i <= V; i++) {
                adjList[i] = new ArrayList<>();
            }

            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                adjList[a].add(b);
                adjList[b].add(a);
            }

            boolean isBipartite = true;
            for (int i = 1; i <= V; i++) {
                if (teamNo[i] > 0) continue;
                if (dfs(i, 1) != true) {
                    isBipartite = false;
                    break;
                }
            }
            sb.append(isBipartite ? "YES" : "NO").append("\n");
        }
        System.out.println(sb);
    }

    private static boolean dfs(int cur, int team) {
        teamNo[cur] = team;

        for (int adj : adjList[cur]) {
            if (teamNo[adj] >= 1) {
                // 팀 체크 먼저, 이후 방문체크
                if (teamNo[adj] == team) return false;
                else continue;
            }
            if (!dfs(adj, 1 + (team % 2))) return false;
        }
        return true;
    }
}
