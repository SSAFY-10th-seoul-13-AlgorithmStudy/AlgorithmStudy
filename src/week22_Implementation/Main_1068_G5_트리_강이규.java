package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;


public class Main_1068_G5_트리_강이규 {

    static int N, target;
    static int[] parent;
    static List<Integer>[] children;
    static int res;
    public static void main(String[] args) throws IOException {
        int root = init();
        dfs(root);
        System.out.println(res);
    }

    private static boolean dfs(int cur) {
        if (cur == target)
            return true;
        if (children[cur].isEmpty()) { // leaf
            res++;
            return false;
        }

        for (int child : children[cur]) {
            boolean childWillDeleted = dfs(child);
            // target을 삭제하면 리프 노드가 되는 경우
            if (childWillDeleted && children[cur].size() == 1) {
//                System.out.println("cur = " + cur);
//                System.out.println("children[cur] = " + children[cur]);
                res++;
                return false;
            }
        }
        return false;
    }

    private static int init() throws IOException {
        int root = -1;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        parent = new int[N];
        children = new LinkedList[N];
        for (int i = 0; i < N; i++) {
            children[i] = new LinkedList<>();
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int p = Integer.parseInt(st.nextToken());
            parent[i] = p;
            if (p == -1) {
                root = i;
                continue;
            }
            children[p].add(i);
        }
        target = Integer.parseInt(br.readLine());
        res = 0;
        return root;
    }
}
