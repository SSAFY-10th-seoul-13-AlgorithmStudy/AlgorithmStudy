package BOJ;

import java.io.*;
import java.util.*;

public class Main_9489_G4_사촌_강이규 {
    static int N, K;
    static int[] node;
    static int[] parent;
    static int[] grandParent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        while (true) {
            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            // 종료조건
            if (N == 0) break;

            node = new int[N+1];
            parent = new int[N+1];
            grandParent = new int[N+1];

            K = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());

            if (N <= 2) {
                sb.append("0\n");
                continue;
            }

            for (int i = 1; i <= N; i++) {
                node[i] = Integer.parseInt(st.nextToken());
            }

            // parent
            parent[2] = 1;

            int parentIdx = 1;
            int pre = node[2];
            for (int i = 3; i <= N; i++) {
                if (node[i] != pre + 1) parentIdx++;
                parent[i] = parentIdx;
                pre = node[i];
            }

            // grandParent
            for (int i = 2; i <= N; i++) {
                grandParent[i] = parent[parent[i]];
            }

            // count
            int kIdx = Arrays.binarySearch(node, K);
            int kParent = parent[kIdx];
            int kGrandParent = grandParent[kIdx];

            int cnt = 0;
            for (int i = 2; i <= N; i++) {
                if (grandParent[i] != kGrandParent) continue;
                if (parent[i] == kParent) continue;
                cnt++;
            }
            sb.append(cnt).append("\n");
        }
        System.out.print(sb);
    }
}
