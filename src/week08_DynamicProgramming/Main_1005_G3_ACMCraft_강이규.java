package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_1005_G3_ACMCraft_강이규 {

    static int N;
    static int K;
    static int[] time;
    static List<Integer>[] adjList;
    static int[] inDegree;
    static long[] maxTime; // 그 이전까지의 최대 소요시간
    static int goal;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            init();
            sb.append(topologicalSort()).append("\n");
        }
        System.out.print(sb);
    }

    private static long topologicalSort() {
        Queue<Integer> q = new ArrayDeque<>();
        // 시작점 찾기
        for (int i = 1; i <= N; i++)
            if (inDegree[i] == 0) q.offer(i);

        while (!q.isEmpty()) {
            int cur = q.poll();

            for (int adj : adjList[cur]) {
                maxTime[adj] = Math.max(maxTime[adj], maxTime[cur] + time[cur]);
                if (--inDegree[adj] == 0) q.offer(adj);
            }
        }
        return maxTime[goal] + time[goal];
    }

    private static void init() throws IOException {
        // n, k
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        // times
        time = new int[N+1];
        maxTime = new long[N+1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            time[i] = Integer.parseInt(st.nextToken());
        }

        // K
        adjList = new ArrayList[N+1];
        inDegree = new int[N+1];
        for (int i = 1; i <= N; i++)
            adjList[i] = new ArrayList<>();

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            adjList[from].add(to);
            inDegree[to]++;
        }

        goal = Integer.parseInt(br.readLine());
    }
}
