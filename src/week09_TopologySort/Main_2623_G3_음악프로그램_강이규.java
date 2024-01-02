package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_2623_G3_음악프로그램_강이규 {

    static int N;
    static List<Integer>[] adjList;
    static int[] inDegree;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        init();
        System.out.print(bfs() ? sb.toString() : "0\n");
    }

    private static boolean bfs() {
        Queue<Integer> q = new ArrayDeque<>();
        for (int i = 1; i <= N; i++)
            if (inDegree[i] == 0) q.offer(i);
        int cnt = N;
        while (!q.isEmpty()) {
            int cur = q.poll();
            sb.append(cur).append("\n");
            cnt--;

            for (int adj : adjList[cur]) {
                if (--inDegree[adj] == 0) q.offer(adj);
            }
        }
        return cnt == 0;
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int m;
        N = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        adjList = new ArrayList[N+1];
        inDegree = new int[N+1];
        sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            adjList[i] = new ArrayList<>();
        }
        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int from;
            int cnt = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            while (--cnt > 0) {
                from = to;
                to = Integer.parseInt(st.nextToken());
                adjList[from].add(to);
                inDegree[to]++;
            }
        }
    }
}
