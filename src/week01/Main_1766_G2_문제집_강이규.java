package week01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_1766_G2_문제집_강이규 {

    static int N, M;
    static int[] arr;
    static int[] inDegree;
    static List<Integer>[] adjList;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N+1];
        inDegree = new int[N+1];
        adjList = new ArrayList[N+1];

        for (int i = 1; i <= N; i++) {
            adjList[i] = new ArrayList<>();
            arr[i] = i;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            adjList[a].add(b);
            inDegree[b] += 1;
        }

        topologySort();
        System.out.println(sb);
    }

    private static void topologySort() {
        Queue<Integer> pq = new PriorityQueue<>();
        // 시작점 찾기
        for (int i = 1; i <= N; i++) {
            if (inDegree[i] == 0) pq.offer(i);
        }

        while (!pq.isEmpty()) {
            int cur = pq.poll();
            sb.append(cur).append(" ");

            for (int adj : adjList[cur]) {
                if (--inDegree[adj] == 0) pq.offer(adj);
            }
        }
    }
}
