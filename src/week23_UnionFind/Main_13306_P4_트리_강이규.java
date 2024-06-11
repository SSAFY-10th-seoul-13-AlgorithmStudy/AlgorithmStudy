package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_13306_P4_트리_강이규 {

    static int N, K;
    static int[] root;
    static int[] parent;
    static Stack<Operation> operation;

    private static void make() {
        for (int i = 1; i <= N; i++) {
            root[i] = i;
        }
    }

    private static int find(int a) {
        if (root[a] == a) return a;
        return root[a] = find(root[a]);
    }

    private static boolean union(int a) {
        int aRoot = find(a);
        int aParentRoot = find(parent[a]);

        if (aRoot == aParentRoot) return false;
        root[aParentRoot] = aRoot;
        return true;
    }

    public static void main(String[] args) throws IOException {
        init();
        doOperations();
    }

    private static void doOperations() {
        StringBuilder sb = new StringBuilder();
        Stack<String> res = new Stack<>();
        for (int i = 0; i < K; i++) {
            Operation o = operation.pop();
            if (o.op == 0) { // 끊기 연산
                union(o.a);
            } else {
                res.push(find(o.a) == find(o.b) ? "YES\n" : "NO\n");
            }
        }
        int size = res.size();
        for (int i = 0; i < size; i++) {
            sb.append(res.pop());
        }
        System.out.print(sb);
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());
        K = N - 1 + q;

        root = new int[N+1]; // 현 시점의 조상 노드 정보를 저장하는 배열(기존 Union find에서 parent 배열)
        parent = new int[N+1]; // 부모 노드 정보를 read only로 저장할 배열
        operation = new Stack<>();

        for (int i = 2; i <= N; i++) {
            parent[i] = Integer.parseInt(br.readLine());
        }

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            Operation o = new Operation(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            if (st.hasMoreTokens()) {
                o.setB(Integer.parseInt(st.nextToken()));
            }
            operation.push(o);
        }
        make();
    }

    static class Operation {
        int op, a, b;

        public Operation(int op, int a) {
            this.op = op;
            this.a = a;
        }

        public void setB(int b) {
            this.b = b;
        }
    }
}
