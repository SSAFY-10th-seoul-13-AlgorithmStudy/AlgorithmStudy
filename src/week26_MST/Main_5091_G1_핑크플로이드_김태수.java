package week26_MST;

import java.util.*;
import java.io.*;

public class Main_5091_G1_핑크플로이드_김태수 {
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine().trim());

        PriorityQueue<Edge> pq = new PriorityQueue<>((a, b) -> a.weight - b.weight);
        for (int i = 1; i < N; i++) {
            String[] data = br.readLine().trim().split(" ");
            for (int j = 0; j < data.length; j++) {
                int weight = Integer.parseInt(data[j]);
                pq.add(new Edge(i, i + j + 1, weight));
            }
        }

        parent = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }

        List<List<Integer>> tree = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            tree.add(new ArrayList<>());
        }

        int edgesUsed = 0;
        while (edgesUsed < N - 1) {
            Edge edge = pq.poll();
            int rootX = findParent(edge.x);
            int rootY = findParent(edge.y);

            if (rootX != rootY) {
                parent[rootX] = rootY;
                tree.get(edge.x).add(edge.y);
                tree.get(edge.y).add(edge.x);
                edgesUsed++;
            }
        }

        for (int i = 1; i <= N; i++) {
            List<Integer> neighbors = tree.get(i);
            Collections.sort(neighbors);
            System.out.print(neighbors.size() + " ");
            for (int neighbor : neighbors) {
                System.out.print(neighbor + " ");
            }
            System.out.println();
        }
    }

    static int findParent(int x) {
        if (parent[x] != x) {
            parent[x] = findParent(parent[x]);
        }
        return parent[x];
    }

    static class Edge {
        int x, y, weight;
        Edge(int x, int y, int weight) {
            this.x = x;
            this.y = y;
            this.weight = weight;
        }
    }
}
