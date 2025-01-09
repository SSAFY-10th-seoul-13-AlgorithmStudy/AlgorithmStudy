package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2268_G1_수들의합7_강이규 {

    static int N, M;
    static int[] arr;
    static long[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N];
        tree = new long[N << 2];
        make(1, 0, N - 1);

        StringBuilder sb = new StringBuilder();
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            if (Integer.parseInt(st.nextToken()) != 1) { // sum
                int i = Integer.parseInt(st.nextToken()) - 1;
                int j = Integer.parseInt(st.nextToken()) - 1;
                int min = Math.min(i, j);
                int max = Math.max(i, j);
                sb.append(sum(0, N - 1, min, max, 1)).append("\n");
            } else { // modify
                int i = Integer.parseInt(st.nextToken()) - 1;
                int k = Integer.parseInt(st.nextToken());
                modify(0, N - 1, i, k - arr[i], 1);
            }
        }
        System.out.print(sb);
    }

    private static long make(int node, int start, int end) { // 노드번호, 표시하는 구간
        if (start == end) {
            return tree[node] = (long) arr[start];
        }
        int mid = (start + end) >> 1;
        int lChild = node << 1;

        return tree[node] = make(lChild, start, mid) + make(lChild + 1, mid + 1, end);
    }

    private static long sum(int start, int end, int l, int r, int node) {
        // 전체 구간이 포함될 때
        if (l <= start && end <= r) {
            return tree[node];
        }
        // 범위 밖일때
        if (r < start || end < l) {
            return 0;
        }
        int mid = (start + end) >> 1;
        int lChild = node << 1;

        return sum(start, mid, l, r, lChild) + sum(mid + 1, end, l, r, lChild + 1);
    }

    private static void modify(int start, int end, int dist, int gap, int node) {
        // 범위 밖일 때
        if (dist < start || end < dist) {
            return;
        }
        tree[node] += gap;
        // 리프 노드일 때
        if (start == end) {
            arr[dist] = (int) tree[node]; // 범위 벗어날 가능성 X
            return;
        }
        int mid = (start + end) >> 1;
        int lChild = node << 1;

        modify(start, mid, dist, gap, lChild);
        modify(mid + 1, end, dist, gap, lChild + 1);
    }
}
