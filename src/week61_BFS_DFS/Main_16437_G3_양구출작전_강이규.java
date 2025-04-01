package BOJ;

import java.io.*;
import java.util.*;

public class Main_16437_G3_양구출작전_강이규 {
    static int N;
    static int[] island;
    static List<Integer>[] adjList;

    public static void main(String[] args) throws IOException {
        init();
        System.out.println(dfs(1, 1));
    }

    private static long dfs(int cur, int pre) {
        long res = island[cur];
        for (int adj : adjList[cur]) {
            if (adj != pre) res += dfs(adj, cur);
        }
        return Math.max(res, 0);
        // if (res > 0) return res;
        // return 0; // cur이후 경로를 포함하면 손해인 경우, 선택X
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        island = new int[N+1];
        adjList = new ArrayList[N+1];
        for (int i = 1; i <= N; i++) {
            adjList[i] = new ArrayList<>();
        }

        for (int i = 2; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int sign = st.nextToken().equals("S") ? 1 : -1;
            island[i] = Integer.parseInt(st.nextToken()) * sign;
            int to = Integer.parseInt(st.nextToken());
            adjList[i].add(to);
            adjList[to].add(i);
        }
    }
}
