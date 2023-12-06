package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_1967_G4_트리의지름_강이규 {

    static int N;
    static Node farNode = new Node(-1, -1);
    static boolean[] visited;
    static List<Node>[] adjList;

    public static void main(String[] args) throws IOException {
        init();
        visited = new boolean[N+1];
        dfs(new Node(1, 0), 0);
        visited = new boolean[N+1];
        int tmp = farNode.cost;
        dfs(farNode, farNode.cost);


        System.out.println(farNode.cost - tmp);
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        adjList = new ArrayList[N+1];

        for (int i = 1; i <= N; i++) {
            adjList[i] = new ArrayList<>();
        }

        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            adjList[from].add(new Node(to, cost));
            adjList[to].add(new Node(from, cost));
        }
    }

    /**
     * 리프 노드까지 내려간 후, 거리를 잰다
     */
    private static void dfs(Node cur, int totalCost) {
        visited[cur.node] = true;

        int cnt = 0;

        for (Node adj : adjList[cur.node]) {
            if (visited[adj.node]) continue;
            dfs(adj, totalCost+adj.cost);
            cnt++;
        }

        if (cnt < 1) // 리프 노드일 때
            farNode = totalCost > farNode.cost ? new Node(cur.node, totalCost) : farNode;
    }


    static class Node {
        int node;
        int cost;

        Node(int node, int cost) {
            this.node = node;
            this.cost = cost;
        }
    }
}
