package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Array;
import java.util.*;

public class Main_2056_G4_작업_강이규 {

    static int N;
    static int[] time;
    static int[] maxPre;
    static List<Integer>[] adjList;
    static int[] inDegree;

    public static void main(String[] args) throws IOException {
        init();
        bfs();
        // find res
        int max = 0;
        for (int i = 1; i <= N; i++) {
            max = Math.max(max, maxPre[i]+time[i]);
        }
        System.out.println(max);
    }

    private static void bfs() {
        Queue<Integer> pq = new PriorityQueue<>();
        for (int i = 1; i <= N; i++)
            if (inDegree[i] == 0) pq.offer(i);

        while (!pq.isEmpty()) {
            int cur = pq.poll();

            for (int adj : adjList[cur]) {
                maxPre[adj] = Math.max(maxPre[adj], maxPre[cur] + time[cur]);
                if (--inDegree[adj] == 0) pq.offer(adj);
            }
        }
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        time = new int[N+1];
        maxPre = new int[N+1];
        inDegree = new int[N+1];
        adjList = new ArrayList[N+1];
        for (int i = 1; i <= N; i++)
            adjList[i] = new ArrayList<>();

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            time[i] = t;
            inDegree[i] = k;
            while (k-- > 0) {
                int pre = Integer.parseInt(st.nextToken());
                adjList[pre].add(i);
            }
        }
    }
}
