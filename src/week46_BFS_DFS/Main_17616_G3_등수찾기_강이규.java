package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_17616_G3_등수찾기_강이규 {

    static int N, M, X;
    static List<Integer>[] inDegree;
    static List<Integer>[] outDegree;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        init();
        int numOfFront = search(X, inDegree);
        int numOfBehind = search(X, outDegree);
        System.out.println((numOfFront + 1) + " " + (N - numOfBehind));
    }

    private static int search(int s, List<Integer>[] target) {
        visited = new boolean[N+1];
        int cnt = 0;
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(s);

        while (!q.isEmpty()) {
            int cur = q.poll();

            for (int adj : target[cur]) {
                if (visited[adj]) continue;
                visited[adj] = true;
                q.offer(adj);
                cnt++;
            }
        }
        return cnt;
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        inDegree = new ArrayList[N+1];
        outDegree = new ArrayList[N+1];
        for (int i = 1; i <= N; i++) {
            inDegree[i] = new ArrayList<>();
            outDegree[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            inDegree[b].add(a);
            outDegree[a].add(b);
        }
    }
}
