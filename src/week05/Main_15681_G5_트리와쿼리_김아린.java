package week05;

import java.io.*;
import java.util.*;

public class Main_15681_G5_트리와쿼리_김아린 {
    public static List<Integer>[] connect;
    public static List<Integer>[] currentNode;
    public static int[] parent;
    public static int[] size;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        connect = new LinkedList[N+1];
        currentNode = new LinkedList[N+1];
        parent = new int[N+1];
        size = new int[N+1];

        for (int i = 1; i <= N; i++) {
            connect[i] = new LinkedList<Integer>();
            currentNode[i] = new LinkedList<Integer>();
        }

        for(int i = 0; i < N-1; i++) {
            st = new StringTokenizer(in.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            //양방향이라서
            connect[a].add(b);
            connect[b].add(a);
        }

        //부모 없는 처음부터
        makeTree(R, -1);
        countSubTreeNodes(R);

        for(int i = 0; i < Q; i++) {
            int q = Integer.parseInt(in.readLine());

            System.out.println(size[q]);
        }
    }

    public static void makeTree(int cur, int p) {
        for(int node : connect[cur]) {
            if(node != p) {
                currentNode[cur].add(node);
                parent[node] = cur;
                makeTree(node, cur);
            }
        }
    }

    public static void countSubTreeNodes(int cur) {
        // 자신도 자신을 루트로 하는 서브트리에 포함되므로 0이 아닌 1에서 시작한다.
        size[cur] = 1;

        for(int node : currentNode[cur]) {
            countSubTreeNodes(node);
            size[cur] += size[node];
        }
    }
}