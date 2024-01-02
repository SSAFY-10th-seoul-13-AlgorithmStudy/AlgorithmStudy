package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_14567_G5_선수과목_강이규 {

    static int N;
    static int M;
    static List<Integer>[] adjList;
    static int[] inDegrees;
    static int[] res;

    public static void main(String[] args) throws IOException {
        init();

        topologicalSort();
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            sb.append(res[i]).append(" ");
        }
        System.out.println(sb);
    }

    private static void topologicalSort() {
        Queue<int[]> q = new ArrayDeque<>();

        for (int i = 1; i <= N; i++) {
            if (inDegrees[i] == 0) q.offer(new int[]{i, 1});
        }

        while (!q.isEmpty()) {
            int[] tmp = q.poll();
            int cur = tmp[0];
            int cnt = tmp[1];
            res[cur] = Math.max(res[cur], cnt);

            for (int adj : adjList[cur]) {
                if (--inDegrees[adj] == 0) q.offer(new int[]{adj, cnt+1});
            }
        }
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        inDegrees = new int[N+1];
        adjList = new ArrayList[N+1];
        res = new int[N+1];
        for (int i = 1; i <= N; i++) {
            adjList[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            adjList[a].add(b);
            inDegrees[b]++;
        }
    }
}
