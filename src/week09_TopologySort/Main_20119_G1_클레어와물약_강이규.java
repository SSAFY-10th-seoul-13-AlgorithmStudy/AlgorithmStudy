package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_20119_G1_클레어와물약_강이규 {

    static int N, M;
    static List<Info>[] adjList;
    static List<Set<Integer>>[] recipes; // inDegree[] 역할
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static boolean[] has;

    public static void main(String[] args) throws IOException {
        init();
        topologicalSort();
        print();
    }

    private static void print() {
        StringBuilder sb = new StringBuilder();
        int cnt = 0;
        for (int i = 1; i <= N; i++) {
            if (has[i]) {
                cnt++;
                sb.append(i).append(" ");
            }
        }
        System.out.println(cnt);
        System.out.println(sb);
    }

    private static void topologicalSort() {
        Queue<Integer> q = new ArrayDeque<>();
        // start
        for (int i = 1; i <= N; i++) {
            if (has[i]) q.offer(i);
        }

        while (!q.isEmpty()) {
            int cur = q.poll();

            for (Info adj : adjList[cur]) {
                if (has[adj.t]) continue;
                Set<Integer> target = recipes[adj.t].get(adj.idx);
                target.remove(cur);
                if (target.isEmpty()) {
                    has[adj.t] = true;
                    q.offer(adj.t);
                }
            }
        }
    }

    private static void init() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        adjList = new ArrayList[N+1];
        recipes = new ArrayList[N+1];
        has = new boolean[N+1];
        for (int i = 1; i <= N; i++) {
            adjList[i] = new ArrayList<>();
            recipes[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            makeRecipe(br.readLine());
        }

        int L = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < L; i++) {
            int y = Integer.parseInt(st.nextToken());
            has[y] = true;
            for (Info adj : adjList[y]) {
                Set<Integer> target = recipes[adj.t].get(adj.idx);
                target.remove(y);
                if (target.isEmpty()) {
                    has[adj.t] = true;
                }
            }
        }
    }

    private static void makeRecipe(String line) {
        st = new StringTokenizer(line);
        int k = Integer.parseInt(st.nextToken());
        int[] xs = new int[k];
        for (int i = 0; i < k; i++) {
            xs[i] = Integer.parseInt(st.nextToken());
        }
        int t = Integer.parseInt(st.nextToken());
        recipes[t].add(new HashSet<>());
        int idx = recipes[t].size()-1;
        for (int x : xs) {
            // adjList에 추가
            adjList[x].add(new Info(t, idx));
            // recipes에 추가
            recipes[t].get(idx).add(x);
        }
    }

    static class Info {
        int t;
        int idx;
        Info(int t, int idx) {
            this.t = t;
            this.idx = idx;
        }
    }
}
