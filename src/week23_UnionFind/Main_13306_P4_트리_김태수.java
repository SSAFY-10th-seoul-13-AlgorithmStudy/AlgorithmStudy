package week23_UnionFind;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_13306_P4_트리_김태수 {
    static int tree[], N, Q;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        Stack<int[]> queries = new Stack();
        Stack<String> answers = new Stack<>();

        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        tree = new int[N + 1];

        for (int i = 1; i <= N - 1; i++) {
            tree[i + 1] = Integer.parseInt(br.readLine());
        }

        int cnt = N - 1 + Q;

        while (cnt-- > 0) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            if (x == 0) {
                int b = Integer.parseInt(st.nextToken());
                queries.push(new int[] { 0, b, tree[b] });
                tree[b] = b;
            } else {
                int c = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());
                queries.push(new int[] { 1, c, d });
            }
        }

        while (!queries.isEmpty()) {
            int[] top = queries.peek();
            if (top[0] == 1) {
                int c = top[1];
                int d = top[2];

                if (find(c) == find(d)) {
                    answers.push("YES");
                } else {
                    answers.push("NO");
                }
            } else {
                int parVal = top[2];
                int child = top[1];
                union(child, parVal);
            }
            queries.pop();
        }
        while (!answers.isEmpty()) {
            System.out.println(answers.pop());
        }
    }

    public static int find(int x) {
        if (tree[x] == x)
            return x;
        else
            return tree[x] = find(tree[x]);
    }

    public static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a == b)
            return;
        tree[a] = b;
    }
}
